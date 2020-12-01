package view.autenticacao.abstract_factory;

import view.autenticacao.TelaAutenticacao;
import view.autenticacao.TelaConfiguracaoAdmin;
import view.autenticacao.TelaCriarConta;

/**
 * Fabrica usada para a aplicacao do padrao abstract nas telas de
 * autenticacao/configuracao, possibilitando extensibilidade para outras API's
 * 
 * @author bruno
 */

public interface FabricaDeTelas {

	/**
	 * metodo para fabricar tela de autenticacao
	 */
	public abstract TelaAutenticacao fabricarTelaAutenticacao();

	/**
	 * metodo para fabricar tela para criacao de contas
	 */
	public abstract TelaCriarConta fabricarTelaCriarConta();

	/**
	 * metodo para fabricar tela para configurar admins
	 */
	public abstract TelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin();
}
