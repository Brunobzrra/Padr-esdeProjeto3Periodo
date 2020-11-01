package ponto.model.projetos.flyweight;

import java.time.LocalDateTime;

import ponto.model.projetos.DiaSemana;

public class HorarioPrevisto {
	private HorarioDeToleranciaFlyweight toleranciaMinutos;
	private HorarioPrevistoExatoFlyweight horarioPrevisto;

	public int getExpectativaHorasTrabalhadas() {
		return horarioPrevisto.getHoraTermino()-horarioPrevisto.getHoraInicio();
	}
	public HorarioPrevisto getFlyweight(DiaSemana diaSemana, LocalDateTime horaInicio, LocalDateTime horaTerminei,short toleranciaEmMinutos ) {
		return null;
		
	}
	public HorarioDeToleranciaFlyweight getToleranciaMinutos() {
		return toleranciaMinutos;
	}

	public void setToleranciaMinutos(HorarioDeToleranciaFlyweight toleranciaMinutos) {
		this.toleranciaMinutos = toleranciaMinutos;
	}

	public HorarioPrevistoExatoFlyweight getHorarioPrevisto() {
		return horarioPrevisto;
	}

	public void setHorarioPrevisto(HorarioPrevistoExatoFlyweight horarioPrevisto) {
		this.horarioPrevisto = horarioPrevisto;
	}
	
}
