package ponto.model.projetos;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PontoTrabalhado {
	private LocalDateTime dataHoraEntrada;
	private LocalDateTime dataHoraSaida;
	private String justificativa;
	private boolean justificativaAceita = true;

	public PontoTrabalhado(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public PontoTrabalhado(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida, String justificativa,
			boolean justificativaAceita) {
		this.dataHoraEntrada = dataHoraEntrada;
		this.dataHoraSaida = dataHoraSaida;
		this.justificativa = justificativa;
		this.justificativaAceita = justificativaAceita;
	}

	public String getHorasTrabalhadas() throws Exception {
		if (dataHoraEntrada != null && dataHoraSaida != null) {
			return "Horas: " + (dataHoraSaida.getHour() - dataHoraEntrada.getHour()) + "Minutos: "
					+ (dataHoraSaida.getMinute() - dataHoraEntrada.getMinute());
		}
		throw new Exception("Horario de saida/entrada nao encontrado");
	}

	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
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

	public String toString() {
		String aceito = "Aceita não";
		if (justificativaAceita) {
			aceito = "Aceita";
		}
		return "Entrada: " + getDataHoraEntrada().toString() + "\nSaida: " + getDataHoraSaida().toString()
				+ "\nJustificativa: " + getJustificativa() + " - " + aceito;
	}

	public boolean equals(PontoTrabalhado ponto) {
		if (getDataHoraEntrada().equals(ponto.getDataHoraEntrada())
				&& getDataHoraSaida().equals(ponto.getDataHoraSaida()))
			return true;
		return false;
	}
}
