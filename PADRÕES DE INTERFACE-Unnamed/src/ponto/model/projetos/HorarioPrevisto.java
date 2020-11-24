package ponto.model.projetos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HorarioPrevisto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DiaSemana diaSemana;
	private LocalDateTime horaInicio;
	private LocalDateTime horaTermino;
	private short minutosTolerante;

	public HorarioPrevisto(DiaSemana dia,LocalDateTime horaInicio, LocalDateTime horaTermino, short toleranciaMinutos) {
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

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDateTime getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(LocalDateTime horaTermino) {
		this.horaTermino = horaTermino;
	}

	public short getMinutosTolerante() {
		return minutosTolerante;
	}

	public void setMinutosTolerante(short minutosTolerante) {
		this.minutosTolerante = minutosTolerante;
	}

}
