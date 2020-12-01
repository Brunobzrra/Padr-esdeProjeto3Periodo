package view.autenticacao.abstract_factory;

import view.autenticacao.TelaAutenticacao;
import view.autenticacao.TelaConfiguracaoAdmin;
import view.autenticacao.TelaCriarConta;

/**
 * Fabrica de telas concreta para implementacao em swing usada
 * @author bruno
 * */

public class FabricaDeTelasSwing implements FabricaDeTelas{
	
	public InterfaceTelaAutenticacao fabricarTelaAutenticacao() {
	
		return new TelaAutenticacao();
	}

	public InterfaceTelaCriarConta fabricarTelaCriarConta() {
		
		return new TelaCriarConta();
	}

	public InterfaceTelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin() {
		
		return new TelaConfiguracaoAdmin();
	}

}
