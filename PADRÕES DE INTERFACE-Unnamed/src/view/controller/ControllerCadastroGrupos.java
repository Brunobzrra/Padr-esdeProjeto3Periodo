package view.controller;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoTres;

public class ControllerCadastroGrupos {

	private CasoDeUsoTres casoDeUsoTres;
	private CasoDeUsoExtra casoDeUsoExtra;
	public ControllerCadastroGrupos() {
		casoDeUsoTres = new CasoDeUsoTres();
	}

	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception {
		casoDeUsoTres.adcionarGrupo(nome, linkCNPq, matricula);

	}

	public void removerGrupo(long matricula, String linkCNPq) throws Exception {
		casoDeUsoTres.removerGrupo(matricula, linkCNPq);

	}

	public void atualizarrGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) throws Exception {
		casoDeUsoTres.atualizarrGrupo(matricula, linkCNPq, nomeNovo, linkCNPqNovo);

	}
	public Object[] recuperarGrupo(String linkCNPq) {
		return casoDeUsoExtra.recuperarGrupo(linkCNPq);
	}
	public void mostrarGruposDoUsuarioLogado() {

	}
}
