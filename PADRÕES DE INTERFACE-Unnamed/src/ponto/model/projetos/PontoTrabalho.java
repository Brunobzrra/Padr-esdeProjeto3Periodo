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
	public long getDataHoraEntrada() {
		return dataHoraEntrada;
	}
	public void setDataHoraEntrada(long dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}
	public long getDataHoraSaida() {
		return dataHoraSaida;
	}
	public void setDataHoraSaida(long dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public boolean isJustificativaAceita() {
		return justificativaAceita;
	}
	public void setJustificativaAceita(boolean justificativaAceita) {
		this.justificativaAceita = justificativaAceita;
	}
	
}
