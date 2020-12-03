package view.controller;

import model.casosDeUsofachadas.CasoDeUsoOito;

public class ControllerGerarRelatorio {
	private CasoDeUsoOito casoDeUsoOito;

	public void gerarRelatorio(String op, String valor) throws Exception {
		casoDeUsoOito.gerarRelatorio(op, valor);
	}
}
