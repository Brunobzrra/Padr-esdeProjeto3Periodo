package view.autenticacao.abstract_factory;

/**
 * Interface uniformizadora para o produto fornecido, que eh a tela de criar
 * conta, fornecendo os metodos que devem ser implementados
 * 
 * @author bruno
 */

public interface InterfaceTelaCriarConta {

	/**
	 * Metodo para cadastrar um membro no sistema
	 * 
	 * @param nome, matricula, email, senha @exception
	 */
	public abstract void cadastrarMembro(String nome, long matricula, String email, String senha) throws Exception;

	/**
	 * Metodo para atualizar um membro no sistema
	 * 
	 * @param matricula, matriculaNovo, nomeNovo, emailNovo, senhaNova @exception
	 */

	public abstract void atualizarMembro(long matricula, long matriculaNovo, String nomeNovo, String emailNovo,
			String senhaNova) throws Exception;

	/**
	 * Metodo para recuperar um membro
	 * 
	 * @param matricula
	 */

	public abstract Object[] recuperarMembro(long matricula);

}
