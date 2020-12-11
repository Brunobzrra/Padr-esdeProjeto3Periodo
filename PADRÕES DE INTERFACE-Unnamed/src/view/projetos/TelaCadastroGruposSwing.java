package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.abstract_factory.InterfaceTelaCadastroGrupos;
import view.controller.ControllerCadastroGrupos;
import view.controller.ControllerGerarRelatorio;

public class TelaCadastroGruposSwing extends JPanel implements InterfaceTelaCadastroGrupos {
	private ControllerGerarRelatorio geradorRelatorio = new ControllerGerarRelatorio();

	private ControllerCadastroGrupos controller = new ControllerCadastroGrupos();
	private JTextField matriculaCriar;
	private JTextField nomeGrupoCriar;
	private JTextField linkCNPqCriar;
	private JTextField matriculaRemover;
	private JTextField linkCNPqRemover;
	private JTextField matriculaAntigo;
	private JTextField linkCNPqAntigo;
	private JTextField nomeGrupoNovo;
	private JTextField linkCNPqNovo;
	private JTextField matriculaAdcionar;
	private JComboBox<Object> op = new JComboBox<Object>();
	private JComboBox<Object> op2 = new JComboBox<Object>();
	private JComboBox<Object> opProjetos = new JComboBox<Object>();
	public TelaCadastroGruposSwing() {
		setBounds(150, 0, 850, 700);
		setBackground(new Color(213, 213, 213));
		setLayout(null);
		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		adcionarJComobox();
		setVisible(true);
	}

	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception {
		controller.adcionarGrupo(nome, linkCNPq, matricula);

	}

	public void removerGrupo(long matricula, String linkCNPq) throws Exception {
		controller.removerGrupo(matricula, linkCNPq);

	}

