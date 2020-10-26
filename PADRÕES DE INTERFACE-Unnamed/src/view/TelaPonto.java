package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TelaPonto extends JFrame {
	private JTextField nomeDoProjeto;
	private JTextField login;
	private JPasswordField senha;

	public TelaPonto() {
		setLayout(null);
		setSize(500, 300);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		setVisible(true);
	}

	public void botaoBaterPonto(String nomeDoProjeto, String login, String senha) {

	}

	public void botaoVerDetalhes() {

	}

	private void adcionarLabels() {
		JLabel marcarPonto = new JLabel("Marcar Ponto");
		marcarPonto.setFont(new Font("Arial", Font.BOLD, 25));
		marcarPonto.setBounds(160, 20, 250, 30);
		marcarPonto.setForeground(new Color(192,192,192));
		this.add(marcarPonto);

		JLabel nome = new JLabel("Nome do projeto:");
		nome.setFont(new Font("Arial", Font.BOLD, 12));
		nome.setBounds(50, 90, 120, 15);
		nome.setForeground(new Color(192,192,192));
		this.add(nome);

		JLabel login = new JLabel("Email:");
		login.setFont(new Font("Arial", Font.BOLD, 12));
		login.setBounds(50, 140, 120, 15);
		login.setForeground(new Color(192,192,192));
		this.add(login);

		JLabel senha = new JLabel("Senha:");
		senha.setFont(new Font("Arial", Font.BOLD, 12));
		senha.setBounds(50, 190, 120, 15);
		senha.setForeground(new Color(192,192,192));
		this.add(senha);
	}

	private void adcionarTextFields() {
		nomeDoProjeto = new JTextField();
		nomeDoProjeto.setToolTipText("ex: Projeto de ADS...");
		nomeDoProjeto.setForeground(Color.WHITE);
		nomeDoProjeto.setBackground(new Color(25, 25, 25));
		nomeDoProjeto.setBounds(165, 86, 280, 25);
		this.add(nomeDoProjeto);

		login = new JTextField();
		login.setToolTipText("ex: nome@gmail.com...");
		login.setForeground(Color.WHITE); 
		login.setBackground(new Color(25, 25, 25));
		login.setBounds(165, 135, 280, 25);
		this.add(login);

		senha = new JPasswordField();
		senha.setToolTipText("ex: seunome123...");
		senha.setForeground(Color.WHITE);
		senha.setBackground(new Color(25, 25, 25));
		senha.setBounds(165, 185, 280, 25);
		this.add(senha);
	}

	private void adcionarBotao() {
		JButton baterPonto = new JButton("Bater Ponto");
		baterPonto.setForeground(Color.WHITE);
		baterPonto.setBackground(new Color(119, 221, 119));
		baterPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botaoBaterPonto(nomeDoProjeto.getText(), login.getText(), senha.getText());
			}
		});
		baterPonto.setBounds(95, 225, 150, 30);
		this.add(baterPonto);

		JButton detalhes = new JButton("Detalhes");
		detalhes.setForeground(Color.WHITE);
		detalhes.setBackground(new Color(119, 221, 119));
		detalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botaoVerDetalhes();
			}
		});
		detalhes.setBounds(255, 225, 150, 30);
		this.add(detalhes);

	}

	public static void main(String[] args) {
		new TelaPonto();
	}
}
