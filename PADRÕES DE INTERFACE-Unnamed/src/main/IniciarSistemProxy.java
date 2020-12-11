package main;

import javax.swing.JOptionPane;

import ponto.model.projetos.AplicacaoServidora;
import view.TelaPontoSwing;

public class IniciarSistemProxy {
	public static void iniciar() {
		try {
			new AplicacaoServidora().iniciar();
			new TelaPontoSwing();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi possivel iniciar a opera��o!");
		}
	}

	public static void main(String[] args) {
		iniciar();
	}
}
