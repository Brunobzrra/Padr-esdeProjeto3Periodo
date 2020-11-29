package view.autenticacao;

public interface InterfaceTelaCriarConta {
	
	public abstract void cadastrarMembro(String nome, long matricula, String email, String senha) throws Exception;

	public abstract void atualizarMembro(long matricula, long matriculaNovo, String nomeNovo, String emailNovo, String senhaNova)
			throws Exception;

	public abstract Object[] recuperarMembro(long matricula);
	
}
