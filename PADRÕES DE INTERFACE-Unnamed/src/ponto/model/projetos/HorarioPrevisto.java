package ponto.model.projetos;

public class HorarioPrevisto {
	private DiaSemana diaSemana;
	private short horaInicio;
	private short horaTermino;
	private short toleranciaMinutos;
	
	public HorarioPrevisto(short horaInicio, short horaTermino, short toleranciaMinutos) {
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.toleranciaMinutos = toleranciaMinutos;
	}
	public int getExpectativaHorasTrabalhadas() {
		return 0;
	}
}
