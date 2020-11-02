package ponto.model.projetos.flyweight;

import ponto.model.projetos.DiaSemana;
/**
 * parte EXTRÍNSECA - Flyweight 
 * @author Antônio Amorim
 *
 */
public class HorarioPrevistoExatoFlyweight implements HorarioPrevistoFlyweight {
	private DiaSemana diaSemana;
	private short horaInicio;
	private short horaTermino;
	private HorarioPrevistoFlyweight minutosTolerante;

	public HorarioPrevistoExatoFlyweight(DiaSemana dia,short horaInicio, short horaTermino, HorarioPrevistoFlyweight toleranciaMinutos) {
		diaSemana=dia;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.setToleranciaMinutos(toleranciaMinutos);
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public short getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(short horaInicio) {
		this.horaInicio = horaInicio;
	}

	public short getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(short horaTermino) {
		this.horaTermino = horaTermino;
	}
	public HorarioPrevistoFlyweight getMinutosTolerantes(){
		return minutosTolerante;
	}
	public short getToleranciaMinutos() {
		return minutosTolerante.getToleranciaMinutos();
	}

	public void setToleranciaMinutos(HorarioPrevistoFlyweight minutosTolerante) {
		this.minutosTolerante = minutosTolerante;
	}

}
