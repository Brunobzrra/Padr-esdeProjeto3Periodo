package view.controller;

import java.util.ArrayList;
import java.util.Date;

import model.casosDeUsofachadas.CasoDeUsoCinco;
import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoSeis;

public class ControllerTelaDeCadastroProjetos {

	private CasoDeUsoCinco casoDeUsoCinco;

	private CasoDeUsoSeis casoDeUsoSeis;
	
	private CasoDeUsoExtra casoDeUsoExtra;

	public ControllerTelaDeCadastroProjetos() {

		casoDeUsoCinco = new CasoDeUsoCinco();

		casoDeUsoSeis = new CasoDeUsoSeis();
		
		casoDeUsoExtra= new CasoDeUsoExtra();
	}

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos)
			throws Exception {

		casoDeUsoCinco.criarProjeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais, matricula, aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos);

	}

	public void atualizarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais) throws Exception {
		casoDeUsoCinco.atualizarProjeto(nome, aporteCusteioReais, aporteCapitalReais);

	}

	public void removerProjeto(String nomeDoProjeto) throws Exception {
		casoDeUsoCinco.removerProjeto(nomeDoProjeto);

	}

	public void adicionarParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerEstrarNoProjeto,
			String nomeDoProjeto, Date dataInicio, float aporteCusteioMensalReais, short qtdMesesCusteados,
			short qtdMesesPagos) throws Exception {

		casoDeUsoSeis.adicionarParticipacao(matriculaDoCordenador, matriculaDoMembroQueQuerEstrarNoProjeto,
				nomeDoProjeto, dataInicio, aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos);
	}

	public void removerParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerRemover,
			String nomeDoProjeto) throws Exception {
		casoDeUsoSeis.removerParticipacao(matriculaDoCordenador, matriculaDoMembroQueQuerRemover, nomeDoProjeto);
		
	}
	public ArrayList<String> mostrarProjetosDoUsuarioLogado() throws Exception {
		return casoDeUsoExtra.recuperarProjetos();
	}
	public Object[] recuperarProjeto(String nome) {
		return casoDeUsoExtra.recuperarPtojeto(nome);
	}
}
