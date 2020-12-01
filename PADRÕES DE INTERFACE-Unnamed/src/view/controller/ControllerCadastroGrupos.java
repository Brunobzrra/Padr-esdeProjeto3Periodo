package view.controller;

import java.util.ArrayList;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoTres;

/**
 * Classe controladora que fornece metodos para a classe cliente (view)
 * responsavel pelo cadastro de grupos, usando-se das fachadas de caso de uso
 * para isso.
 * 
 * @author bruno
 */
public class ControllerCadastroGrupos {

	private CasoDeUsoTres casoDeUsoTres;
	private CasoDeUsoExtra casoDeUsoExtra;

	public ControllerCadastroGrupos() {
		casoDeUsoTres = new CasoDeUsoTres();
		casoDeUsoExtra = new CasoDeUsoExtra();
	}

	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception {
		casoDeUsoTres.adcionarGrupo(nome, linkCNPq, matricula);

	}

	public void removerGrupo(long matricula, String linkCNPq) throws Exception {
		casoDeUsoTres.removerGrupo(matricula, linkCNPq);

	}

	public void atualizarGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) throws Exception {
		casoDeUsoTres.atualizarrGrupo(matricula, linkCNPq, nomeNovo, linkCNPqNovo);

	}

	public Object[] recuperarGrupo(String linkCNPq) {
		return casoDeUsoExtra.recuperarGrupo(linkCNPq);
	}

	public ArrayList<String> mostrarGruposDoUsuarioLogado() throws Exception {
		return casoDeUsoExtra.recuperarGrupos();
	}
}
