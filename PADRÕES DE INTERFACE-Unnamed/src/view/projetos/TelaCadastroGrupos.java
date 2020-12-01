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

import view.controller.ControllerCadastroGrupos;
import view.controller.ControllerGerarRelatorio;
import view.projetos.abstract_factory.InterfaceTelaCadastroGrupos;

public class TelaCadastroGrupos extends JPanel implements InterfaceTelaCadastroGrupos {
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
	private JComboBox<Object> op = new JComboBox<Object>();

	public TelaCadastroGrupos() {
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
		atualizar.setBounds(300, 400, 300, 40);
		atualizar.setFont(new Font("Arial", Font.BOLD, 30));
		atualizar.setForeground(Color.DARK_GRAY);
		this.add(atualizar);

		JLabel matriculaAtualizar = new JLabel("Matricula:");
		matriculaAtualizar.setBounds(200, 440, 300, 40);
		matriculaAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		matriculaAtualizar.setForeground(Color.DARK_GRAY);
		this.add(matriculaAtualizar);

		JLabel linkCNPqAntigo = new JLabel("Link CNPq:");
		linkCNPqAntigo.setBounds(200, 530, 300, 40);
		linkCNPqAntigo.setFont(new Font("Arial", Font.BOLD, 14));
		linkCNPqAntigo.setForeground(Color.DARK_GRAY);
		this.add(linkCNPqAntigo);

		JLabel nomeNovo = new JLabel("Novo nome:");
		nomeNovo.setBounds(500, 440, 300, 40);
		nomeNovo.setFont(new Font("Arial", Font.BOLD, 14));
		nomeNovo.setForeground(Color.DARK_GRAY);
		this.add(nomeNovo);

		JLabel linkCNPqNovo = new JLabel("Novo link CNPq:");
		linkCNPqNovo.setBounds(502, 530, 300, 40);
		linkCNPqNovo.setFont(new Font("Arial", Font.BOLD, 14));
		linkCNPqNovo.setForeground(Color.DARK_GRAY);
		this.add(linkCNPqNovo);
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
		matriculaAntigo.setBounds(200, 480, 200, 25);
		this.add(matriculaAntigo);

		linkCNPqAntigo = new JTextField();
		linkCNPqAntigo.setToolTipText("ex: 123456...");
		linkCNPqAntigo.setBounds(200, 570, 200, 25);
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
		nomeGrupoNovo.setBounds(500, 480, 200, 25);
		this.add(nomeGrupoNovo);

		linkCNPqNovo = new JTextField();
		linkCNPqNovo.setToolTipText("ex: 123456...");
		linkCNPqNovo.setBounds(502, 570, 200, 25);
		this.add(linkCNPqNovo);

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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		atualizar.setBounds(600, 605, 100, 40);
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
		mostrarGrupos.setBounds(720, 610, 100, 30);
		this.add(mostrarGrupos);
	}

	private void adcionarJComobox() {
		Object[] grupos = mostrarGruposDoUsuarioLogado();
		String[] nome = { "---Nenhum Grupo cadastrado---" };
		if (grupos == null || grupos.length == 0) {
			op.removeAllItems();
			op.addItem(nome[0].toString());
		} else {
			op.removeAllItems();
			for (Object object : grupos) {
				op.addItem(object.toString());
			}
		}
		op.setBounds(720, 580, 100, 20);
		add(op);
	}

	public static void main(String[] args) {
		JFrame p = new JFrame();
		p.add(new TelaCadastroGrupos());
		p.setSize(1000, 700);
		p.setVisible(true);
	}

}
