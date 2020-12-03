package view.autenticacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.abstract_factory.InterfaceTelaConfiguracaoAdmin;
import view.controller.ControllerTelaConfiguracaoAdmin;

public class TelaConfiguracaoAdminSwing extends JFrame implements InterfaceTelaConfiguracaoAdmin{

	private static final long serialVersionUID = 1L;
	private JTextField matriculaAdministrador;
	private JTextField matriculaMembro;
	
	private ControllerTelaConfiguracaoAdmin controller= new ControllerTelaConfiguracaoAdmin();
	
	public TelaConfiguracaoAdminSwing()  {

		setLayout(null);
		setSize(300, 320);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(1);
		setTitle("Tornar Administrador");

		adcionarLabels();
		adcionarTextFields();
		adcionarBotao();
		setVisible(true);
	}

	private void botaCancelar() {
		this.dispose();
	}

	private void botaoModificar() {
		try {
			
			habilitarAdministrador(Long.parseLong(matriculaAdministrador.getText()),Long.parseLong(matriculaMembro.getText()));
			JOptionPane.showMessageDialog(null, "Administrador habilitado!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private void adcionarLabels() {
		JLabel tornar = new JLabel("Tornar");
		tornar.setFont(new Font("Arial", Font.BOLD, 25));
		tornar.setBounds(110, 20, 250, 30);
		tornar.setForeground(new Color(192, 192, 192));
		this.add(tornar);

		JLabel administrador = new JLabel("Administrador");
		administrador.setFont(new Font("Arial", Font.BOLD, 25));
		administrador.setBounds(70, 50, 250, 30);
		administrador.setForeground(new Color(192, 192, 192));
		this.add(administrador);

		JLabel administradorMatricula = new JLabel("Matricula do administrador:");
		administradorMatricula.setFont(new Font("Arial", Font.BOLD, 12));
		administradorMatricula.setBounds(50, 100, 180, 15);
		administradorMatricula.setForeground(new Color(192, 192, 192));
		this.add(administradorMatricula);

		JLabel membroMatricula = new JLabel("Matricula do membro:");
		membroMatricula.setFont(new Font("Arial", Font.BOLD, 12));
		membroMatricula.setBounds(50, 170, 180, 15);
		membroMatricula.setForeground(new Color(192, 192, 192));
		this.add(membroMatricula);
	}

	private void adcionarTextFields() {
		matriculaAdministrador = new JTextField();
		matriculaAdministrador.setToolTipText("ex: 123456...");
		matriculaAdministrador.setForeground(Color.WHITE);
		matriculaAdministrador.setBackground(new Color(25, 25, 25));
		matriculaAdministrador.setBounds(50, 120, 200, 25);
		this.add(matriculaAdministrador);

		matriculaMembro = new JTextField();
		matriculaMembro.setToolTipText("ex: 123456...");
		matriculaMembro.setForeground(Color.WHITE);
		matriculaMembro.setBackground(new Color(25, 25, 25));
		matriculaMembro.setBounds(50, 190, 200, 25);
		this.add(matriculaMembro);
	}

	private void adcionarBotao() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.setBounds(50, 230, 90, 30);
		cancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				botaCancelar();
			}
		});
		this.add(cancelar);

		JButton modifica = new JButton("Modificar");
		modifica.setForeground(Color.WHITE);
		modifica.setBackground(new Color(119, 221, 119));
		modifica.setBounds(160, 230, 90, 30);
		modifica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				botaoModificar();
			}
		});
		this.add(modifica);

	}

	public void habilitarAdministrador(long matriculaAdministrador,long matricula) throws Exception {
		controller.habilitarAdministrador(matriculaAdministrador,matricula);
		
	}
	public static void main(String[] args) {
		new TelaConfiguracaoAdminSwing();
	}
}
