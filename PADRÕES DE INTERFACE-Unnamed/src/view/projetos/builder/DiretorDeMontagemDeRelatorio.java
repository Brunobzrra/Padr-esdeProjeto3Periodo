package view.projetos.builder;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;

/**
 * Diretor de montagem dos relatorios que fazem uso da interface de Montagem.
 * 
 * Não colocamos mais metodos de edição no diretor por não prevermos mais
 * nenhuma modo de criação, no entanto o diretor pode se quiser tem mais metodos
 * para manipular os relatorios de diferentes maneiras.
 * 
 * @author bruno
 */
public class DiretorDeMontagemDeRelatorio {
	private InterfaceDeMontagemRelatorio montadorDeRelatorio;
	private Object produtoConstruido;

	public DiretorDeMontagemDeRelatorio(InterfaceDeMontagemRelatorio montadorDeRelatorio) {
		super();
		this.montadorDeRelatorio = montadorDeRelatorio;
	}

	public void setMontadorDeRelatorio(InterfaceDeMontagemRelatorio montadorDeRelatorio) {
		this.montadorDeRelatorio = montadorDeRelatorio;
	}

	/**
	 * Metodo ultilizado para montar um relatorio com o componente.
	 * 
	 * @param componente
	 */
	public void montarRelatorioCompleto(ProjetoComponente componente) throws Exception {
		montadorDeRelatorio.iniciarMontagem();
		if (componente.getTipo() == TipoProjetoComponente.PROJETO) {
			Projeto projeto = (Projeto) componente;
			montadorDeRelatorio.montarCorpoRelatorio(projeto);
			montadorDeRelatorio.montarMembrosFilhos(projeto.getItens());
		} else if (componente.getTipo() == TipoProjetoComponente.GRUPO) {
			Grupo grupo = (Grupo) componente;
			montadorDeRelatorio.montarCorpoRelatorio(grupo);
			montadorDeRelatorio.montarMembrosFilhos(grupo.getItens());
			montadorDeRelatorio.montarProjetosFilhos(grupo.getItens());
		} else if (componente.getTipo() == TipoProjetoComponente.EDITAL) {
			Edital edital = (Edital) componente;
			montadorDeRelatorio.montarCorpoRelatorio(edital);
			montadorDeRelatorio.montarGruposFilhos(edital.getItens());
			montadorDeRelatorio.montarProjetosFilhos(edital.getItens());
		}
		setProdutoConstruido(montadorDeRelatorio.finalizarMontagem());
		montadorDeRelatorio.mostrarRelatorioConstruido();
	}

	public void montarRelatorioSuperficial(ProjetoComponente componente) throws Exception {
		montadorDeRelatorio.iniciarMontagem();
		if (componente.getTipo() == TipoProjetoComponente.PROJETO) {
			Projeto projeto = (Projeto) componente;
			montadorDeRelatorio.montarCorpoRelatorio(projeto);
		} else if (componente.getTipo() == TipoProjetoComponente.GRUPO) {
			Grupo grupo = (Grupo) componente;
			montadorDeRelatorio.montarCorpoRelatorio(grupo);
		} else if (componente.getTipo() == TipoProjetoComponente.EDITAL) {
			Edital edital = (Edital) componente;
			montadorDeRelatorio.montarCorpoRelatorio(edital);
		}
		setProdutoConstruido(montadorDeRelatorio.finalizarMontagem());
		montadorDeRelatorio.mostrarRelatorioConstruido();
	}

	public void mostrarRelatorio() {
		try {
			montadorDeRelatorio.mostrarRelatorioConstruido();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getProdutoConstruido() {
		return produtoConstruido;
	}

	public void setProdutoConstruido(Object produtoConstruido) {
		this.produtoConstruido = produtoConstruido;
	}
}