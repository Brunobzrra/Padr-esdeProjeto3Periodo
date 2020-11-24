package view.projetos;

import java.awt.Color;
import java.util.Date;

import javax.swing.JPanel;

import view.controller.ControllerCadastroEditais;

public class TelaCadastroEditais extends JPanel {
	public TelaCadastroEditais() {
		setBounds(150, 0, 850, 700);
		setBackground(Color.BLACK);
		setVisible(true);
	
	}
	
	private ControllerCadastroEditais controller= new ControllerCadastroEditais();
	
	
	public void cadastrarEdital(String nomeEdital, Date dataInicio, Date dataTermino, long matricula) throws Exception {
		controller.adcionarEdital(nomeEdital, dataInicio, dataTermino, matricula);
		
	}
	public void atualizarEdital(String nomeEdital, String novoNome, Date dataInicio, Date dataTermino, long matricula) throws Exception {
		controller.atualizarEdital(nomeEdital, novoNome, dataInicio, dataTermino, matricula);

	}
	public void removerEdital(String nomeEdital, long matricula) throws Exception {
		controller.removerEdital(nomeEdital, matricula);
	
	}
	public void mostrarEditaisUsuarioLogado() {
		controller.mostrarEditaisDoUsuario();
	}
	
}
