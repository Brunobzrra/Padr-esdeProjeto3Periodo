package view.controller;

import java.time.LocalDateTime;

import model.casosDeUsofachadas.CasoDeUsoDoze;

/**
 * Classe controladora que fornece metodos para a classe cliente (view)
 * responsavel pela justificacao de um ponto nao valido, usando-se das fachadas
 * de caso de uso para isso.
 * 
 * @author bruno
 */

public class ControllerTelaJustificativaPonto {

	private CasoDeUsoDoze casoDeUsoDoze;

	public ControllerTelaJustificativaPonto() {

		casoDeUsoDoze = new CasoDeUsoDoze();
	}

	public void justificarPonto(LocalDateTime dataHoraEntrada, String justificar, long matricula) throws Exception {
		casoDeUsoDoze.justificarPonto(dataHoraEntrada, justificar, matricula);
	}
}
