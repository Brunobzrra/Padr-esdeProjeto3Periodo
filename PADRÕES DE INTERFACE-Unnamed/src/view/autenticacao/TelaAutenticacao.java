package view.autenticacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.casosDeUsofachadas.CasoDeUsoDoiseNove;

public class TelaAutenticacao extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField login;
	private JPasswordField senha;
	private JComboBox<Object> op;

	public TelaAutenticacao() {
		setLayout(null);
		setSize(700, 250);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		adcionarCombo();
		setVisible(true);
	}
	private void botaCancelar() {
		this.dispose();
	}
	private void botaoDeLogin() {
		try {
			new CasoDeUsoDoiseNove().fazerLogin(login.getText(), senha.getText(), (String) op.getSelectedItem());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	private void botaoFazerLogout() {
		try {
			new CasoDeUsoDoiseNove().fazerLogout(login.getText());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	private void adcionarCombo() {
		Object[] projetos= {"Interno","ISMTP"};
		op = new JComboBox<Object>(projetos);
		op.setBounds(480, 86, 190, 25);
		op.setBackground(new Color(25, 25, 25));
		op.setForeground(Color.WHITE);
		;
		add(op);
	}
	private void adcionarLabels() {
		JLabel marcarPonto = new JLabel("Faça o seu Login");
		marcarPonto.setFont(new Font("Arial", Font.BOLD, 25));
		marcarPonto.setBounds(145, 20, 250, 30);
		marcarPonto.setForeground(new Color(192, 192, 192));
		this.add(marcarPonto);

		JLabel login = new JLabel("Email:");
		login.setFont(new Font("Arial", Font.BOLD, 12));
		login.setBounds(50, 90, 120, 15);
		login.setForeground(new Color(192, 192, 192));
		this.add(login);

		JLabel senha = new JLabel("Senha:");
		senha.setFont(new Font("Arial", Font.BOLD, 12));
		senha.setBounds(50, 140, 120, 15);
		senha.setForeground(new Color(192, 192, 192));
		this.add(senha);
	}

	private void adcionarTextFields() {
		login = new JTextField();
		login.setToolTipText("ex: nome@gmail.com...");
		login.setForeground(Color.WHITE);
		login.setBackground(new Color(25, 25, 25));
		login.setBounds(145, 85, 300, 25);
		this.add(login);

		senha = new JPasswordField();
		senha.setToolTipText("ex: seunome123...");
		senha.setForeground(Color.WHITE);
		senha.setBackground(new Color(25, 25, 25));
		senha.setBounds(145, 135, 300, 25);
		this.add(senha);
	}

	private void adcionarBotao() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.setBounds(145, 170, 90, 30);
		cancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				botaCancelar();
			}
		});
		this.add(cancelar);

		JButton fazerLogout = new JButton("<-Fazer Logout");
		fazerLogout.setForeground(Color.WHITE);
		fazerLogout.setBackground(new Color(119, 221, 119));
		fazerLogout.setBounds(326, 170, 120, 30);
		fazerLogout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				botaoFazerLogout();
			}
		});
		this.add(fazerLogout);
		
		JButton fazerLogin = new JButton("Fazer Login ->");
		fazerLogin.setForeground(Color.WHITE);
		fazerLogin.setBackground(new Color(119, 221, 119));
		fazerLogin.setBounds(480, 170, 190, 30);
		fazerLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				botaoDeLogin();
			}
		});
		this.add(fazerLogin);
	}

	public static void main(String[] args) {
		new TelaAutenticacao();
	}
}
