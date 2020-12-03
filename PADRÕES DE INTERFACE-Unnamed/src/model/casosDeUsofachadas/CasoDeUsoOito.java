package model.casosDeUsofachadas;

import java.util.logging.Level;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import model.utilitarios.LoggerProjeto;
import view.projetos.builder.DiretorDeMontagemDeRelatorio;
import view.projetos.builder.MontadorRelatorioProjetoHTML;
import view.projetos.builder.MontadorRelatorioSwing;

//caso de uso 8
public class CasoDeUsoOito {

	/**
	 * gera instring equivalente a um html
	 * 
	 * @throws Exception
	 */
	public StringBuffer gerarRelatorio(String op, String valor) throws Exception {
		DiretorDeMontagemDeRelatorio diretor = new DiretorDeMontagemDeRelatorio(new MontadorRelatorioSwing());
		if (valor.equals("HTML")) {
			diretor.setMontadorDeRelatorio(new MontadorRelatorioProjetoHTML(""));
		}
		diretor.montarRelatorioCompleto(op);
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Gerando relatorio em texto");
		return gerarHtml(new CasoDeUsoExtra().recuperarProjetoComponente(valor));
	}

	public StringBuffer gerarHtml(ProjetoComponente componente) throws Exception {
		StringBuffer texto = new StringBuffer();
		texto.append(String.format(
				"<html>\n<head>\n<title>Relatorio Do Item</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n"));
		texto.append(String.format("<span>" + componente.toString() + "</span><br>\n"));

		texto.append("</body>\n</html>");
		LoggerProjeto.getInstance().getLogger().warning("Relatorio gerado");

		System.out.println(texto);
		return texto;
	}

	public String toStringHTML(Projeto projeto) {
		String texto = String.format("<span>Projeto %s </span><br>\n<span>Aporte Custeio Reais %s </span><br>\n"
				+ "<span>Aporte Capital Reais %s </span><br>\n<span>Gasto Executado Custeio Reais %s </span><br>"
				+ "\n<span>gasto Executado Capital Reais %s </span><br>\n<span>Membros </span><br>\n",
				projeto.getNome(), projeto.getAporteCusteioReais(), projeto.getAporteCapitalReais(),
				projeto.getAporteCusteioReais(), projeto.getAporteCapitalReais());
		for (ProjetoComponente projetoComponente : projeto.getItens()) {
			texto += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
		}
		return texto;
	}

	public String toStringHTML(Edital edital) {
		String texto = String.format(
				"<span>Edital %s </span><br>\n<span>Data de Inicio %s </span><br>\n<span>Data Termino %s </span><br>\n<span>Grupos </span><br>\n",
				edital.getNome(), edital.getDataInicio().toString(), edital.getDataTermino().toString());
		String grupos = "";
		String projetos = "";
		for (ProjetoComponente projetoComponente : edital.getItens()) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.GRUPO) {
				grupos += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			} else {
				projetos += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			}
		}
		texto += grupos + String.format("<span>Projetos </span><br>\n" + projetos);
		return texto;
	}

	public String toStringHTML(Grupo grupo) {
		String texto = String.format(
				"<span>Grupo %s </span><br>\n<span>Data de Criação %s </span><br>\n<span>linkCNPq %s </span><br>\n<span>Membros </span><br>\n",
				grupo.getNome(), grupo.getDataCriacao().toString(), grupo.getLinkCNPq());
		String membros = "";
		String projetos = "";
		for (ProjetoComponente projetoComponente : grupo.getItens()) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.MEMBRO) {
				membros += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			} else {
				projetos += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			}
		}
		texto += membros + String.format("<span>Projetos </span><br>\n" + projetos);
		return texto;
	}
}