package ponto.model.projetos.flyweight;

import ponto.model.projetos.DiaSemana;

public class HorarioPrevistoExatoFlyweight implements HorarioPrevistoFlyweight {
	private DiaSemana diaSemana;
	private short horaInicio;
	private short horaTermino;
	private HorarioDeToleranciaFlyweight toleranciaMinutos;

	public HorarioPrevistoExatoFlyweight(short horaInicio, short horaTermino, short toleranciaMinutos) {
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.setToleranciaMinutos(new HorarioDeToleranciaFlyweight(toleranciaMinutos));
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

	@Override
	public short getToleranciaMinutos() {
		// TODO Auto-generated method stub
		return toleranciaMinutos.getToleranciaMinutos();
	}

	public void setToleranciaMinutos(HorarioDeToleranciaFlyweight toleranciaMinutos) {
		this.toleranciaMinutos = toleranciaMinutos;
	}

}
