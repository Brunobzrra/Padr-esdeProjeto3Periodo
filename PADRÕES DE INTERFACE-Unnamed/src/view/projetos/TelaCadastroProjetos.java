package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.controller.ControllerTelaDeCadastroProjetos;

public class TelaCadastroProjetos extends JPanel {
	private ControllerTelaDeCadastroProjetos controller = new ControllerTelaDeCadastroProjetos();

	private JTextField nomeProjetoCriar;
	private JTextField aporteCusteioReaisCriar;
	private JTextField aporteCapitalReaisCriar;
	private JTextField gastoExecutadoCusteioReaisCriar;
	private JTextField gastoExecutadoCapitalReaisCriar;
	private JTextField aporteCusteioMensalReaisCriar;
	private JTextField qtdMesesCusteadosCriar;
	private JTextField qtdMesesPagosCriar;
	private JTextField matriculaCoordenadorCriar;
	private JTextField nomeProjetoAtualizar;
	private JTextField aporteCusteioReaisNovo;
	private JTextField aporteCapitalReaisNovo;

	private JTextField nomeProjetoRemover;

	private JTextField nomeProjetoR;
	private JTextField MatriculaRemover;
	private JTextField matriculaCoordenador;

	private JTextField matriculaDoCordenadorAdcionar;
	private JTextField matriculaDoMembroQueQuerEstrarNoProjetoAdcionar;
	private JTextField nomeDoProjetoAdcionar;
	private JTextField dataInicioAdcionar;
	private JTextField qtdMesesPagosAdcionar;
	private JTextField qtdMesesCusteadosMensalReaisAdcionar;
	private JTextField aporteCusteioMensalReaisAdcionar;

