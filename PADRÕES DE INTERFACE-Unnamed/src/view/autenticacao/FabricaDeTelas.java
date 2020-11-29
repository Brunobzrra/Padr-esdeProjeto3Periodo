package view.autenticacao;

public interface FabricaDeTelas {
	
	public abstract TelaAutenticacao fabricarTelaAutenticacao();
	
	public abstract TelaCriarConta fabricarTelaCriarConta();
	
	public abstract TelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin();
}
