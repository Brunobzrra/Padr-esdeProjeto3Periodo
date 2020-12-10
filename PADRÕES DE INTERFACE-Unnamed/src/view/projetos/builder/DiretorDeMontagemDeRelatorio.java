package view.projetos.builder;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import persistencia.xml.DAOXMLEdital;
import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLProjetoParticipacao;

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
		if (componente.getTipo() == TipoProjetoComponente.PROJETO) {
			Projeto projeto=(Projeto) componente;
			montadorDeRelatorio.montarCorpoRelatorio(projeto);
			montadorDeRelatorio.montarMembrosFilhos(projeto.getItens());
		} else if (componente.getTipo() == TipoProjetoComponente.GRUPO) {
			Grupo grupo=(Grupo) componente;
			montadorDeRelatorio.montarCorpoRelatorio(grupo);
			montadorDeRelatorio.montarMembrosFilhos(grupo.getItens());
			montadorDeRelatorio.montarProjetosFilhos(grupo.getItens());
		} else if (componente.getTipo() == TipoProjetoComponente.EDITAL) {
			Edital edital= (Edital) componente;
			montadorDeRelatorio.montarCorpoRelatorio(edital);
			montadorDeRelatorio.montarGruposFilhos(edital.getItens());
			montadorDeRelatorio.montarProjetosFilhos(edital.getItens());
		}
		montadorDeRelatorio.finalizarMontagem();
	}
	public void montarRelatorioSuperficial(ProjetoComponente componente) throws Exception {
		montadorDeRelatorio.iniciarMontagem();
		if (componente.getTipo() == TipoProjetoComponente.PROJETO) {
			Projeto projeto=(Projeto) componente;
			montadorDeRelatorio.montarCorpoRelatorio(projeto);
		} else if (componente.getTipo() == TipoProjetoComponente.GRUPO) {
			Grupo grupo=(Grupo) componente;
			montadorDeRelatorio.montarCorpoRelatorio(grupo);
		} else if (componente.getTipo() == TipoProjetoComponente.EDITAL) {
			Edital edital= (Edital) componente;
			montadorDeRelatorio.montarCorpoRelatorio(edital);
		}
		montadorDeRelatorio.finalizarMontagem();
	}

	public static void main(String[] args) {
		try {
			new DiretorDeMontagemDeRelatorio(new MontadorRelatorioProjetoHTML(""))
					.montarRelatorioSuperficial(new DAOXMLGrupo().recuperarPorIndentificador("25147555"));
			new DiretorDeMontagemDeRelatorio(new MontadorRelatorioProjetoHTML(""))
			.montarRelatorioSuperficial(new DAOXMLEdital().recuperarPorIndentificador("ffdssd"));
			new DiretorDeMontagemDeRelatorio(new MontadorRelatorioProjetoHTML(""))
			.montarRelatorioSuperficial(new DAOXMLProjetoParticipacao().recuperarPorIndentificador("projeto"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}