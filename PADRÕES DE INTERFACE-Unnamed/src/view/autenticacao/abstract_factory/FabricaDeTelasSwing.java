package view.autenticacao.abstract_factory;

import view.autenticacao.TelaAutenticacao;
import view.autenticacao.TelaConfiguracaoAdmin;
import view.autenticacao.TelaCriarConta;

/**
 * Fabrica de telas concreta para implementacao em swing usada
 * @author bruno
 * */

public class FabricaDeTelasSwing implements FabricaDeTelas{
	
	public TelaAutenticacao fabricarTelaAutenticacao() {
	
		return new TelaAutenticacao();
	}

	public TelaCriarConta fabricarTelaCriarConta() {
		
		return new TelaCriarConta();
	}

	public TelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin() {
		
		return new TelaConfiguracaoAdmin();
	}

}
