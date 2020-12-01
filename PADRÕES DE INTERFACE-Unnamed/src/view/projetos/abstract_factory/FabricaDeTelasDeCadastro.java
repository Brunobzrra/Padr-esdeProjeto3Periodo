package view.projetos.abstract_factory;

import view.projetos.TelaCadastroEditais;
import view.projetos.TelaCadastroGrupos;
import view.projetos.TelaCadastroProjetos;

/**
 * Interface de fabrica abstrata e uniformizadora para as fabricas concretas
 * 
 * @author bruno
 */

public interface FabricaDeTelasDeCadastro {

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de projeto
	 */
	public abstract TelaCadastroProjetos fabricarTelaCadastroProjetos();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de grupos
	 */
	public abstract TelaCadastroGrupos fabricarTelaCadastroGrupos();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de editais
	 */

	public abstract TelaCadastroEditais fabricarTelaCadastroEditais();

}
