package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.controller.ControllerCadastroEditais;

public class TelaCadastroEditais extends JPanel implements InterfaceTelaCadastroEditais {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControllerCadastroEditais controller = new ControllerCadastroEditais();

	private JTextField matriculaCriar;
	private JTextField nomeEditalCriar;
	private JTextField dataInicio;
	private JTextField dataTermino;

	private JTextField matriculaRemover;
	private JTextField nomeEditalRemover;

	private JTextField matriculaAtualizar;
	private JTextField antigoNomeEdital;
	private JTextField novoNomeEdital;
	private JTextField novaDataInicio;
	private JTextField novaDataTermino;
	private JComboBox<Object> op = new JComboBox<Object>();

	public TelaCadastroEditais() {

		setBounds(150, 0, 850, 700);
		setBackground(new Color(213, 213, 213));
		setLayout(null);
		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		adcionarJComobox();
		setVisible(true);
	}

	// String nomeEdital, Date dataInicio, Date dataTermino, long matricula
	private void adcionarLabels() {
		JLabel cadastrar = new JLabel("Cadastrar Edital");
		cadastrar.setBounds(50, 20, 300, 40);
		cadastrar.setFont(new Font("Arial", Font.BOLD, 30));
		cadastrar.setForeground(Color.DARK_GRAY);
		this.add(cadastrar);

		JLabel matricula = new JLabel("Matricula:");
		matricula.setBounds(50, 60, 300, 40);
		matricula.setFont(new Font("Arial", Font.BOLD, 14));
		matricula.setForeground(Color.DARK_GRAY);
		this.add(matricula);

		JLabel nomeEdital = new JLabel("Nome do Edital:");
		nomeEdital.setBounds(50, 150, 300, 40);
		nomeEdital.setFont(new Font("Arial", Font.BOLD, 14));
		nomeEdital.setForeground(Color.DARK_GRAY);
		this.add(nomeEdital);

		JLabel dataInicio = new JLabel("Data de Inicio:");
		dataInicio.setBounds(52, 240, 300, 40);
		dataInicio.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicio.setForeground(Color.DARK_GRAY);
		this.add(dataInicio);

		JLabel dataTermino = new JLabel("Data de Inicio:");
		dataTermino.setBounds(204, 240, 300, 40);
		dataTermino.setFont(new Font("Arial", Font.BOLD, 14));
		dataTermino.setForeground(Color.DARK_GRAY);
		this.add(dataTermino);

		// =------------------------------------------------------
		// String nomeEdital, long matricula
		JLabel remover = new JLabel("Remover Edital");
		remover.setBounds(500, 20, 300, 40);
		remover.setFont(new Font("Arial", Font.BOLD, 30));
		remover.setForeground(Color.DARK_GRAY);
		this.add(remover);

		JLabel matriculaRemover = new JLabel("Matricula:");
		matriculaRemover.setBounds(500, 60, 300, 40);
		matriculaRemover.setFont(new Font("Arial", Font.BOLD, 14));
		matriculaRemover.setForeground(Color.DARK_GRAY);
		this.add(matriculaRemover);

		JLabel nomeEditalRemover = new JLabel("Nome do Edital:");
		nomeEditalRemover.setBounds(500, 150, 300, 40);
		nomeEditalRemover.setFont(new Font("Arial", Font.BOLD, 14));
		nomeEditalRemover.setForeground(Color.DARK_GRAY);
		this.add(nomeEditalRemover);
		// --------------------------------------------------------
		// String nomeEdital, String novoNome, Date dataInicio, Date dataTermino, long
		// matricula
		JLabel atualizar = new JLabel("Atualizar Edital");
		atualizar.setBounds(300, 400, 300, 40);
		atualizar.setFont(new Font("Arial", Font.BOLD, 30));
		atualizar.setForeground(Color.DARK_GRAY);
		this.add(atualizar);

		JLabel matriculaAtualizar = new JLabel("Matricula:");
		matriculaAtualizar.setBounds(100, 440, 300, 40);
		matriculaAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		matriculaAtualizar.setForeground(Color.DARK_GRAY);
		this.add(matriculaAtualizar);

		JLabel dataInicioAtualizar = new JLabel("Nova Data de Inicio:");
		dataInicioAtualizar.setBounds(100, 530, 300, 40);
		dataInicioAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioAtualizar.setForeground(Color.DARK_GRAY);
		this.add(dataInicioAtualizar);

		JLabel nomeNovo = new JLabel("Nome:");
		nomeNovo.setBounds(350, 440, 300, 40);
		nomeNovo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeNovo.setForeground(Color.DARK_GRAY);
		this.add(nomeNovo);

		JLabel nomeAntigo = new JLabel("Novo nome:");
		nomeAntigo.setBounds(600, 440, 300, 40);
		nomeAntigo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeAntigo.setForeground(Color.DARK_GRAY);
		this.add(nomeAntigo);

		JLabel dataDeterminoAtualizar = new JLabel("Nova Data de Termino:");
		dataDeterminoAtualizar.setBounds(350, 530, 300, 40);
		dataDeterminoAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		dataDeterminoAtualizar.setForeground(Color.DARK_GRAY);
		this.add(dataDeterminoAtualizar);
	}

