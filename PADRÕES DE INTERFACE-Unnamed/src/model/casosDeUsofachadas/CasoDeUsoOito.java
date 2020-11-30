package model.casosDeUsofachadas;

import model.projetos.ProjetoComponente;

//caso de uso 8
public class CasoDeUsoOito {

	/**
	 * gera instring equivalente a um html
	 */
	public StringBuffer gerarRelatorio(ProjetoComponente componente) {
		StringBuffer texto = new StringBuffer();
		texto.append(String.format(
				"<html>\n<head>\n<title>Relatorio Do Item</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n"));
		texto.append(String.format(
				"<span>"+componente.toStringHTML()+"</span><br>\n"));

		texto.append("</body>\n</html>");
		System.out.println(texto);
		return texto;
	}
	
}