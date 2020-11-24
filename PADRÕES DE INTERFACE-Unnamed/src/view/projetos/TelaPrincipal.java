package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame {
	private JButton cancelar;
	private JPanel painel;
	private JButton voltar;

	private JLabel projeto;
	private JLabel grupo;
	private JLabel edital;
	private JButton proxima1;
	private JButton proxima2;
	private JButton proxima3;

	public TelaPrincipal() {
		setLayout(null);
		setSize(700, 500);
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

	private void botaCancelar() {
		this.dispose();
	}

	private void botaoVoltar() {
		this.remove(painel);
		painel = null;
		this.remove(voltar);
		voltar = null;
		adcionarLabels();
		adcionarBotao();
		this.repaint();
	}

	private void criarBotaoVoltar() {
		voltar = new JButton("Voltar");
		voltar.setForeground(Color.WHITE);
		voltar.setBackground(new Color(119, 221, 119));
		voltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoVoltar();
			}
		});
		voltar.setBounds(30, 410, 100, 40);
		this.add(voltar);
		this.remove(grupo);
		grupo = null;
		this.remove(projeto);
		projeto = null;
		this.remove(edital);
		edital = null;
		this.remove(proxima1);
		proxima1 = null;
		this.remove(proxima2);
		proxima2 = null;
		this.remove(proxima3);
		proxima3 = null;
		remove(cancelar);
	}

	private void criarGrupo() {
		painel = new TelaCadastroGrupos();
		criarBotaoVoltar();
		add(painel);
		repaint();
	}

	private void criarEdital() {
		painel = new TelaCadastroEditais();
		criarBotaoVoltar();
		add(painel);
		repaint();
	}

	private void criarProjeto() {
		painel = new TelaCadastroProjetos();
		add(painel);
		criarBotaoVoltar();
		repaint();
	}

	private void adcionarPainel() {
		JPanel painelCinza = new JPanel();
		painelCinza.setBackground(Color.DARK_GRAY);
		painelCinza.setSize(150, 500);
		add(painelCinza);
	}

	private void adcionarLabels() {
		JLabel label = new JLabel("<html>Menu</html>");
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setBounds(30, 0, 90, 150);
		label.setForeground(new Color(192, 192, 192));
		this.add(label);

		projeto = new JLabel("Administrar Projetos");
		projeto.setFont(new Font("Arial", Font.BOLD, 25));
		projeto.setBounds(165, -40, 300, 150);
		projeto.setForeground(Color.DARK_GRAY);
		this.add(projeto);

		grupo = new JLabel("Administrar Grupos");
		grupo.setFont(new Font("Arial", Font.BOLD, 25));
		grupo.setBounds(165, 95, 300, 150);
		grupo.setForeground(Color.DARK_GRAY);
		this.add(grupo);

		edital = new JLabel("Administrar Editais");
		edital.setFont(new Font("Arial", Font.BOLD, 25));
		edital.setBounds(165, 230, 300, 150);
		edital.setForeground(Color.DARK_GRAY);
		this.add(edital);

	}

	private void adcionarBotao() {
		cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaCancelar();
			}
		});
		cancelar.setBounds(30, 410, 100, 40);
		this.add(cancelar);

		proxima1 = new JButton("Proximo =>");
		proxima1.setForeground(Color.WHITE);
		proxima1.setBackground(new Color(119, 221, 119));
		proxima1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarProjeto();
			}
		});
		proxima1.setBounds(550, 15, 100, 40);
		this.add(proxima1);

		proxima2 = new JButton("Proximo =>");
		proxima2.setForeground(Color.WHITE);
		proxima2.setBackground(new Color(119, 221, 119));
		proxima2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarGrupo();
			}
		});
		proxima2.setBounds(550, 150, 100, 40);
		this.add(proxima2);

		proxima3 = new JButton("Proximo =>");
		proxima3.setForeground(Color.WHITE);
		proxima3.setBackground(new Color(119, 221, 119));
		proxima3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarEdital();
			}
		});
		proxima3.setBounds(550, 285, 100, 40);
		this.add(proxima3);
	}

	public static void main(String[] args) {
		new TelaPrincipal();
	}

}
