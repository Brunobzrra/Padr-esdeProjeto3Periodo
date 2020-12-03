package view.projetos.builder;

/**
 * Interface uniformizadora para a montagem de um relatorio
 * 
 * @author bruno
 */
public interface InterfaceDeMontagemRelatorio {

	public void montarRelatorio(String componente) throws Exception;

	public void iniciarMontagem();

	public void finalizarMontagem();
}
