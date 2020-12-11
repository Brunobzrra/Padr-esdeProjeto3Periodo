package view.autenticacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import view.abstract_factory.FabricaDeTelasSwing;
import view.abstract_factory.InterfaceFabricaDeTelas;
import view.abstract_factory.InterfaceTelaCriarConta;
import view.controller.ControllerTelaCriarConta;

public class TelaCriarContaSwing extends JFrame implements InterfaceTelaCriarConta {

	private static final long serialVersionUID = 1L;
	private JTextField nomeCriar;
	private JTextField matriculaCriar;
	private JTextField loginCriar;
	private JPasswordField senhaCriar;
	
	
	private JTextField matriculaADM;
	private JTextField matriculaAtualizar;
	private JTextField nomeAtualizar;
	private JTextField loginAtualizar;
	private JPasswordField senhaAtualizar;

	private ControllerTelaCriarConta controller = new ControllerTelaCriarConta();

	private InterfaceFabricaDeTelas fabrica = FabricaDeTelasSwing.getFabrica();

	public TelaCriarContaSwing() {
		setLayout(null);
		setSize(700, 500);
		getContentPane().setBackground(new Color(213, 213, 213));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Criar conta");
		adcionarLabels();
		adcionarBotao();
		adcionarTextFields();
		adcionarPanel();
		setVisible(true);
	}

	private void botaCancelar() {
		if (controller.isVazia()) {
			JOptionPane.showMessageDialog(null, "Você precisa se cadastrar");
		} else {
			this.dispose();
			fabrica.fabricarTelaAutenticacao();
		}

	}

