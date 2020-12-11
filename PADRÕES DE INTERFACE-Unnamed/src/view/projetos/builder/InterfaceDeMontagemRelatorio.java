package view.projetos.builder;

import java.util.ArrayList;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;

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

	public void montarProjetosFilhos(ArrayList<ProjetoComponente> componentes);

	public void montarEditaisFilhos(ArrayList<ProjetoComponente> componentes);

	public void montarMembrosFilhos(ArrayList<ProjetoComponente> componentes);

	public void montarGruposFilhos(ArrayList<ProjetoComponente> componentes);

	public Object finalizarMontagem();
	
	public void mostrarRelatorioConstruido();
}
