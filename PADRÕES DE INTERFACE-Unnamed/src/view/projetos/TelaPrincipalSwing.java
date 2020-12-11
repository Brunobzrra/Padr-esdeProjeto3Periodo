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

import view.abstract_factory.FabricaDeTelasSwing;
import view.abstract_factory.InterfaceFabricaDeTelas;
import view.abstract_factory.InterfaceTelaPrincipal;
import view.controller.ControllerTelaAutenticacao;




public class TelaPrincipalSwing extends JFrame implements InterfaceTelaPrincipal{
	private JButton cancelar;
	private JPanel painelSecundario;
	private JButton voltar;
	private JLabel projeto;
	private JLabel grupo;
	private JLabel edital;
	private JLabel justificar;
	private JLabel configAdmin;
	private JButton proxima1;
	private JButton proxima2;
	private JButton proxima3;
	private JButton botaoConfigAdmin;
	private JButton botaoJustificar;
	private JButton logout;
	private JButton horario;
	private ControllerTelaAutenticacao controler = new ControllerTelaAutenticacao();
	private InterfaceFabricaDeTelas fabricaDeTelas = FabricaDeTelasSwing.getFabrica();

	public TelaPrincipalSwing() {
		setLayout(null);
		setSize(500, 400);
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

	public void botaoFazerLogout() {
		String email = (String) JOptionPane.showInputDialog("Coloque o email");
		try {
			controler.fazerLogout(email);
			this.dispose();
	
			fabricaDeTelas.fabricarTelaAutenticacao();
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
		projeto.setVisible(true);
		proxima1.setVisible(true);
		cancelar.setVisible(true);
		logout.setVisible(true);
		if (controler.isAdmin()) {
			horario.setVisible(true);
			grupo.setVisible(true);
			edital.setVisible(true);
			proxima2.setVisible(true);
			proxima3.setVisible(true);
			configAdmin.setVisible(true);
			botaoConfigAdmin.setVisible(true);
		}
		justificar.setVisible(true);
		botaoJustificar.setVisible(true);
		voltar.setVisible(false);
		setSize(500, 400);
		setLocationRelativeTo(null);
		this.repaint();
	}

	private void transitarTela() {
		proxima1.setVisible(false);
		projeto.setVisible(false);
		cancelar.setVisible(false);
		logout.setVisible(false);
		if (controler.isAdmin()) {
			horario.setVisible(false);
			configAdmin.setVisible(false);
			grupo.setVisible(false);
			proxima2.setVisible(false);
			proxima3.setVisible(false);
			edital.setVisible(false);
			botaoConfigAdmin.setVisible(false);
		}
		justificar.setVisible(false);
		botaoJustificar.setVisible(false);
		voltar.setVisible(true);
		setSize(1000, 700);
		setLocationRelativeTo(null);
	}

	private void criarGrupo() {
		painelSecundario = (JPanel) fabricaDeTelas.fabricarTelaCadastroGrupos();
		transitarTela();
		add(painelSecundario);
		repaint();
	}

	private void criarEdital() {
		painelSecundario = (JPanel) fabricaDeTelas.fabricarTelaCadastroEditais();
		transitarTela();
		add(painelSecundario);
		repaint();
	}

	private void criarProjeto() {
		painelSecundario = (JPanel) fabricaDeTelas.fabricarTelaCadastroProjetos();
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
		projeto.setBounds(165, 265, 300, 150);
		projeto.setForeground(Color.DARK_GRAY);
		this.add(projeto);
		if (controler.isAdmin()) {
			grupo = new JLabel("Administrar Grupos");
			grupo.setFont(new Font("Arial", Font.BOLD, 20));
			grupo.setBounds(165, 215, 300, 150);
			grupo.setForeground(Color.DARK_GRAY);
			this.add(grupo);

			edital = new JLabel("Administrar Editais");
			edital.setFont(new Font("Arial", Font.BOLD, 20));
			edital.setBounds(165, 165, 300, 150);
			edital.setForeground(Color.DARK_GRAY);
			this.add(edital);

			configAdmin = new JLabel("<html>Recursos do administrador</html>");
			configAdmin.setFont(new Font("Arial", Font.BOLD, 20));
			configAdmin.setBounds(175, -30, 150, 150);
			configAdmin.setForeground(Color.DARK_GRAY);
			this.add(configAdmin);
		}
		justificar = new JLabel("<html>Justificar ponto</html>");
		justificar.setFont(new Font("Arial", Font.BOLD, 20));
		justificar.setBounds(350, -30, 100, 150);
		justificar.setForeground(Color.DARK_GRAY);
		this.add(justificar);

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
		voltar.setBounds(25, 620, 100, 40);
		voltar.setVisible(false);
		this.add(voltar);

		cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(new Color(119, 221, 119));
		cancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaCancelar();
			}
		});
		cancelar.setBounds(25, 320, 100, 40);
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
		logout.setBounds(25, 270, 100, 40);
		this.add(logout);
		

		proxima1 = new JButton("Proximo =>");
		proxima1.setForeground(Color.WHITE);
		proxima1.setBackground(new Color(119, 221, 119));
		proxima1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				criarProjeto();
			}
		});
		proxima1.setBounds(380, 320, 100, 40);
		this.add(proxima1);
		if (controler.isAdmin()) {
			horario = new JButton("<html>Horario</html>");
			horario.setForeground(Color.WHITE);
			horario.setBackground(new Color(119, 221, 119));
			horario.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					fabricaDeTelas.fabricarTelaCadastroHorarioPrevisto();
				}
			});
			horario.setFont(new Font("Arial", Font.BOLD, 10));
			horario.setBounds(25, 220, 100, 40);
			this.add(horario);

			proxima2 = new JButton("Proximo =>");
			proxima2.setForeground(Color.WHITE);
			proxima2.setBackground(new Color(119, 221, 119));
			proxima2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					criarGrupo();
				}
			});
			proxima2.setBounds(380, 270, 100, 40);
			this.add(proxima2);

			proxima3 = new JButton("Proximo =>");
			proxima3.setForeground(Color.WHITE);
			proxima3.setBackground(new Color(119, 221, 119));
			proxima3.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					criarEdital();
				}
			});
			proxima3.setBounds(380, 220, 100, 40);
			this.add(proxima3);

			botaoConfigAdmin = new JButton("<html>Configurar Administrador</html>");
			botaoConfigAdmin.setForeground(Color.WHITE);
			botaoConfigAdmin.setBackground(new Color(119, 221, 119));
			botaoConfigAdmin.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					fabricaDeTelas.fabricarTelaConfiguracaoAdmin();
				}
			});
			botaoConfigAdmin.setBounds(180, 100, 130, 70);
			this.add(botaoConfigAdmin);
		}
		botaoJustificar = new JButton("<html>Justificar Ponto</html>");
		botaoJustificar.setForeground(Color.WHITE);
		botaoJustificar.setBackground(new Color(119, 221, 119));
		botaoJustificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fabricaDeTelas.fabricarTelaJustificarPonto();
				
				
			}
		});
		botaoJustificar.setBounds(350, 100, 130, 70);
		this.add(botaoJustificar);

	}

	public static void main(String[] args) {
		new TelaPrincipalSwing();
	}

}
