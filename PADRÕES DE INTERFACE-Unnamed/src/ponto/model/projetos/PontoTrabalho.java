package ponto.model.projetos;

public class PontoTrabalho {
	private long dataHoraEntrada;
	private long dataHoraSaida;
	private String justificativa;
	private boolean justificativaAceita;
	
	public PontoTrabalho(long dataHoraEntrada, long dataHoraSaida, String justificativa, boolean justificativaAceita) {
		this.dataHoraEntrada = dataHoraEntrada;
		this.dataHoraSaida = dataHoraSaida;
		this.justificativa = justificativa;
		this.justificativaAceita = justificativaAceita;
	}
	public int getHorasTrabalhadas() {
		return 0;
	}
}
