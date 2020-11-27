package model.casosDeUsofachadas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Set;

import model.projetos.Edital;
import model.projetos.ProjetoComponente;
import persistencia.xml.DAOXMLEdital;

//caso de uso 8
public class CasoDeUsoOito {
	private DAOXMLEdital daoEdital = new DAOXMLEdital();

	/**
	 * gera instring equivalente a um html
	 */
	public StringBuffer gerarRelatorio(ProjetoComponente componente) {
		StringBuffer texto = new StringBuffer();
		texto.append(String.format(
				"<html>\n<head>\n<title>Relatorio de custos</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n"));
		texto.append(String.format(
				"<span>"+componente.toString()+"</span><br>\n"));

		texto.append("</body>\n</html>");
		System.out.println(texto);
		return texto;
	}
	
}