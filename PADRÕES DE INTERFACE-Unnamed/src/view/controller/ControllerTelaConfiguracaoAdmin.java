package view.controller;

import model.casosDeUsofachadas.CasoDeUsoSete;

public class ControllerTelaConfiguracaoAdmin {

	private CasoDeUsoSete casoDeUsoSete= new CasoDeUsoSete();

	public void habilitarAdministrador(long matriculaDoAdministrador,long matricula) throws Exception {
		casoDeUsoSete.habilitarAdministrador(matriculaDoAdministrador,matricula);

	}
}
