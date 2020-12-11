package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import view.abstract_factory.InterfaceTelaCadastroEditais;
import view.controller.ControllerCadastroEditais;
import view.controller.ControllerGerarRelatorio;

public class TelaCadastroEditaisSwing extends JPanel implements InterfaceTelaCadastroEditais {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerGerarRelatorio geradorRelatorio = new ControllerGerarRelatorio();
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
	private JComboBox<Object> op2 = new JComboBox<Object>();
	private JComboBox<Object> opGrupos = new JComboBox<Object>();
	private JComboBox<Object> opProjetos = new JComboBox<Object>();

	public TelaCadastroEditaisSwing() {

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

		JLabel dataTermino = new JLabel("Data de Termino:");
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
		atualizar.setBounds(200, 400, 300, 40);
		atualizar.setFont(new Font("Arial", Font.BOLD, 30));
		atualizar.setForeground(Color.DARK_GRAY);
		this.add(atualizar);

		JLabel matriculaAtualizar = new JLabel("Matricula:");
		matriculaAtualizar.setBounds(50, 440, 100, 40);
		matriculaAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		matriculaAtualizar.setForeground(Color.DARK_GRAY);
		this.add(matriculaAtualizar);

		JLabel dataInicioAtualizar = new JLabel("<html>Nova Data de Inicio:</html>");
		dataInicioAtualizar.setBounds(50, 530, 100, 40);
		dataInicioAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioAtualizar.setForeground(Color.DARK_GRAY);
		this.add(dataInicioAtualizar);

		JLabel nomeNovo = new JLabel("Nome:");
		nomeNovo.setBounds(200, 440, 100, 40);
		nomeNovo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeNovo.setForeground(Color.DARK_GRAY);
		this.add(nomeNovo);

		JLabel nomeAntigo = new JLabel("Novo nome:");
		nomeAntigo.setBounds(350, 440, 100, 40);
		nomeAntigo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeAntigo.setForeground(Color.DARK_GRAY);
		this.add(nomeAntigo);

		JLabel dataDeterminoAtualizar = new JLabel("<html>Nova Data de Termino:</html>");
		dataDeterminoAtualizar.setBounds(200, 530, 100, 40);
		dataDeterminoAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		dataDeterminoAtualizar.setForeground(Color.DARK_GRAY);
		this.add(dataDeterminoAtualizar);
		
		JLabel adcionarRemover = new JLabel("<html>Adcionar ou Remover\nGrupo ou Projeto</html>");
		adcionarRemover.setBounds(600, 400, 250, 50);
		adcionarRemover.setFont(new Font("Arial", Font.BOLD, 20));
		adcionarRemover.setForeground(Color.DARK_GRAY);
		this.add(adcionarRemover);
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

		try {
			MaskFormatter macaraDeData = new MaskFormatter("##/##/####");
			dataInicio = new JFormattedTextField(macaraDeData);
			dataTermino = new JFormattedTextField(macaraDeData);
			novaDataInicio = new JFormattedTextField(macaraDeData);
			novaDataTermino = new JFormattedTextField(macaraDeData);

		} catch (ParseException e) {
		}
		dataInicio.setToolTipText("ex: 00/00/0000...");
		dataInicio.setBounds(52, 280, 100, 25);
		this.add(dataInicio);
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
		matriculaAtualizar.setBounds(50, 480, 100, 25);
		this.add(matriculaAtualizar);

		novaDataInicio.setToolTipText("ex: 123456...");
		novaDataInicio.setBounds(50, 570, 100, 25);
		this.add(novaDataInicio);

		antigoNomeEdital = new JTextField();
		antigoNomeEdital.setToolTipText("ex: Fulano...");
		antigoNomeEdital.setBounds(200, 480, 100, 25);
		antigoNomeEdital.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				Object[] dados = controller.recuperarEdital(antigoNomeEdital.getText());
				novoNomeEdital.setText((String) dados[0]);
				novaDataInicio.setText("01/12/2020");
				novaDataTermino.setText("01/12/2021");
				novoNomeEdital.repaint();
				novaDataInicio.repaint();
				novaDataTermino.repaint();
			}