	public TelaCadastroProjetos() {
		setBounds(150, 0, 850, 700);
		setBackground(new Color(213, 213, 213));
		setLayout(null);
		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		setVisible(true);
	}

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos) throws Exception {

		controller.criarProjeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais, matricula, aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos);

	}

	public void atualizarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais) throws Exception {
		controller.atualizarProjeto(nome, aporteCusteioReais, aporteCapitalReais);

	}

	public void removerProjeto(String nomeDoProjeto) throws Exception {
		controller.removerProjeto(nomeDoProjeto);

	}

	public void adicionarParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerEstrarNoProjeto,
			String nomeDoProjeto, String dataInicio, float aporteCusteioMensalReais, short qtdMesesCusteados,
			short qtdMesesPagos) throws Exception {

		controller.adicionarParticipacao(matriculaDoCordenador, matriculaDoMembroQueQuerEstrarNoProjeto, nomeDoProjeto,
				new Date(dataInicio), aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos);
	}

	public void removerParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerRemover,
			String nomeDoProjeto) throws Exception {
		controller.removerParticipacao(matriculaDoCordenador, matriculaDoMembroQueQuerRemover, nomeDoProjeto);

	}

	public void mostrarProjetosDoUsuarioLogado() {
		// o que fazer aqui?
	}

	private void adcionarLabels() {
		JLabel cadastrar = new JLabel("Cadastrar Projeto");
		cadastrar.setBounds(40, 20, 300, 40);
		cadastrar.setFont(new Font("Arial", Font.BOLD, 30));
		cadastrar.setForeground(Color.DARK_GRAY);
		this.add(cadastrar);

		JLabel nomeProjeto = new JLabel("Nome do projeto:");
		nomeProjeto.setBounds(170, 60, 300, 40);
		nomeProjeto.setFont(new Font("Arial", Font.BOLD, 12));
		nomeProjeto.setForeground(Color.DARK_GRAY);
		this.add(nomeProjeto);

		JLabel matricula = new JLabel("Matricula:");
		matricula.setBounds(50, 60, 300, 40);
		matricula.setFont(new Font("Arial", Font.BOLD, 12));
		matricula.setForeground(Color.DARK_GRAY);
		this.add(matricula);

		JLabel aporteCusteioReais = new JLabel("<html>Aporte custeio reais:</html>");
		aporteCusteioReais.setBounds(52, 125, 100, 40);
		aporteCusteioReais.setFont(new Font("Arial", Font.BOLD, 12));
		aporteCusteioReais.setForeground(Color.DARK_GRAY);
		this.add(aporteCusteioReais);

		JLabel aporteCapitalReais = new JLabel("<html>Aporte Capital Reais:</html>");
		aporteCapitalReais.setBounds(170, 125, 100, 40);
		aporteCapitalReais.setFont(new Font("Arial", Font.BOLD, 12));
		aporteCapitalReais.setForeground(Color.DARK_GRAY);
		this.add(aporteCapitalReais);

		JLabel gastoExecutadoCusteioReais = new JLabel("<html>Gasto Executado Custeio Reais:</html>");
		gastoExecutadoCusteioReais.setBounds(52, 185, 100, 40);
		gastoExecutadoCusteioReais.setFont(new Font("Arial", Font.BOLD, 12));
		gastoExecutadoCusteioReais.setForeground(Color.DARK_GRAY);
		this.add(gastoExecutadoCusteioReais);

		JLabel gastoExecutadoCapitalReais = new JLabel("<html>Gasto Executado Capital Reais:</html>");
		gastoExecutadoCapitalReais.setBounds(170, 185, 100, 40);
		gastoExecutadoCapitalReais.setFont(new Font("Arial", Font.BOLD, 12));
		gastoExecutadoCapitalReais.setForeground(Color.DARK_GRAY);
		this.add(gastoExecutadoCapitalReais);

		JLabel aporteCusteioMensalReais = new JLabel("<html>Aporte Custeio Mensal Reais:</html>");
		aporteCusteioMensalReais.setBounds(52, 245, 100, 40);
		aporteCusteioMensalReais.setFont(new Font("Arial", Font.BOLD, 12));
		aporteCusteioMensalReais.setForeground(Color.DARK_GRAY);
		this.add(aporteCusteioMensalReais);

		JLabel qtdMesesCusteados = new JLabel("<html>Quantidade Meses Custeados:</html>");
		qtdMesesCusteados.setBounds(170, 245, 120, 40);
		qtdMesesCusteados.setFont(new Font("Arial", Font.BOLD, 12));
		qtdMesesCusteados.setForeground(Color.DARK_GRAY);
		this.add(qtdMesesCusteados);

		JLabel qtdMesesPagos = new JLabel("<html>Quando Meses Pagos:</html>");
		qtdMesesPagos.setBounds(52, 305, 120, 40);
		qtdMesesPagos.setFont(new Font("Arial", Font.BOLD, 12));
		qtdMesesPagos.setForeground(Color.DARK_GRAY);
		this.add(qtdMesesPagos);

		JLabel atualizar = new JLabel("Atualizar Projeto");
		atualizar.setBounds(350, 20, 300, 40);
		atualizar.setFont(new Font("Arial", Font.BOLD, 30));
		atualizar.setForeground(Color.DARK_GRAY);
		this.add(atualizar);

		JLabel nomeProjetoAtualizar = new JLabel("Nome do projeto:");
		nomeProjetoAtualizar.setBounds(350, 60, 300, 40);
		nomeProjetoAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
		nomeProjetoAtualizar.setForeground(Color.DARK_GRAY);
		this.add(nomeProjetoAtualizar);

		JLabel aporteCusteioReaisAtualizar = new JLabel("<html>Aporte custeio reais:<html>");
		aporteCusteioReaisAtualizar.setBounds(480, 60, 80, 40);
		aporteCusteioReaisAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
		aporteCusteioReaisAtualizar.setForeground(Color.DARK_GRAY);
		this.add(aporteCusteioReaisAtualizar);

		JLabel aporteCapitalReaisAtualizar = new JLabel("<html>Aporte Capital Reais:</html>");
		aporteCapitalReaisAtualizar.setBounds(350, 125, 100, 40);
		aporteCapitalReaisAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
		aporteCapitalReaisAtualizar.setForeground(Color.DARK_GRAY);
		this.add(aporteCapitalReaisAtualizar);

		JLabel remover = new JLabel("Remover Projeto");
		remover.setBounds(350, 205, 300, 40);
		remover.setFont(new Font("Arial", Font.BOLD, 30));
		remover.setForeground(Color.DARK_GRAY);
		this.add(remover);

		JLabel nomeProjetoRmover = new JLabel("Nome do projeto:");
		nomeProjetoRmover.setBounds(350, 240, 300, 40);
		nomeProjetoRmover.setFont(new Font("Arial", Font.BOLD, 12));
		nomeProjetoRmover.setForeground(Color.DARK_GRAY);
		this.add(nomeProjetoRmover);

		JLabel removerP = new JLabel("<html>Remover Participação</html>");
		removerP.setBounds(650, 10, 300, 60);
		removerP.setFont(new Font("Arial", Font.BOLD, 30));
		removerP.setForeground(Color.DARK_GRAY);
		this.add(removerP);

		JLabel nomeProjetoP = new JLabel("Nome do projeto:");
		nomeProjetoP.setBounds(650, 60, 300, 50);
		nomeProjetoP.setFont(new Font("Arial", Font.BOLD, 12));
		nomeProjetoP.setForeground(Color.DARK_GRAY);
		this.add(nomeProjetoP);

		JLabel matricularR = new JLabel("<html>Matricula a excluir:<html>");
		matricularR.setBounds(650, 125, 80, 40);
		matricularR.setFont(new Font("Arial", Font.BOLD, 12));
		matricularR.setForeground(Color.DARK_GRAY);
		this.add(matricularR);

		JLabel matriculaCoordenadorR = new JLabel("<html>Matricula coordenador:</html>");
		matriculaCoordenadorR.setBounds(650, 200, 100, 40);
		matriculaCoordenadorR.setFont(new Font("Arial", Font.BOLD, 12));
		matriculaCoordenadorR.setForeground(Color.DARK_GRAY);
		this.add(matriculaCoordenadorR);

		JLabel adcionarP = new JLabel("<html>Adcionar Participação</html>");
		adcionarP.setBounds(350, 350, 300, 60);
		adcionarP.setFont(new Font("Arial", Font.BOLD, 30));
		adcionarP.setForeground(Color.DARK_GRAY);
		this.add(adcionarP);

		JLabel nomeProjetoAdcionar = new JLabel("Nome do projeto:");
		nomeProjetoAdcionar.setBounds(50, 420, 300, 50);
		nomeProjetoAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		nomeProjetoAdcionar.setForeground(Color.DARK_GRAY);
		this.add(nomeProjetoAdcionar);

		JLabel matriculaDoMembroQueQuerEstrarNoProjetoAdcionar = new JLabel("<html>Matricula a adcionar:<html>");
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setBounds(50, 500, 80, 40);
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setForeground(Color.DARK_GRAY);
		this.add(matriculaDoMembroQueQuerEstrarNoProjetoAdcionar);

		JLabel matriculaDoCordenadorAdcionar = new JLabel("<html>Matricula coordenador:</html>");
		matriculaDoCordenadorAdcionar.setBounds(50, 580, 100, 40);
		matriculaDoCordenadorAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		matriculaDoCordenadorAdcionar.setForeground(Color.DARK_GRAY);
		this.add(matriculaDoCordenadorAdcionar);

		JLabel dataInicioAdcionar = new JLabel("Data de inicio:");
		dataInicioAdcionar.setBounds(300, 420, 300, 50);
		dataInicioAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		dataInicioAdcionar.setForeground(Color.DARK_GRAY);
		this.add(dataInicioAdcionar);

		JLabel aporteCusteioMensalReaisAdcionar = new JLabel("<html>Aporte Custeio Mensal Reais:<html>");
		aporteCusteioMensalReaisAdcionar.setBounds(300, 500, 100, 40);
		aporteCusteioMensalReaisAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		aporteCusteioMensalReaisAdcionar.setForeground(Color.DARK_GRAY);
		this.add(aporteCusteioMensalReaisAdcionar);

		JLabel qtdMesesCusteadosAdcionar = new JLabel("<html>Quantidade Meses Custeados:</html>");
		qtdMesesCusteadosAdcionar.setBounds(300, 580, 130, 40);
		qtdMesesCusteadosAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		qtdMesesCusteadosAdcionar.setForeground(Color.DARK_GRAY);
		this.add(qtdMesesCusteadosAdcionar);

		JLabel qtdMesesPagosAdcionar = new JLabel("<html>Quantidade Meses Pago:</html>");
		qtdMesesPagosAdcionar.setBounds(550, 420, 100, 50);
		qtdMesesPagosAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		qtdMesesPagosAdcionar.setForeground(Color.DARK_GRAY);
		this.add(qtdMesesPagosAdcionar);

	}

	private void adcionarTextFields() {
		matriculaCoordenadorCriar = new JTextField();
		matriculaCoordenadorCriar.setToolTipText("ex: 123456...");
		matriculaCoordenadorCriar.setBounds(50, 100, 100, 25);
		this.add(matriculaCoordenadorCriar);

		nomeProjetoCriar = new JTextField();
		nomeProjetoCriar.setToolTipText("ex: Projeto Novo...");
		nomeProjetoCriar.setBounds(170, 100, 100, 25);
		this.add(nomeProjetoCriar);

		aporteCusteioReaisCriar = new JTextField();
		aporteCusteioReaisCriar.setToolTipText("ex: 123...");
		aporteCusteioReaisCriar.setBounds(50, 160, 100, 25);
		this.add(aporteCusteioReaisCriar);

		aporteCapitalReaisCriar = new JTextField();
		aporteCapitalReaisCriar.setToolTipText("ex: 123...");
		aporteCapitalReaisCriar.setBounds(170, 160, 100, 25);
		this.add(aporteCapitalReaisCriar);

		gastoExecutadoCusteioReaisCriar = new JTextField();
		gastoExecutadoCusteioReaisCriar.setToolTipText("ex: 123...");
		gastoExecutadoCusteioReaisCriar.setBounds(50, 220, 100, 25);
		this.add(gastoExecutadoCusteioReaisCriar);

		gastoExecutadoCapitalReaisCriar = new JTextField();
		gastoExecutadoCapitalReaisCriar.setToolTipText("ex: 123...");
		gastoExecutadoCapitalReaisCriar.setBounds(170, 220, 100, 25);
		this.add(gastoExecutadoCapitalReaisCriar);

		aporteCusteioMensalReaisCriar = new JTextField();
		aporteCusteioMensalReaisCriar.setToolTipText("ex: 123...");
		aporteCusteioMensalReaisCriar.setBounds(50, 280, 100, 25);
		this.add(aporteCusteioMensalReaisCriar);

		qtdMesesCusteadosCriar = new JTextField();
		qtdMesesCusteadosCriar.setToolTipText("ex: 123...");
		qtdMesesCusteadosCriar.setBounds(170, 280, 100, 25);
		this.add(qtdMesesCusteadosCriar);

		qtdMesesPagosCriar = new JTextField();
		qtdMesesPagosCriar.setToolTipText("ex: 123...");
		qtdMesesPagosCriar.setBounds(50, 340, 100, 25);
		this.add(qtdMesesPagosCriar);

		nomeProjetoAtualizar = new JTextField();
		nomeProjetoAtualizar.setToolTipText("ex: Projeto Novo...");
		nomeProjetoAtualizar.setBounds(350, 100, 100, 25);
		this.add(nomeProjetoAtualizar);

		aporteCusteioReaisNovo = new JTextField();
		aporteCusteioReaisNovo.setToolTipText("ex: 123...");
		aporteCusteioReaisNovo.setBounds(480, 100, 100, 25);
		this.add(aporteCusteioReaisNovo);

		aporteCapitalReaisNovo = new JTextField();
		aporteCapitalReaisNovo.setToolTipText("ex: 123...");
		aporteCapitalReaisNovo.setBounds(350, 160, 100, 25);
		this.add(aporteCapitalReaisNovo);

		nomeProjetoRemover = new JTextField();
		nomeProjetoRemover.setToolTipText("ex: Projeto Novo...");
		nomeProjetoRemover.setBounds(350, 280, 100, 25);
		this.add(nomeProjetoRemover);

		nomeProjetoR = new JTextField();
		nomeProjetoR.setToolTipText("ex: Nome Projeto...");
		nomeProjetoR.setBounds(650, 100, 150, 25);
		this.add(nomeProjetoR);

		MatriculaRemover = new JTextField();
		MatriculaRemover.setToolTipText("ex: 123...");
		MatriculaRemover.setBounds(650, 160, 150, 25);
		this.add(MatriculaRemover);

		matriculaCoordenador = new JTextField();
		matriculaCoordenador.setToolTipText("ex: 123...");
		matriculaCoordenador.setBounds(650, 240, 150, 25);
		this.add(matriculaCoordenador);

		nomeDoProjetoAdcionar = new JTextField();
		nomeDoProjetoAdcionar.setToolTipText("ex: Nome Projeto...");
		nomeDoProjetoAdcionar.setBounds(50, 460, 200, 25);
		this.add(nomeDoProjetoAdcionar);

		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar = new JTextField();
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setToolTipText("ex: 123...");
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setBounds(50, 540, 200, 25);
		this.add(matriculaDoMembroQueQuerEstrarNoProjetoAdcionar);

		matriculaDoCordenadorAdcionar = new JTextField();
		matriculaDoCordenadorAdcionar.setToolTipText("ex: 123...");
		matriculaDoCordenadorAdcionar.setBounds(50, 620, 200, 25);
		this.add(matriculaDoCordenadorAdcionar);

		nomeDoProjetoAdcionar = new JTextField();
		nomeDoProjetoAdcionar.setToolTipText("ex: Nome Projeto...");
		nomeDoProjetoAdcionar.setBounds(50, 460, 200, 25);
		this.add(nomeDoProjetoAdcionar);

		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar = new JTextField();
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setToolTipText("ex: 123...");
		matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.setBounds(50, 540, 200, 25);
		this.add(matriculaDoMembroQueQuerEstrarNoProjetoAdcionar);

		matriculaDoCordenadorAdcionar = new JTextField();
		matriculaDoCordenadorAdcionar.setToolTipText("ex: 123...");
		matriculaDoCordenadorAdcionar.setBounds(50, 620, 200, 25);
		this.add(matriculaDoCordenadorAdcionar);

		dataInicioAdcionar = new JTextField();
		dataInicioAdcionar.setToolTipText("ex: Nome Projeto...");
		dataInicioAdcionar.setBounds(300, 460, 200, 25);
		this.add(dataInicioAdcionar);

		aporteCusteioMensalReaisAdcionar = new JTextField();
		aporteCusteioMensalReaisAdcionar.setToolTipText("ex: 123...");
		aporteCusteioMensalReaisAdcionar.setBounds(300, 540, 200, 25);
		this.add(aporteCusteioMensalReaisAdcionar);

		qtdMesesCusteadosMensalReaisAdcionar = new JTextField();
		qtdMesesCusteadosMensalReaisAdcionar.setToolTipText("ex: 123...");
		qtdMesesCusteadosMensalReaisAdcionar.setBounds(300, 620, 200, 25);
		this.add(qtdMesesCusteadosMensalReaisAdcionar);

		qtdMesesPagosAdcionar = new JTextField();
		qtdMesesPagosAdcionar.setToolTipText("ex: 123...");
		qtdMesesPagosAdcionar.setBounds(550, 460, 200, 25);
		this.add(qtdMesesPagosAdcionar);
	}

	private void adcionarBotao() {
		JButton criar = new JButton("Criar");
		criar.setForeground(Color.WHITE);
		criar.setBackground(new Color(119, 221, 119));
		criar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					criarProjeto(nomeProjetoCriar.getText(), Float.parseFloat(aporteCusteioReaisCriar.getText()),
							Float.parseFloat(aporteCapitalReaisCriar.getText()),
							Float.parseFloat(gastoExecutadoCusteioReaisCriar.getText()),
							Float.parseFloat(gastoExecutadoCapitalReaisCriar.getText()),
							Long.parseLong(matriculaCoordenadorCriar.getText()),
							Float.parseFloat(aporteCusteioMensalReaisCriar.getText()),
							Short.parseShort(qtdMesesCusteadosCriar.getText()),
							Short.parseShort(qtdMesesPagosCriar.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		criar.setBounds(170, 335, 100, 30);
		this.add(criar);

		JButton atualizar = new JButton("Atualizar");
		atualizar.setForeground(Color.WHITE);
		atualizar.setBackground(new Color(119, 221, 119));
		atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					atualizarProjeto(nomeProjetoAtualizar.getText(), Float.parseFloat(aporteCusteioReaisNovo.getText()),
							Float.parseFloat(aporteCapitalReaisNovo.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		atualizar.setBounds(480, 155, 100, 30);
		this.add(atualizar);

		JButton remover = new JButton("Remover");
		remover.setForeground(Color.WHITE);
		remover.setBackground(new Color(119, 221, 119));
		remover.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					removerProjeto(nomeProjetoRemover.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		remover.setBounds(480, 275, 100, 30);
		this.add(remover);

		JButton removerParticipacao = new JButton("Remover");
		removerParticipacao.setForeground(Color.WHITE);
		removerParticipacao.setBackground(new Color(119, 221, 119));
		removerParticipacao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					removerParticipacao(Long.parseLong(matriculaCoordenador.getText()),
							Long.parseLong(MatriculaRemover.getText()), nomeDoProjetoAdcionar.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		removerParticipacao.setBounds(700, 275, 100, 30);
		this.add(removerParticipacao);

		JButton adcionar = new JButton("Adcionar");
		adcionar.setForeground(Color.WHITE);
		adcionar.setBackground(new Color(119, 221, 119));
		adcionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					adicionarParticipacao(Long.parseLong(matriculaDoCordenadorAdcionar.getText()),
							Long.parseLong(matriculaDoMembroQueQuerEstrarNoProjetoAdcionar.getText()),
							nomeDoProjetoAdcionar.getText(), dataInicioAdcionar.getText(),
							Float.parseFloat(aporteCusteioMensalReaisAdcionar.getText()),
							Short.parseShort(qtdMesesCusteadosMensalReaisAdcionar.getText()),
							Short.parseShort(qtdMesesPagosAdcionar.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		adcionar.setBounds(650, 530, 100, 30);
		this.add(adcionar);

		JButton mostrarProjetos = new JButton("Projetos");
		mostrarProjetos.setForeground(Color.WHITE);
		mostrarProjetos.setBackground(new Color(119, 221, 119));
		mostrarProjetos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mostrarProjetos.setBounds(650, 610, 100, 30);
		this.add(mostrarProjetos);
	}

	public static void main(String[] args) {
		JFrame p = new JFrame();
		p.add(new TelaCadastroProjetos());
		p.setSize(1000, 700);
		p.setVisible(true);
	}

}
