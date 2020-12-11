package view;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.abstract_factory.InterfaceTelaPonto;
import view.controller.ControllerRegistradorEView;

public class TelaPontoSwing extends JFrame implements InterfaceTelaPonto {

	private static final long serialVersionUID = 1L;
	private JTextField login;
	private JPasswordField senha;
	private ControllerRegistradorEView controller;
	private JComboBox<Object> op;

	public TelaPontoSwing() {
		try {
			controller = new ControllerRegistradorEView();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aplicacao servidora não inicializada", "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
		setLayout(null);
		setSize(700, 220);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Marcar Ponto");
		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		adcionarCombo(null);
		setVisible(true);
	}

	public void botaoBaterPonto(String nomeDoProjeto, String login, String senha) {
		try {
			controller.registrarPonto(nomeDoProjeto, login, senha);
			JOptionPane.showMessageDialog(this, "Ponto batido com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	public void botaoVerDetalhes(String login, String nomeDoProjeto) {
		try {
			String texto = controller.horasTrabalhadasValidas(login, nomeDoProjeto) + "\n"
					+ controller.defcitHoras(login, nomeDoProjeto) + "\n"
					+ controller.getPontosValidos(login, nomeDoProjeto);
			JOptionPane.showMessageDialog(this, texto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void adcionarLabels() {
		JLabel marcarPonto = new JLabel("Bater Ponto");
		marcarPonto.setFont(new Font("Arial", Font.BOLD, 25));
		marcarPonto.setBounds(270, 20, 250, 30);
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

	private void adcionarCombo(Object[] projetos) {
		if (projetos == null) {
			projetos = new String[1];
			projetos[0] = "------Nenhum Projeto------";
		}
		if (op != null) {
			op.removeAllItems();
			for (Object object : projetos) {
				op.addItem(object.toString());
			}
			this.repaint();
		}
		op = new JComboBox<Object>(projetos);
		op.setBounds(480, 86, 190, 25);
		op.setBackground(new Color(25, 25, 25));
		op.setForeground(Color.WHITE);
		;
		add(op);
	}

	/*
	 * TODO Ao invés do nome do projeto, colocar o combobox com a relação detodos os
	 * projetos para o membro inserido
	 */
	private void adcionarTextFields() {
		login = new JTextField();
		login.setToolTipText("ex: nome@gmail.com...");
		login.setForeground(Color.WHITE);
		login.setBackground(new Color(25, 25, 25));
		login.setBounds(145, 85, 300, 25);
		login.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				try {
					adcionarCombo(controller.recuperarProjetos(login.getText()).toArray());
				} catch (Exception e2) {
					if (!op.getItemAt(0).equals("------Nenhum Projeto------")) {
						op.removeAllItems();
						op.addItem("------Nenhum Projeto------");
						op.repaint();
					}
				}
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		this.add(login);

		senha = new JPasswordField();
		senha.setToolTipText("ex: seunome123...");
		senha.setForeground(Color.WHITE);
		senha.setBackground(new Color(25, 25, 25));
		senha.setBounds(145, 135, 300, 25);
		this.add(senha);
	}

	private void adcionarBotao() {
		JButton baterPonto = new JButton("Bater");
		baterPonto.setForeground(Color.WHITE);
		baterPonto.setBackground(new Color(119, 221, 119));
		baterPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botaoBaterPonto((String) op.getSelectedItem(), login.getText(), senha.getText());
				senha.setText("");
			}
		});
		baterPonto.setBounds(480, 135, 90, 30);
		this.add(baterPonto);

		JButton detalhes = new JButton("Detalhes");
		detalhes.setForeground(Color.WHITE);
		detalhes.setBackground(new Color(119, 221, 119));
		detalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botaoVerDetalhes(login.getText(), (String) op.getSelectedItem());
			}
		});
		detalhes.setBounds(580, 135, 90, 30);
		this.add(detalhes);

	}

	public static void main(String[] args) {
		new TelaPontoSwing();
	}
}
