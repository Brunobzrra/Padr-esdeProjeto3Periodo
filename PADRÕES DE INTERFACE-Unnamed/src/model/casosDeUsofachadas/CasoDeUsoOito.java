package model.casosDeUsofachadas;

import java.util.logging.Level;

import model.projetos.ProjetoComponente;
import model.utilitarios.LoggerProjeto;

//caso de uso 8
public class CasoDeUsoOito {

	/**
	 * gera instring equivalente a um html
	 * 
	 * @throws Exception
	 */
	public StringBuffer gerarRelatorio(ProjetoComponente componente) throws Exception {

		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Gerando relatorio em texto");

		StringBuffer texto = new StringBuffer();
		texto.append(String.format(
				"<html>\n<head>\n<title>Relatorio Do Item</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n"));
		texto.append(String.format("<span>" + componente.toStringHTML() + "</span><br>\n"));

		texto.append("</body>\n</html>");
		LoggerProjeto.getInstance().getLogger().warning("Relatorio gerado");

		System.out.println(texto);
		return texto;
	}

}