package ponto.model.projetos.flyweight;

import java.util.ArrayList;

public class HararioPervistoFlyWeigtFactory {

	private ArrayList<HorarioPrevistoFlyweight> horarios = new ArrayList<HorarioPrevistoFlyweight>();

	public HorarioPrevistoFlyweight getFlyweight(short toleranciaEmMinutos) {
		HorarioPrevistoFlyweight horarioExistente = null;
		for (HorarioPrevistoFlyweight horarioBuscado : horarios) {
			if (horarioBuscado.getToleranciaMinutos() == toleranciaEmMinutos) {
				horarioExistente = horarioBuscado;
			}
		}
		if (horarioExistente == null) {
			horarioExistente = new HorarioDeToleranciaFlyweight(toleranciaEmMinutos);
			horarios.add(horarioExistente);
		}
		return horarioExistente;
	}
}