	private void botaoCriarConta() {
		try {
			controller.cadastrarMembro(nomeCriar.getText(), Long.parseLong(matriculaCriar.getText()),
					loginCriar.getText(), senhaCriar.getText());
			nomeCriar.setText("");
			matriculaCriar.setText("");
			loginCriar.setText("");
			senhaCriar.setText("");
			JOptionPane.showMessageDialog(null, "Conta criada!");
			this.dispose();
			fabrica.fabricarTelaAutenticacao();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void botaoAtualizar() {
		try {
			controller.atualizarMembro(Long.parseLong(matriculaADM.getText()),
					Long.parseLong(matriculaAtualizar.getText()), nomeAtualizar.getText(), loginAtualizar.getText(),
					senhaAtualizar.getText());
			nomeAtualizar.setText("");
			matriculaADM.setText("");
			matriculaAtualizar.setText("");
			loginAtualizar.setText("");
			senhaAtualizar.setText("");
			JOptionPane.showMessageDialog(null, "Conta atualizado!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void adcionarPanel() {
		JPanel painelCinza = new JPanel();
		painelCinza.setBackground(Color.DARK_GRAY);
		painelCinza.setSize(150, 500);
		add(painelCinza);
	}

	private void adcionarLabels() {
		JLabel label = new JLabel("<html>Criar Conta</html>");
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setBounds(30, 0, 90, 150);
		label.setForeground(new Color(192, 192, 192));
		this.add(label);

		JLabel criar = new JLabel("Criar Membro");
		criar.setFont(new Font("Arial", Font.BOLD, 30));
		criar.setBounds(165, -40, 300, 150);
		criar.setForeground(Color.DARK_GRAY);
		this.add(criar);

		JLabel matricula = new JLabel("Matricula:");
		matricula.setFont(new Font("Arial", Font.BOLD, 13));
		matricula.setBounds(165, 10, 300, 150);
		matricula.setForeground(Color.BLACK);
		this.add(matricula);

		JLabel nome = new JLabel("Nome:");
		nome.setFont(new Font("Arial", Font.BOLD, 13));
		nome.setBounds(165, 95, 300, 150);
		nome.setForeground(Color.BLACK);
		this.add(nome);

		JLabel login = new JLabel("Email:");
		login.setFont(new Font("Arial", Font.BOLD, 13));
		login.setBounds(165, 182, 300, 150);
		login.setForeground(Color.BLACK);
		this.add(login);

		JLabel senha = new JLabel("Senha:");
		senha.setFont(new Font("Arial", Font.BOLD, 13));
		senha.setBounds(165, 265, 300, 150);
		senha.setForeground(Color.BLACK);
		this.add(senha);

		JLabel atualizar = new JLabel("Atualizar Membro");
		atualizar.setFont(new Font("Arial", Font.BOLD, 30));
		atualizar.setBounds(440, -40, 300, 150);
		atualizar.setForeground(Color.DARK_GRAY);
		this.add(atualizar);

		JLabel coordenador = new JLabel("Matricula antiga:");
		coordenador.setFont(new Font("Arial", Font.BOLD, 13));
		coordenador.setBounds(440, 10, 300, 150);
		coordenador.setForeground(Color.BLACK);
		this.add(coordenador);

		JLabel matricula2 = new JLabel("Matricula:");
		matricula2.setFont(new Font("Arial", Font.BOLD, 13));
		matricula2.setBounds(570, 10, 300, 150);
		matricula2.setForeground(Color.BLACK);
		this.add(matricula2);

		JLabel nome2 = new JLabel("Nome:");
		nome2.setFont(new Font("Arial", Font.BOLD, 13));
		nome2.setBounds(440, 95, 300, 150);
		nome2.setForeground(Color.BLACK);
		this.add(nome2);

		JLabel login2 = new JLabel("Email:");
		login2.setFont(new Font("Arial", Font.BOLD, 13));
		login2.setBounds(440, 182, 300, 150);
		login2.setForeground(Color.BLACK);
		this.add(login2);

		JLabel senha2 = new JLabel("Senha:");
		senha2.setFont(new Font("Arial", Font.BOLD, 13));
		senha2.setBounds(440, 265, 300, 150);
		senha2.setForeground(Color.BLACK);
		this.add(senha2);
	}

	private void adcionarTextFields() {
		nomeCriar = new JTextField();
		nomeCriar.setToolTipText("ex: fulano...");
		nomeCriar.setForeground(Color.BLACK);
		nomeCriar.setBackground(new Color(240, 240, 240));
		nomeCriar.setBounds(200, 180, 200, 40);
		this.add(nomeCriar);

		matriculaCriar = new JTextField();
		matriculaCriar.setToolTipText("ex: 123456...");
		matriculaCriar.setForeground(Color.BLACK);
		matriculaCriar.setBackground(new Color(240, 240, 240));
		matriculaCriar.setBounds(200, 95, 200, 40);
		this.add(matriculaCriar);

		loginCriar = new JTextField();
		loginCriar.setToolTipText("ex: nome@gmail.com...");
		loginCriar.setForeground(Color.BLACK);
		loginCriar.setBackground(new Color(240, 240, 240));
		loginCriar.setBounds(200, 265, 200, 40);
		this.add(loginCriar);

		senhaCriar = new JPasswordField();
		senhaCriar.setToolTipText("ex: seunome123...");
		senhaCriar.setForeground(Color.BLACK);
		senhaCriar.setBackground(new Color(240, 240, 240));
		senhaCriar.setBounds(200, 350, 200, 40);
		this.add(senhaCriar);

		matriculaADM = new JTextField();
		matriculaADM.setToolTipText("ex: 123456...");
		matriculaADM.setForeground(Color.BLACK);
		matriculaADM.setBackground(new Color(240, 240, 240));
		matriculaADM.setBounds(440, 95, 110, 40);
		matriculaADM.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				try {
					Object[] dados = controller.recuperarMembro(Long.parseLong(matriculaAtualizar.getText()));
					nomeAtualizar.setText((String) dados[0]);
					loginAtualizar.setText((String) dados[1]);
					senhaAtualizar.setText((String) dados[2]);
					recarregarTela();
				} catch (Exception e2) {

				}
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		this.add(matriculaADM);

		matriculaAtualizar = new JTextField();
		matriculaAtualizar.setToolTipText("ex: 123456...");
		matriculaAtualizar.setForeground(Color.BLACK);
		matriculaAtualizar.setBackground(new Color(240, 240, 240));
		matriculaAtualizar.setBounds(570, 95, 110, 40);
		this.add(matriculaAtualizar);

		nomeAtualizar = new JTextField();
		nomeAtualizar.setToolTipText("ex: fulano...");
		nomeAtualizar.setForeground(Color.BLACK);
		nomeAtualizar.setBackground(new Color(240, 240, 240));
		nomeAtualizar.setBounds(440, 180, 240, 40);
		this.add(nomeAtualizar);

		loginAtualizar = new JTextField();
		loginAtualizar.setToolTipText("ex: nome@gmail.com...");
		loginAtualizar.setForeground(Color.BLACK);
		loginAtualizar.setBackground(new Color(240, 240, 240));
		loginAtualizar.setBounds(440, 265, 240, 40);
		this.add(loginAtualizar);

		senhaAtualizar = new JPasswordField();
		senhaAtualizar.setToolTipText("ex: seunome123...");
		senhaAtualizar.setForeground(Color.BLACK);
		senhaAtualizar.setBackground(new Color(240, 240, 240));
		senhaAtualizar.setBounds(440, 350, 240, 40);
		this.add(senhaAtualizar);
	}

	private void adcionarBotao() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaCancelar();
			}
		});
		cancelar.setBounds(30, 410, 100, 40);
		this.add(cancelar);

		JButton criar = new JButton("Criar");
		criar.setForeground(Color.WHITE);
		criar.setBackground(new Color(119, 221, 119));
		criar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoCriarConta();
			}
		});
		criar.setBounds(300, 410, 100, 40);
		this.add(criar);
		JButton atualizar = new JButton("Atualizar");
		atualizar.setForeground(Color.WHITE);
		atualizar.setBackground(new Color(119, 221, 119));
		atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoAtualizar();
			}
		});
		atualizar.setBounds(570, 410, 100, 40);
		this.add(atualizar);
	}

	private void recarregarTela() {
		this.repaint();
	}


	@Override
	public void cadastrarMembro(String nome, long matricula, String email, String senha) throws Exception {
		controller.cadastrarMembro(nome, matricula, email, senha);

	}

	@Override
	public void atualizarMembro(long matricula, long matriculaNovo, String nomeNovo, String emailNovo, String senhaNova)
			throws Exception {
		controller.atualizarMembro(matricula, matriculaNovo, nomeNovo, emailNovo, senhaNova);

	}

	@Override
	public Object[] recuperarMembro(long matricula) {
		return controller.recuperarMembro(matricula);
	}

	public static void main(String[] args) {
		new TelaCriarContaSwing();
	}
}
