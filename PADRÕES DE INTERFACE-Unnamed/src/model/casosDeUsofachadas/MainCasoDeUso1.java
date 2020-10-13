package model.casosDeUsofachadas;

import java.util.Scanner;

public class MainCasoDeUso1 {

	public static void main(String[] args) {
		
		CasoDeUsoUmCadastroFachada cadastroMembro = new CasoDeUsoUmCadastroFachada();
		cadastroMembro.cadastrarMembro("brunin", Long.parseLong("1111"), "fan@gmail.com", "1212", "unnamed!");
	
	}

}
