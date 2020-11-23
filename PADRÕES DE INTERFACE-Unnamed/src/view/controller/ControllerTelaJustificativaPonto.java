package view.controller;

import java.time.LocalDateTime;

import model.casosDeUsofachadas.CasoDeUsoDoze;

public class ControllerTelaJustificativaPonto {

	private CasoDeUsoDoze casoDeUsoDoze;

	public ControllerTelaJustificativaPonto() {

		casoDeUsoDoze = new CasoDeUsoDoze();
	}

	public void justificarPonto(LocalDateTime dataHoraEntrada, String justificar, long matricula) throws Exception {
		casoDeUsoDoze.justificarPonto(dataHoraEntrada, justificar, matricula);
	}
}
