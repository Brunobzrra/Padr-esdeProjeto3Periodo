package view.projetos.builder;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;

/**
 * Interface uniformizadora para a montagem de um relatorio
 * 
 * @author bruno
 */
public interface InterfaceDeMontagemRelatorio {

	public void iniciarMontagem();

	public void montarCorpoRelatorio(Projeto projeto);

	public void montarCorpoRelatorio(Edital edital);

	public void montarCorpoRelatorio(Grupo grupo);

	public void finalizarMontagem();
}