	public void atualizarGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) throws Exception {
		controller.atualizarGrupo(matricula, linkCNPq, nomeNovo, linkCNPqNovo);

	}

	public Object[] mostrarGruposDoUsuarioLogado() {
		try {
			return controller.mostrarGruposDoUsuarioLogado().toArray();
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Object[] mostrarProjetosDoUsuarioLogado() {
		try {
			return controller.mostrarProjetosDoUsuarioLogado().toArray();
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Object[] recuperarGrupo(String linkCNPq) {
		return controller.recuperarGrupo(linkCNPq);
	}

	private void adcionarLabels() {
		JLabel cadastrar = new JLabel("Cadastrar grupo");
		cadastrar.setBounds(50, 20, 300, 40);
		cadastrar.setFont(new Font("Arial", Font.BOLD, 30));
		cadastrar.setForeground(Color.DARK_GRAY);
		this.add(cadastrar);

		JLabel nomeGrupo = new JLabel("Nome do grupo:");
		nomeGrupo.setBounds(50, 150, 300, 40);
		nomeGrupo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeGrupo.setForeground(Color.DARK_GRAY);
		this.add(nomeGrupo);

		JLabel linkCNPq = new JLabel("Link CNPq:");
		linkCNPq.setBounds(52, 240, 300, 40);
		linkCNPq.setFont(new Font("Arial", Font.BOLD, 14));
		linkCNPq.setForeground(Color.DARK_GRAY);
		this.add(linkCNPq);

		JLabel matricula = new JLabel("Matricula:");
		matricula.setBounds(50, 60, 300, 40);
		matricula.setFont(new Font("Arial", Font.BOLD, 14));
		matricula.setForeground(Color.DARK_GRAY);
		this.add(matricula);

		JLabel remover = new JLabel("Remover grupo");
		remover.setBounds(500, 20, 300, 40);
		remover.setFont(new Font("Arial", Font.BOLD, 30));
		remover.setForeground(Color.DARK_GRAY);
		this.add(remover);

		JLabel matriculaRemover = new JLabel("Matricula:");
		matriculaRemover.setBounds(500, 60, 300, 40);
		matriculaRemover.setFont(new Font("Arial", Font.BOLD, 14));
		matriculaRemover.setForeground(Color.DARK_GRAY);
		this.add(matriculaRemover);

		JLabel linkCNPqRemover = new JLabel("Link CNPq:");
		linkCNPqRemover.setBounds(500, 150, 300, 40);
		linkCNPqRemover.setFont(new Font("Arial", Font.BOLD, 14));
		linkCNPqRemover.setForeground(Color.DARK_GRAY);
		this.add(linkCNPqRemover);

		JLabel atualizar = new JLabel("Atualizar grupo");
		atualizar.setBounds(50, 400, 300, 40);
		atualizar.setFont(new Font("Arial", Font.BOLD, 30));
		atualizar.setForeground(Color.DARK_GRAY);
		this.add(atualizar);

		JLabel matriculaAtualizar = new JLabel("Matricula:");
		matriculaAtualizar.setBounds(50, 440, 300, 40);
		matriculaAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		matriculaAtualizar.setForeground(Color.DARK_GRAY);
		this.add(matriculaAtualizar);

		JLabel linkCNPqAntigo = new JLabel("Link CNPq:");
		linkCNPqAntigo.setBounds(50, 530, 300, 40);
		linkCNPqAntigo.setFont(new Font("Arial", Font.BOLD, 14));
		linkCNPqAntigo.setForeground(Color.DARK_GRAY);
		this.add(linkCNPqAntigo);

		JLabel nomeNovo = new JLabel("Novo nome:");
		nomeNovo.setBounds(200, 440, 300, 40);
		nomeNovo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeNovo.setForeground(Color.DARK_GRAY);
		this.add(nomeNovo);

		JLabel linkCNPqNovo = new JLabel("Novo link CNPq:");
		linkCNPqNovo.setBounds(200, 530, 300, 40);
		linkCNPqNovo.setFont(new Font("Arial", Font.BOLD, 14));
		linkCNPqNovo.setForeground(Color.DARK_GRAY);
		this.add(linkCNPqNovo);
		
		JLabel adcionarRemover = new JLabel("<html>Adcionar ou Remover\nMembro ou Projeto</html>");
		adcionarRemover.setBounds(500, 400, 250, 50);
		adcionarRemover.setFont(new Font("Arial", Font.BOLD, 20));
		adcionarRemover.setForeground(Color.DARK_GRAY);
		this.add(adcionarRemover);
		
		JLabel matriculaAdcionar = new JLabel("<html>Matricula</html>");
		matriculaAdcionar.setBounds(650, 495, 250, 50);
		matriculaAdcionar.setFont(new Font("Arial", Font.BOLD, 12));
		matriculaAdcionar.setForeground(Color.DARK_GRAY);
		this.add(matriculaAdcionar);	
	}

	private void adcionarTextFields() {
		matriculaCriar = new JTextField();
		matriculaCriar.setToolTipText("ex: 123456...");
		matriculaCriar.setBounds(50, 100, 200, 25);
		this.add(matriculaCriar);

		nomeGrupoCriar = new JTextField();
		nomeGrupoCriar.setToolTipText("ex: Fulano...");
		nomeGrupoCriar.setBounds(50, 190, 200, 25);
		this.add(nomeGrupoCriar);

		linkCNPqCriar = new JTextField();
		linkCNPqCriar.setToolTipText("ex: 123456...");
		linkCNPqCriar.setBounds(50, 280, 200, 25);
		this.add(linkCNPqCriar);

		matriculaRemover = new JTextField();
		matriculaRemover.setToolTipText("ex: 123456...");
		matriculaRemover.setBounds(500, 100, 200, 25);
		this.add(matriculaRemover);

		linkCNPqRemover = new JTextField();
		linkCNPqRemover.setToolTipText("ex: 123456...");
		linkCNPqRemover.setBounds(500, 190, 200, 25);
		this.add(linkCNPqRemover);

		matriculaAntigo = new JTextField();
		matriculaAntigo.setToolTipText("ex: 123456...");
		matriculaAntigo.setBounds(50, 480, 100, 25);
		this.add(matriculaAntigo);

		linkCNPqAntigo = new JTextField();
		linkCNPqAntigo.setToolTipText("ex: 123456...");
		linkCNPqAntigo.setBounds(50, 570, 100, 25);
		linkCNPqAntigo.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				Object[] dados = controller.recuperarGrupo(linkCNPqAntigo.getText());
				nomeGrupoNovo.setText((String) dados[0]);
				linkCNPqNovo.setText((String) dados[1]);
				nomeGrupoNovo.repaint();
				linkCNPqNovo.repaint();				
			}
			
			public void keyPressed(KeyEvent e) {
				
			}
		});
		this.add(linkCNPqAntigo);

		nomeGrupoNovo = new JTextField();
		nomeGrupoNovo.setToolTipText("ex: Fulano...");
		nomeGrupoNovo.setBounds(200, 480, 100, 25);
		this.add(nomeGrupoNovo);

		linkCNPqNovo = new JTextField();
		linkCNPqNovo.setToolTipText("ex: 123456...");
		linkCNPqNovo.setBounds(200, 570, 100, 25);
		this.add(linkCNPqNovo);
		
		matriculaAdcionar = new JTextField();	
		matriculaAdcionar.setToolTipText("ex: 123456...");
		matriculaAdcionar.setBounds(650, 530, 100, 20);
		this.add(matriculaAdcionar);
	}

	private void adcionarBotao() {
		JButton criar = new JButton("Criar");
		criar.setForeground(Color.WHITE);
		criar.setBackground(new Color(119, 221, 119));
		criar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					adcionarGrupo(nomeGrupoCriar.getText(), linkCNPqCriar.getText(),
							Long.parseLong(matriculaCriar.getText()));
					JOptionPane.showMessageDialog(null, "Grupo criado!");
					adcionarJComobox();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		criar.setBounds(280, 265, 100, 40);
		this.add(criar);
		JButton remover = new JButton("Remover");
		remover.setForeground(Color.WHITE);
		remover.setBackground(new Color(119, 221, 119));
		remover.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					removerGrupo(Long.parseLong(matriculaRemover.getText()), linkCNPqRemover.getText());
					JOptionPane.showMessageDialog(null, "Grupo removido!");
					adcionarJComobox();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		remover.setBounds(700, 265, 100, 40);
		this.add(remover);
		JButton atualizar = new JButton("Atualizar");
		atualizar.setForeground(Color.WHITE);
		atualizar.setBackground(new Color(119, 221, 119));
		atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					atualizarGrupo(Long.parseLong(matriculaAntigo.getText()), linkCNPqAntigo.getText(),
							nomeGrupoNovo.getText(), linkCNPqNovo.getText());
					JOptionPane.showMessageDialog(null, "Grupo atualizado!");
					adcionarJComobox();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		atualizar.setBounds(350, 565, 100, 30);
		this.add(atualizar);
		JButton mostrarGrupos = new JButton("<html>Criar Relatorio</html>");
		mostrarGrupos.setForeground(Color.WHITE);
		mostrarGrupos.setBackground(new Color(119, 221, 119));
		mostrarGrupos.setFont(new Font("Arial", Font.BOLD, 9));
		mostrarGrupos.addActionListener(new ActionListener() {

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
		mostrarGrupos.setBounds(350, 520, 100, 30);
		this.add(mostrarGrupos);
		
		JButton adcionarGrupo = new JButton("<html>Adcionar Membro</html>");
		adcionarGrupo.setForeground(Color.WHITE);
		adcionarGrupo.setBackground(new Color(119, 221, 119));
		adcionarGrupo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.adcionarMembro(Long.parseLong(matriculaAdcionar.getText()), (String) op2.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Membro adcionado!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Membro não adcionado!");
					e1.printStackTrace();
				}
			}
		});
		adcionarGrupo.setFont(new Font("Arial", Font.BOLD, 9));
		adcionarGrupo.setBounds(650, 555, 90, 25);
		this.add(adcionarGrupo);

		JButton removerGrupo = new JButton("<html>Remover Membro</html>");
		removerGrupo.setForeground(Color.WHITE);
		removerGrupo.setBackground(new Color(119, 221, 119));
		removerGrupo.setFont(new Font("Arial", Font.BOLD, 9));
		removerGrupo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.removerMembro(Long.parseLong(matriculaAdcionar.getText()), (String) op2.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Membro Removido!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Membro não Removido!");
					e1.printStackTrace();
				}
			}
		});
		removerGrupo.setBounds(650, 585, 90, 25);
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
		adcionarProjeto.setBounds(500, 555, 90, 25);
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
		removerProjeto.setBounds(500, 585, 90, 25);
		this.add(removerProjeto);
	}

	private void adcionarJComobox() {
		Object[] grupos = mostrarGruposDoUsuarioLogado();
		Object[] projetos = mostrarProjetosDoUsuarioLogado();
		String[] textoProjetos = { "---Nenhum projetos cadastrado---" };
		String[] textoGrupos = { "---Nenhum grupos cadastrado---" };
		if (grupos == null || grupos.length == 0) {
			op.removeAllItems();
			op.addItem(textoGrupos[0].toString());
			op2.removeAllItems();
			op2.addItem(textoGrupos[0].toString());
		} else {
			op.removeAllItems();
			op2.removeAllItems();
			for (Object object : grupos) {
				op.addItem(object.toString());
				op2.addItem(object.toString());
			}
		}
		op.setBounds(350, 480, 100, 20);
		add(op);
		op2.setBounds(500, 480, 250, 20);
		add(op2);
		
		if ( projetos == null||projetos.length == 0) {
			opProjetos.addItem(textoProjetos[0].toString());		
		} else {
			opProjetos.removeAllItems();
			for (Object object : projetos) {
				opProjetos.addItem(object.toString());
			}
		}
		opProjetos.setBounds(500, 525, 100, 25);
		add(opProjetos);
	}

	public static void main(String[] args) {
		JFrame p = new JFrame();
		p.add(new TelaCadastroGruposSwing());
		p.setSize(1000, 700);
		p.setVisible(true);
	}

}
