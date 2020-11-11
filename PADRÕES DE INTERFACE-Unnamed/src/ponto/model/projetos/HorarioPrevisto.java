package ponto.model.projetos;

import java.io.Serializable;

public class HorarioPrevisto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DiaSemana diaSemana;
	private short horaInicio;
	private short horaTermino;
	private short minutosTolerante;

	public HorarioPrevisto(DiaSemana dia,short horaInicio, short horaTermino, short toleranciaMinutos) {
		diaSemana=dia;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		setMinutosTolerante(toleranciaMinutos);
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

	public short getMinutosTolerante() {
		return minutosTolerante;
	}

	public void setMinutosTolerante(short minutosTolerante) {
		this.minutosTolerante = minutosTolerante;
	}

}
