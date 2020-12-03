package view.abstract_factory;

/**
 * Interface uniformizadora do produto entregue, que no caso eh a
 * TelaAutenticacao
 * 
 * 
 * @author bruno
 */

public interface InterfaceTelaAutenticacao {
	/**
	 * metodo para o usuario logar no sistema com a base de dados selecionada
	 * 
	 * @param login, senha, tipoProvedor
	 */
	public abstract void fazerLogin(String login, String senha, String tipoProvedor) throws Exception;

	/**
	 * Metodo usado para um membro logado realizar o logout.
	 * 
	 * @param login
	 */

	public abstract void fazerLogout(String login) throws Exception;
}