	private void adcionarTextFields() {
		matriculaCriar = new JTextField();
		matriculaCriar.setToolTipText("ex: 123456...");
		matriculaCriar.setBounds(50, 100, 200, 25);
		this.add(matriculaCriar);

		nomeEditalCriar = new JTextField();
		nomeEditalCriar.setToolTipText("ex: Fulano...");
		nomeEditalCriar.setBounds(50, 190, 200, 25);
		this.add(nomeEditalCriar);

		dataInicio = new JTextField();
		dataInicio.setToolTipText("ex: 00/00/0000...");
		dataInicio.setBounds(52, 280, 100, 25);
		this.add(dataInicio);

		dataTermino = new JTextField();
		dataTermino.setToolTipText("ex: 00/00/0000...");
		dataTermino.setBounds(204, 280, 100, 25);
		this.add(dataTermino);
		// ---------------------------------------------------------
		matriculaRemover = new JTextField();
		matriculaRemover.setToolTipText("ex: 123456...");
		matriculaRemover.setBounds(500, 100, 200, 25);
		this.add(matriculaRemover);

		nomeEditalRemover = new JTextField();
		nomeEditalRemover.setToolTipText("ex: 123456...");
		nomeEditalRemover.setBounds(500, 190, 200, 25);
		this.add(nomeEditalRemover);
		// -----------------------------------------------------------
		matriculaAtualizar = new JTextField();
		matriculaAtualizar.setToolTipText("ex: 123456...");
		matriculaAtualizar.setBounds(100, 480, 200, 25);
		this.add(matriculaAtualizar);

		novaDataInicio = new JTextField();
		novaDataInicio.setToolTipText("ex: 123456...");
		novaDataInicio.setBounds(100, 570, 200, 25);
		novaDataInicio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object[] dados = controller.recuperarEdital(novaDataInicio.getText());
				novoNomeEdital.setText((String) dados[0]);
				dataTermino.setText((String) dados[1]);
				novoNomeEdital.repaint();
				dataTermino.repaint();
			}
		});
		this.add(novaDataInicio);

		antigoNomeEdital = new JTextField();
		antigoNomeEdital.setToolTipText("ex: Fulano...");
		antigoNomeEdital.setBounds(350, 480, 200, 25);
		this.add(antigoNomeEdital);

		novaDataTermino = new JTextField();
		novaDataTermino.setToolTipText("ex: 00/00/0000...");
		novaDataTermino.setBounds(350, 570, 200, 25);
		this.add(novaDataTermino);

		novoNomeEdital = new JTextField();
		novoNomeEdital.setToolTipText("ex: Fulano...");
		novoNomeEdital.setBounds(600, 480, 200, 25);
		this.add(novoNomeEdital);

	}

	private void adcionarBotao() {
		JButton criar = new JButton("Criar");
		criar.setForeground(Color.WHITE);
		criar.setBackground(new Color(119, 221, 119));
		criar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
					Date dateInicioFormatado = (Date) formatter.parse(dataInicio.getText());
					Date dateTerminoFormatado = (Date) formatter.parse(dataTermino.getText());

					adcionarEdital(nomeEditalCriar.getText(), dateInicioFormatado, dateTerminoFormatado,
							Long.parseLong(matriculaCriar.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		criar.setBounds(280, 180, 100, 40);
		this.add(criar);

		JButton remover = new JButton("Remover");
		remover.setForeground(Color.WHITE);
		remover.setBackground(new Color(119, 221, 119));
		remover.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					removerEdital(nomeEditalRemover.getText(), Long.parseLong(matriculaRemover.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		remover.setBounds(500, 265, 100, 40);
		this.add(remover);

		JButton atualizar = new JButton("Atualizar");
		atualizar.setForeground(Color.WHITE);
		atualizar.setBackground(new Color(119, 221, 119));
		atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
					Date dateInicioFormatado = (Date) formatter.parse(novaDataInicio.getText());
					Date dateTerminoFormatado = (Date) formatter.parse(novaDataTermino.getText());
					atualizarEdital(antigoNomeEdital.getText(), novoNomeEdital.getText(), dateInicioFormatado,
							dateTerminoFormatado, Long.parseLong(matriculaAtualizar.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		atualizar.setBounds(600, 550, 100, 40);
		this.add(atualizar);

		JButton mostrarEditais = new JButton("<html>Criar Relatorio</html>");
		mostrarEditais.setForeground(Color.WHITE);
		mostrarEditais.setBackground(new Color(119, 221, 119));
		mostrarEditais.setFont(new Font("Arial", Font.BOLD, 9));
		mostrarEditais.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String valor = (String) JOptionPane.showInputDialog(null, "Escolha o tipo de relatorio",
							"Gerar relatorio", JOptionPane.PLAIN_MESSAGE, null, new Object[] { "HTML", "JPAINEL" },
							null);
					DiretorDeMontagemDeRelatorio diretor = new DiretorDeMontagemDeRelatorio(
							new MontadorRelatorioSwing());
					if (valor.equals("HTML")) {
						diretor.setMontadorDeRelatorio(new MontadorRelatorioProjetoHTML(""));
					}
					diretor.montarRelatorioCompleto(op.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Relatorio criado!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Não foi possivel criar o realtorio!");
					e1.printStackTrace();
				}
			}
		});
		mostrarEditais.setBounds(710, 550, 90, 40);
		this.add(mostrarEditais);
	}

	private void adcionarJComobox() {
		Object[] editais = mostrarEditaisDoUsuarioLogado();
		String[] nome = { "---Nenhum edital cadastrado---" };
		if (editais.length == 0 || editais == null) {
			op.addItem(nome[0].toString());
		} else {
			for (Object object : editais) {
				op.addItem(object.toString());
			}
		}
		op.setBounds(600, 520, 200, 20);
		add(op);
	}

	public void adcionarEdital(String nomeEdital, Date dataInicio, Date dataTermino, long matricula) throws Exception {
		controller.adcionarEdital(nomeEdital, dataInicio, dataTermino, matricula);

	}

	public void atualizarEdital(String nomeEdital, String novoNome, Date dataInicio, Date dataTermino, long matricula)
			throws Exception {
		controller.atualizarEdital(nomeEdital, novoNome, dataInicio, dataTermino, matricula);

	}

	public void removerEdital(String nomeEdital, long matricula) throws Exception {
		controller.removerEdital(nomeEdital, matricula);

	}

	public Object[] mostrarEditaisDoUsuarioLogado() {
		try {
			return controller.mostrarEditaisDoUsuarioLogado().toArray();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}

	public Object[] recuperarEdital(String nome) {
		return controller.recuperarEdital(nome);
	}

	public static void main(String[] args) {
		JFrame p = new JFrame();
		p.add(new TelaCadastroEditais());
		p.setSize(1000, 700);
		p.setVisible(true);
	}

}
