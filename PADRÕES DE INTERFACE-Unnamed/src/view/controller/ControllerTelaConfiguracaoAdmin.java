package view.controller;

import model.casosDeUsofachadas.CasoDeUsoSete;

/**
 * Classe controladora que fornece metodos para a classe cliente (view)
 * responsavel pela configuracao (adicao/remocao) de administradores , usando-se
 * das fachadas de caso de uso para isso.
 * 
 * @author bruno
 */
public class ControllerTelaConfiguracaoAdmin {

	private CasoDeUsoSete casoDeUsoSete = new CasoDeUsoSete();

	public void habilitarAdministrador(long matriculaDoAdministrador, long matricula) throws Exception {
		casoDeUsoSete.habilitarAdministrador(matriculaDoAdministrador, matricula);

	}
}
