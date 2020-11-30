package view.projetos.abstract_factory;

import java.util.Date;

public interface InterfaceTelaCadastroEditais {

	public void adcionarEdital(String nomeEdital, Date dataInicio, Date dataTermino, long matricula) throws Exception;

	public void atualizarEdital(String nomeEdital, String novoNome, Date dataInicio, Date dataTermino, long matricula)
			throws Exception;

	public void removerEdital(String nomeEdital, long matricula) throws Exception;

	public Object[] recuperarEdital(String nome);

	public Object[] mostrarEditaisDoUsuarioLogado() throws Exception;
}
