package view.autenticacao.abstract_factory;

import view.autenticacao.TelaAutenticacao;
import view.autenticacao.TelaConfiguracaoAdmin;
import view.autenticacao.TelaCriarConta;

public interface FabricaDeTelas {
	
	public abstract TelaAutenticacao fabricarTelaAutenticacao();
	
	public abstract TelaCriarConta fabricarTelaCriarConta();
	
	public abstract TelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin();
}
