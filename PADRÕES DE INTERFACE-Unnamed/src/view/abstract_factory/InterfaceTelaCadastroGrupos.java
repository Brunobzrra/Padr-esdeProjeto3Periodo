package view.abstract_factory;

/**
 * Interface uniformizadora do produto fornecido, que eh a tela de cadastro de
 * grupos
 * 
 * @author bruno
 */

public interface InterfaceTelaCadastroGrupos {

	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception;

	public void removerGrupo(long matricula, String linkCNPq) throws Exception;

	public void atualizarGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) throws Exception;

	public Object[] recuperarGrupo(String linkCNPq);

	public Object[] mostrarGruposDoUsuarioLogado() throws Exception;
}
