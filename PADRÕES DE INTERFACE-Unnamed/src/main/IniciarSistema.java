package main;

import model.casosDeUsofachadas.CasoDeUsoUm;
import view.abstract_factory.FabricaDeTelasSwing;
import view.abstract_factory.InterfaceFabricaDeTelas;

public class IniciarSistema {
	public static void iniciar() {
		InterfaceFabricaDeTelas fabrica = FabricaDeTelasSwing.getFabrica();
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