			public void keyPressed(KeyEvent e) {

			}
		});
		this.add(antigoNomeEdital);

		novaDataTermino.setToolTipText("ex: 00/00/0000...");
		novaDataTermino.setBounds(200, 570, 100, 25);
		this.add(novaDataTermino);

		novoNomeEdital = new JTextField();
		novoNomeEdital.setToolTipText("ex: Fulano...");
		novoNomeEdital.setBounds(350, 480, 200, 25);
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
					adcionarJComobox();
					JOptionPane.showMessageDialog(null, "Edital adcionado!");
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
					JOptionPane.showMessageDialog(null, "Edital removido!");
					adcionarJComobox();
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
					JOptionPane.showMessageDialog(null, "Edital atualizado!");
					adcionarJComobox();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		atualizar.setBounds(350, 560, 100, 40);
		this.add(atualizar);

		JButton mostrarEditais = new JButton("<html>Criar Relatorio</html>");
		mostrarEditais.setForeground(Color.WHITE);
		mostrarEditais.setBackground(new Color(119, 221, 119));
		mostrarEditais.setFont(new Font("Arial", Font.BOLD, 9));
		mostrarEditais.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String valor = (String) JOptionPane.showInputDialog(null, "Escolha o tipo de relatorio", "Gerar relatorio",
							JOptionPane.PLAIN_MESSAGE, null, new Object[] { "HTML", "JPAINEL" }, null);
					geradorRelatorio.gerarRelatorio(op.getSelectedItem().toString(), valor);
					JOptionPane.showMessageDialog(null, "Relatorio criado!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Não foi possivel criar o realtorio!");
					e1.printStackTrace();
				}
			}
		});
		mostrarEditais.setBounds(460, 560, 90, 40);
		this.add(mostrarEditais);
		
		JButton adcionarGrupo = new JButton("<html>Adcionar Grupo</html>");
		adcionarGrupo.setForeground(Color.WHITE);
		adcionarGrupo.setBackground(new Color(119, 221, 119));
		adcionarGrupo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.adcionarGrupo((String) opGrupos.getSelectedItem(), (String) op2.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Grupo adcionado!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Grupo não adcionado!");
					e1.printStackTrace();
				}
			}
		});
		adcionarGrupo.setFont(new Font("Arial", Font.BOLD, 9));
		adcionarGrupo.setBounds(600, 550, 90, 25);
		this.add(adcionarGrupo);

		JButton removerGrupo = new JButton("<html>Remover Grupo</html>");
		removerGrupo.setForeground(Color.WHITE);
		removerGrupo.setBackground(new Color(119, 221, 119));
		removerGrupo.setFont(new Font("Arial", Font.BOLD, 9));
		removerGrupo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.removerGrupo((String) opGrupos.getSelectedItem(), (String) op2.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Grupo Removido!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Grupo não Removido!");
					e1.printStackTrace();
				}
			}
		});
		removerGrupo.setBounds(600, 580, 90, 25);
		this.add(removerGrupo);
		JButton adcionarProjeto = new JButton("<html>Adcionar projeto</html>");
		adcionarProjeto.setForeground(Color.WHITE);
		adcionarProjeto.setBackground(new Color(119, 221, 119));
		adcionarProjeto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.adcionarProjeto((String) opProjetos.getSelectedItem(), (String) op2.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Projeto adcionado!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Projeto não adcionado!");
					e1.printStackTrace();
				}
			}
		});
		adcionarProjeto.setFont(new Font("Arial", Font.BOLD, 9));
		adcionarProjeto.setBounds(710, 550, 90, 25);
		this.add(adcionarProjeto);

		JButton removerProjeto = new JButton("<html>Remover projeto</html>");
		removerProjeto.setForeground(Color.WHITE);
		removerProjeto.setBackground(new Color(119, 221, 119));
		removerProjeto.setFont(new Font("Arial", Font.BOLD, 9));
		removerProjeto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.removerProjeto((String) opProjetos.getSelectedItem(), (String) op2.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Projeto Removido!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Projeto não Removido!");
					e1.printStackTrace();
				}
			}
		});
		removerProjeto.setBounds(710, 580, 90, 25);
		this.add(removerProjeto);
	}

	private void adcionarJComobox() {
		Object[] editais = mostrarEditaisDoUsuarioLogado();
		Object[] projetos = mostrarProjetosDoUsuarioLogado();
		Object[] grupos = mostrarGruposDoUsuarioLogado();
		String[] textoEdital = { "---Nenhum edital cadastrado---" };
		String[] textoProjetos = { "---Nenhum projetos cadastrado---" };
		String[] textoGrupos = { "---Nenhum grupos cadastrado---" };
		if (editais.length == 0 || editais == null) {
			op.removeAllItems();
			op.addItem(textoEdital[0].toString());
			op2.removeAllItems();
			op2.addItem(textoEdital[0].toString());
		} else {
			op.removeAllItems();
			op2.removeAllItems();
			for (Object object : editais) {
				op.addItem(object.toString());
				op2.addItem(object.toString());
			}
		}
		op.setBounds(350, 520, 200, 20);
		add(op);
		op2.setBounds(600, 480, 200, 20);
		add(op2);
		if (grupos.length == 0 || grupos == null) {
			opGrupos.addItem(textoGrupos[0].toString());		
		} else {
			opGrupos.removeAllItems();
			for (Object object : grupos) {
				opGrupos.addItem(object.toString());
			}
		}
		opGrupos.setBounds(600, 520, 100, 20);
		add(opGrupos);
		if (projetos.length == 0 || projetos == null) {
			opProjetos.addItem(textoProjetos[0].toString());		
		} else {
			opProjetos.removeAllItems();
			for (Object object : projetos) {
				opProjetos.addItem(object.toString());
			}
		}
		opProjetos.setBounds(700, 520, 100, 20);
		add(opProjetos);
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
	public Object[] mostrarProjetosDoUsuarioLogado() {
		try {
			return controller.mostrarProjetosDoUsuarioLogado().toArray();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}
	public Object[] mostrarGruposDoUsuarioLogado() {
		try {
			return controller.mostrarGruposDoUsuarioLogado().toArray();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}

	public Object[] recuperarEdital(String nome) {
		return controller.recuperarEdital(nome);
	}
}
