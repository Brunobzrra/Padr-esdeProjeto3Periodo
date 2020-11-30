package main;

import model.casosDeUsofachadas.CasoDeUsoUm;
import view.autenticacao.abstract_factory.FabricaDeTelas;
import view.autenticacao.abstract_factory.FabricaDeTelasSwing;

public class IniciarSistema {
	public static void iniciar() {
		FabricaDeTelas fabrica = new FabricaDeTelasSwing();
		CasoDeUsoUm casoDeUso = new CasoDeUsoUm();
		if(casoDeUso.isVazia()) {
			fabrica.fabricarTelaCriarConta();
		}else {
			fabrica.fabricarTelaAutenticacao();
		}
	}
	public static void main(String[] args) {
		iniciar();

	}

}
