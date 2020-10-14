package model.clientes;

import model.casosDeUsofachadas.CasoDeUsoUmCadastroFachada;

public class MainCasoDeUso1 {

	public static void main(String[] args) {
		
		CasoDeUsoUmCadastroFachada cadastroMembro = new CasoDeUsoUmCadastroFachada();
		cadastroMembro.cadastrarMembro("bruno", Long.parseLong("1121"), "fananitadz@gmail.com", "1212", "unnamed!");
	
	}

}
