package view.projetos;

import java.awt.Color;
import java.util.Date;

import javax.swing.JPanel;

import view.controller.ControllerTelaDeCadastroProjetos;

public class TelaCadastroProjetos extends JPanel {
	public TelaCadastroProjetos() {
		setBounds(150, 0, 550, 500);
		setBackground(Color.BLACK);
		setVisible(true);
	}

	private ControllerTelaDeCadastroProjetos controller = new ControllerTelaDeCadastroProjetos();

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador)
			throws Exception {

		controller.criarProjeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais, matricula, aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos,
				coordenador);

	}

	public void atualizarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais) throws Exception {
		controller.atualizarProjeto(nome, aporteCusteioReais, aporteCapitalReais);

	}

	public void removerProjeto(String nomeDoProjeto) throws Exception {
		controller.removerProjeto(nomeDoProjeto);

	}

	public void adicionarParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerEstrarNoProjeto,
			String nomeDoProjeto, Date dataInicio, float aporteCusteioMensalReais, short qtdMesesCusteados,
			short qtdMesesPagos) throws Exception {

		controller.adicionarParticipacao(matriculaDoCordenador, matriculaDoMembroQueQuerEstrarNoProjeto, nomeDoProjeto,
				dataInicio, aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos);
	}

	public void removerParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerRemover,
			String nomeDoProjeto) throws Exception {
		controller.removerParticipacao(matriculaDoCordenador, matriculaDoMembroQueQuerRemover, nomeDoProjeto);

	}

}
