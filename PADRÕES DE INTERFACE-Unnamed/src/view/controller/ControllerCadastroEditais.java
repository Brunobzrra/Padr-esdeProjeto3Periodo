package view.controller;

import java.util.Date;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoQuatro;

public class ControllerCadastroEditais {

	private CasoDeUsoQuatro casoDeUsoQuatro;
	
	private CasoDeUsoExtra casoDeUsoExtra;

	public ControllerCadastroEditais() {
		casoDeUsoQuatro = new CasoDeUsoQuatro();
		casoDeUsoExtra = new CasoDeUsoExtra();
	}

	public void adcionarEdital(String nomeEdital, Date dataInicio, Date dataTermino, long matricula) throws Exception {
		casoDeUsoQuatro.adcionarEdital(nomeEdital, dataInicio, dataTermino, matricula);
	}

	public void atualizarEdital(String nomeEdital, String novoNome, Date dataInicio, Date dataTermino, long matricula)
			throws Exception {
		casoDeUsoQuatro.atualizarEdital(nomeEdital, novoNome, dataInicio, dataTermino, matricula);

	}

	public void removerEdital(String nomeEdital, long matricula) throws Exception {
		casoDeUsoQuatro.removerEdital(nomeEdital, matricula);
	}
	
	public Object[] recuperarEdital(String nome) {
		return casoDeUsoExtra.recuperarEdital(nome);
	}
	
	public void mostrarEditaisDoUsuario() {
		//que metodo é pra chamar aqui?
	}

}
