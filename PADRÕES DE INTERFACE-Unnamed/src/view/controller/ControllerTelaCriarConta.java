package view.controller;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoUm;

public class ControllerTelaCriarConta {

	private CasoDeUsoUm casoDeUsoUm;
	private CasoDeUsoExtra casoDeUsoExtra;

	public ControllerTelaCriarConta() {
		casoDeUsoUm = new CasoDeUsoUm();
	}

	public void cadastrarMembro(String nome, long matricula, String email, String senha) throws Exception {
		casoDeUsoUm.cadastrarMembro(nome, matricula, email, senha);

	}

	public void atualizarMembro(long matricula, long matriculaNovo, String nomeNovo, String emailNovo, String senhaNova)
			throws Exception {
		casoDeUsoUm.atualizarMembro(matricula, matriculaNovo, nomeNovo, emailNovo, senhaNova);

	}

	public Object[] recuperarMembro(long matricula) {
		return casoDeUsoExtra.recuperarMembro(matricula);
	}
	
	public boolean isVazia() {
		return casoDeUsoUm.isVazia();
	}
}
