package view.projetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.casosDeUsofachadas.CasoDeUsoDoze;

public class TelaJustificativaPonto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField matricula;
	private JTextArea justificativa;

	public TelaJustificativaPonto() {
		setLayout(null);
		setSize(300, 360);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Justificar Ponto");
		adcionarLabels();
		adcionarBotao();
		adcionarTextFields();
		setVisible(true);
	}

	private void botaCancelar() {
		this.dispose();
	}

	private void botaoJustificarPonto() {
		try {
			new CasoDeUsoDoze().justificarPonto(LocalDateTime.now(), justificativa.getText(),
					Long.parseLong(matricula.getText()));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void adcionarLabels() {
		JLabel justificar = new JLabel("Justificar");
		justificar.setFont(new Font("Arial", Font.BOLD, 25));
		justificar.setBounds(95, 20, 250, 30);
		justificar.setForeground(new Color(192, 192, 192));
		this.add(justificar);

		JLabel membroMatricula = new JLabel("Matricula do membro:");
		membroMatricula.setFont(new Font("Arial", Font.BOLD, 12));
		membroMatricula.setBounds(50, 90, 180, 15);
		membroMatricula.setForeground(new Color(192, 192, 192));
		this.add(membroMatricula);

		JLabel justificativa = new JLabel("Justificativa:");
		justificativa.setFont(new Font("Arial", Font.BOLD, 12));
		justificativa.setBounds(50, 160, 180, 15);
		justificativa.setForeground(new Color(192, 192, 192));
		this.add(justificativa);

	}

	private void adcionarTextFields() {
		matricula = new JTextField();
		matricula.setToolTipText("ex: seunome123@email.com...");
		matricula.setForeground(Color.WHITE);
		matricula.setBackground(new Color(25, 25, 25));
		matricula.setBounds(50, 110, 200, 25);
		this.add(matricula);

		justificativa = new JTextArea();
		justificativa.setToolTipText("ex: Não pude vir...");
		justificativa.setLineWrap(true);
		justificativa.setBackground(new Color(25, 25, 25));
		justificativa.setForeground(Color.WHITE);
		justificativa.setBounds(50, 180, 200, 100);
		this.add(justificativa);
	}

	private void adcionarBotao() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.setBounds(50, 290, 90, 30);
		cancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				botaCancelar();
			}
		});
		this.add(cancelar);

		JButton Justifica = new JButton("Justificar");
		Justifica.setForeground(Color.WHITE);
		Justifica.setBackground(new Color(119, 221, 119));
		Justifica.setBounds(160, 290, 90, 30);
		Justifica.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoJustificarPonto();
			}
		});
		this.add(Justifica);

	}

	public static void main(String[] args) {
		new TelaJustificativaPonto();
	}
}
