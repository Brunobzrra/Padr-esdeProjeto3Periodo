package view.autenticacao.abstract_factory;

import view.autenticacao.TelaAutenticacao;
import view.autenticacao.TelaConfiguracaoAdmin;
import view.autenticacao.TelaCriarConta;

public class FabricaDeTelasSwing implements FabricaDeTelas{

	@Override
	public TelaAutenticacao fabricarTelaAutenticacao() {
	
		return new TelaAutenticacao();
	}

	@Override
	public TelaCriarConta fabricarTelaCriarConta() {
		
		return new TelaCriarConta();
	}

	@Override
	public TelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin() {
		
		return new TelaConfiguracaoAdmin();
	}

}
