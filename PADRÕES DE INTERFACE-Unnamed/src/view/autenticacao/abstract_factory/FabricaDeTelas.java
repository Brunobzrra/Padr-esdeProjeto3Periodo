package view.autenticacao.abstract_factory;

/**
 * Fabrica usada para a aplicacao do padrao abstract nas telas de
 * autenticacao/configuracao, possibilitando extensibilidade para outras API's
 * 
 * @author bruno
 */

public interface FabricaDeTelas{

	/**
	 * metodo para fabricar tela de autenticacao
	 */
	public abstract InterfaceTelaAutenticacao fabricarTelaAutenticacao();

	/**
	 * metodo para fabricar tela para criacao de contas
	 */
	public abstract InterfaceTelaCriarConta fabricarTelaCriarConta();

	/**
	 * metodo para fabricar tela para configurar admins
	 */
	public abstract InterfaceTelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin();
}
