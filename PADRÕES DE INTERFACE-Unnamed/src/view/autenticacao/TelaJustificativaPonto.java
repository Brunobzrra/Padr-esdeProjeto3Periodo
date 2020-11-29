package view.autenticacao;

import java.time.LocalDateTime;

import javax.swing.JTextField;

import view.controller.ControllerTelaJustificativaPonto;

public class TelaJustificativaPonto {
	
	private ControllerTelaJustificativaPonto controller = new ControllerTelaJustificativaPonto();
	
	private JTextField dataHoraEntrada;
	
	private JTextField justificativa;
	
	private JTextField matricula;
	
	public void justificarPonto(LocalDateTime dataHoraEntrada, String justificar, long matricula) throws Exception{
		controller.justificarPonto(dataHoraEntrada, justificar, matricula);
	}
	
}
