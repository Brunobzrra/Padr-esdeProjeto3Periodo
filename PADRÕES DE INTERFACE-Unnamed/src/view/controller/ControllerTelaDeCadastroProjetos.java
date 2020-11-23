package view.controller;

import java.util.Date;

import model.casosDeUsofachadas.CasoDeUsoCinco;
import model.casosDeUsofachadas.CasoDeUsoSeis;

public class ControllerTelaDeCadastroProjetos {

	private CasoDeUsoCinco casoDeUsoCinco;

	private CasoDeUsoSeis casoDeUsoSeis;

	public ControllerTelaDeCadastroProjetos() {

		casoDeUsoCinco = new CasoDeUsoCinco();

		casoDeUsoSeis = new CasoDeUsoSeis();
	}

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador)
			throws Exception {

		casoDeUsoCinco.criarProjeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais, matricula, aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos,
				coordenador);

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
}
