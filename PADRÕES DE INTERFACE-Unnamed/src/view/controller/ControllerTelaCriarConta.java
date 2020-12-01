package view.controller;

import model.casosDeUsofachadas.CasoDeUsoExtra;

import model.casosDeUsofachadas.CasoDeUsoUm;

/**
 * Classe controladora que fornece metodos para a classe cliente (view)
 * responsavel pela criacao de novas contas, usando-se das fachadas de caso de
 * uso para isso.
 * 
 * @author bruno
 */

public class ControllerTelaCriarConta {

	private CasoDeUsoUm casoDeUsoUm;
	private CasoDeUsoExtra casoDeUsoExtra;

	public ControllerTelaCriarConta() {
		casoDeUsoUm = new CasoDeUsoUm();
		casoDeUsoExtra = new CasoDeUsoExtra();
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
