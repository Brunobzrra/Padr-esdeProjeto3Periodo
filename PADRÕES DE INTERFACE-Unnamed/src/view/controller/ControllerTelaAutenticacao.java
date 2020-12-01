package view.controller;

import model.casosDeUsofachadas.CasoDeUsoDoiseNove;

/**
 * Clase controladora que fornece metodos para a classe cliente (view)
 * responsavel pela autenticacao de um usuario, usando-se das fachadas de caso
 * de uso para isso.
 * 
 * @author bruno
 */

public class ControllerTelaAutenticacao {

	private CasoDeUsoDoiseNove casoDeUso;

	public ControllerTelaAutenticacao() {
		casoDeUso = new CasoDeUsoDoiseNove();
	}

	public void fazerLogin(String login, String senha, String tipoProvedor) throws Exception {
		casoDeUso.fazerLogin(login, senha, tipoProvedor);

	}

	public void fazerLogout(String login) throws Exception {
		casoDeUso.fazerLogout(login);

	}

	public boolean isAdmin() {
		return casoDeUso.isAdmin();
	}

}
