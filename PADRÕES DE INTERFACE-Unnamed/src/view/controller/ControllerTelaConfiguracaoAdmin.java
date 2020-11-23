package view.controller;

import model.casosDeUsofachadas.CasoDeUsoSete;

public class ControllerTelaConfiguracaoAdmin {

	private CasoDeUsoSete casoDeUsoSete;

	public ControllerTelaConfiguracaoAdmin(long matriculaDoAdministrador) throws Exception {
		casoDeUsoSete = new CasoDeUsoSete(matriculaDoAdministrador);
	}

	public void habilitarAdministrador(long matricula) throws Exception {
		casoDeUsoSete.habilitarAdministrador(matricula);

	}
}
