package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.controller.ControllerTelaAutenticacao;

public class TelaPrincipal extends JFrame {
	private JButton cancelar;
	private JPanel painelSecundario;
	private JButton voltar;
	private JLabel projeto;
	private JLabel grupo;
	private JLabel edital;
	private JButton proxima1;
	private JButton proxima2;
	private JButton proxima3;
	private JButton logout;
	private ControllerTelaAutenticacao controler = new ControllerTelaAutenticacao();

	public TelaPrincipal() {
		setLayout(null);
		setSize(500, 280);
		getContentPane().setBackground(new Color(213, 213, 213));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu");
		adcionarLabels();
		adcionarBotao();
		adcionarPainel();
		setVisible(true);
	}

	private void botaoFazerLogout() {
		String email = (String) JOptionPane.showInputDialog("Coloque o email");
		try {
			controler.fazerLogout(email);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Email errado!");
			e.printStackTrace();
		}
	}

	private void botaCancelar() {
		this.dispose();
	}

	private void botaoVoltar() {
		this.remove(painelSecundario);
		painelSecundario = null;
		grupo.setVisible(true);
		projeto.setVisible(true);
		edital.setVisible(true);
		proxima1.setVisible(true);
		proxima2.setVisible(true);
		proxima3.setVisible(true);
		cancelar.setVisible(true);
		logout.setVisible(true);
		voltar.setVisible(false);
		setSize(500, 280);
		setLocationRelativeTo(null);
		this.repaint();
	}

	private void transitarTela() {
		voltar.setBounds(25, 620, 100, 40);
		grupo.setVisible(false);
		projeto.setVisible(false);
		edital.setVisible(false);
		proxima1.setVisible(false);
		proxima2.setVisible(false);
		proxima3.setVisible(false);
		cancelar.setVisible(false);
		logout.setVisible(false);
		voltar.setVisible(true);
		setSize(1000, 700);
		setLocationRelativeTo(null);
	}

	private void criarGrupo() {
		painelSecundario = new TelaCadastroGrupos();
		transitarTela();
		add(painelSecundario);
		repaint();
	}

	private void criarEdital() {
		painelSecundario = new TelaCadastroEditais();
		transitarTela();
		add(painelSecundario);
		repaint();
	}

	private void criarProjeto() {
		painelSecundario = new TelaCadastroProjetos();
		add(painelSecundario);
		transitarTela();
		repaint();
	}

	private void adcionarPainel() {
		JPanel painelCinza = new JPanel();
		painelCinza.setBackground(Color.DARK_GRAY);
		painelCinza.setSize(150, 1000);
		add(painelCinza);
	}

	private void adcionarLabels() {
		JLabel label = new JLabel("<html>Menu</html>");
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setBounds(30, 0, 90, 150);
		label.setForeground(new Color(192, 192, 192));
		this.add(label);

		projeto = new JLabel("Administrar Projetos");
		projeto.setFont(new Font("Arial", Font.BOLD, 20));
		projeto.setBounds(165, -40, 300, 150);
		projeto.setForeground(Color.DARK_GRAY);
		this.add(projeto);

		grupo = new JLabel("Administrar Grupos");
		grupo.setFont(new Font("Arial", Font.BOLD, 20));
		grupo.setBounds(165, 30, 300, 150);
		grupo.setForeground(Color.DARK_GRAY);
		this.add(grupo);

		edital = new JLabel("Administrar Editais");
		edital.setFont(new Font("Arial", Font.BOLD, 20));
		edital.setBounds(165, 100, 300, 150);
		edital.setForeground(Color.DARK_GRAY);
		this.add(edital);

	}

	private void adcionarBotao() {
		voltar = new JButton("Voltar");
		voltar.setForeground(Color.WHITE);
		voltar.setBackground(new Color(119, 221, 119));
		voltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoVoltar();
			}
		});
		this.add(voltar);

		cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaCancelar();
			}
		});
		cancelar.setBounds(25, 140, 100, 40);
		this.add(cancelar);

		logout = new JButton("<html>Fazer logout</html>");
		logout.setForeground(Color.WHITE);
		logout.setBackground(new Color(119, 221, 119));
		logout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoFazerLogout();
			}
		});
		logout.setFont(new Font("Arial", Font.BOLD, 10));
		logout.setBounds(25, 200, 100, 40);
		this.add(logout);

		proxima1 = new JButton("Proximo =>");
		proxima1.setForeground(Color.WHITE);
		proxima1.setBackground(new Color(119, 221, 119));
		proxima1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarProjeto();
			}
		});
		proxima1.setBounds(380, 15, 100, 40);
		this.add(proxima1);

		proxima2 = new JButton("Proximo =>");
		proxima2.setForeground(Color.WHITE);
		proxima2.setBackground(new Color(119, 221, 119));
		proxima2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarGrupo();
			}
		});
		proxima2.setBounds(380, 85, 100, 40);
		this.add(proxima2);

		proxima3 = new JButton("Proximo =>");
		proxima3.setForeground(Color.WHITE);
		proxima3.setBackground(new Color(119, 221, 119));
		proxima3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarEdital();
			}
		});
		proxima3.setBounds(380, 155, 100, 40);
		this.add(proxima3);
	}

	public static void main(String[] args) {
		new TelaPrincipal();
	}

}
