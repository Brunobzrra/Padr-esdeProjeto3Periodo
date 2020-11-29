package view.autenticacao;

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
