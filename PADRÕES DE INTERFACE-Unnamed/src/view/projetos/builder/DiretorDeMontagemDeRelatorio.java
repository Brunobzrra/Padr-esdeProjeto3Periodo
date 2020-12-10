package view.projetos.builder;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;

/**
 * Diretor de montagem dos relatorios que fazem uso da interface de Montagem
 * 
 * @author bruno
 */
public class DiretorDeMontagemDeRelatorio {
	private InterfaceDeMontagemRelatorio montadorDeRelatorio;

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
		if(componente.getTipo()==TipoProjetoComponente.PROJETO) {
			montadorDeRelatorio.montarCorpoRelatorio((Projeto) componente);
		}else if(componente.getTipo()==TipoProjetoComponente.GRUPO) {
			montadorDeRelatorio.montarCorpoRelatorio((Grupo) componente);
		}else if(componente.getTipo()==TipoProjetoComponente.EDITAL) {
			montadorDeRelatorio.montarCorpoRelatorio((Edital) componente);
		}
		montadorDeRelatorio.finalizarMontagem();
	}

//	/**
//	 * Metodo ultilizado para montar um arquivo com texto.
//	 * 
//	 * @param texto
//	 */
//	public void iniciarMontagem() {
//		
//	}
//
//	public void montarCorpoRelatorio(Projeto projeto) {
//		
//	}
//
//	public void montarCorpoRelatorio(Edital edital) {
//		
//	}
//
//	public void montarCorpoRelatorio(Grupo grupo) {
//		
//	}
//
//	public void finalizarMontagem() {
//		
//	}
}