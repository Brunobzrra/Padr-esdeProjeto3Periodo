package ponto.model.projetos.flyweight;

import java.util.ArrayList;

import ponto.model.projetos.DiaSemana;
/**
 * Factory Flywieght
 * Aplicado o singleton pois deveria manter uma unica instancia unversal para todo o sistema 
 * para garantir a coesão dos dados
 * @author Antônio Amorim
 *
 */
public class HorarioPrevisto {
	private static HorarioPrevisto horarioPrevistoSingleton= new HorarioPrevisto();
	private HorarioPrevistoExatoFlyweight horarioPrevisto;
	private ArrayList<HorarioPrevistoFlyweight> toleranciaMinutos = new ArrayList<HorarioPrevistoFlyweight>();
	private HorarioPrevisto() {
		
	}
	public static synchronized HorarioPrevisto getInstance(){
		return horarioPrevistoSingleton;
	}
	public int getExpectativaHorasTrabalhadas() {
		return horarioPrevisto.getHoraTermino()-horarioPrevisto.getHoraInicio();
	}
	/**
	 * este metodo retorna a parte EXTRÍNSECA que trás consigo também a parte INSTRÍNSECA
	 * @param dia
	 * @param horaInicio
	 * @param horaTermino
	 * @param toleranciaEmMinutos
	 * @return
	 */
	public HorarioPrevistoFlyweight getFlyweight(DiaSemana dia,short horaInicio, short horaTermino,short toleranciaEmMinutos ) {
		HorarioPrevistoFlyweight horarioExistente = null;
		for (HorarioPrevistoFlyweight horarioBuscado : toleranciaMinutos) {
			if (horarioBuscado.getToleranciaMinutos() == toleranciaEmMinutos) {
				horarioExistente = horarioBuscado;
			}
		}
		if (horarioExistente == null) {
			horarioExistente = new HorarioDeToleranciaFlyweight(toleranciaEmMinutos);
			toleranciaMinutos.add(horarioExistente);
		}
		horarioPrevisto= new HorarioPrevistoExatoFlyweight(dia,horaInicio, horaTermino, horarioExistente);
		return horarioPrevisto;
	}
	public ArrayList<HorarioPrevistoFlyweight> getToleranciaMinutos() {
		return toleranciaMinutos;
	}

	public void setToleranciaMinutos(ArrayList<HorarioPrevistoFlyweight> toleranciaMinutos) {
		this.toleranciaMinutos = toleranciaMinutos;
	}

	public HorarioPrevistoExatoFlyweight getHorarioPrevisto() {
		return horarioPrevisto;
	}

	public void setHorarioPrevisto(HorarioPrevistoExatoFlyweight horarioPrevisto) {
		this.horarioPrevisto = horarioPrevisto;
	}
	
}
