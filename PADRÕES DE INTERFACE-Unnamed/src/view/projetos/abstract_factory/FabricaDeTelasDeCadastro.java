package view.projetos.abstract_factory;

/**
 * Interface de fabrica abstrata e uniformizadora para as fabricas concretas
 * 
 * @author bruno
 */

public interface FabricaDeTelasDeCadastro {

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de projeto
	 */
	public InterfaceTelaCadastroProjetos fabricarTelaCadastroProjetos();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de grupos
	 */
	public InterfaceTelaCadastroGrupos fabricarTelaCadastroGrupos();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de editais
	 */

	public InterfaceTelaCadastroEditais fabricarTelaCadastroEditais();

}
