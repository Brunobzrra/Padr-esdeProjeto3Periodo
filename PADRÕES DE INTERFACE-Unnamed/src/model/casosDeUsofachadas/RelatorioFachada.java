package model.casosDeUsofachadas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Set;

import model.projetos.Edital;
import persistenia.xml.DAOXMLEdital;

//caso de uso 8
public class RelatorioFachada {
	private DAOXMLEdital daoEdital = new DAOXMLEdital();
	/**
	 * gera instring equivalente a um html
	 */
	public void gerarRelatorio() {
		StringBuffer texto = new StringBuffer();
		texto.append(String.format(
				"<html>\n<head>\n<title>Relatorio de custos</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n"));
		String[] consulta = {"*"};
		Set<Edital> editais = daoEdital.consultarAnd(consulta, consulta);
		if (editais.size() != 0) {
			try {
				for (Edital edital : editais) {
							texto.append(String.format(
							"<span>Dados do edital %s -- Valor total de custeio não gasto %s -- valor total de capital não gasto %s</span><br>\n",
							edital.getNome(), edital.getCusteioReaisNaoGastoTotal(),
							edital.getCapitalReaiNaoGastoTotal()));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			texto.append(String.format("<span>Ainda não à nenhuma valor estabelecido!</span><br>\n"));
		}
		texto.append("</body>\n</html>");
		System.out.println(texto);
		gerarArquivoHTML(texto.toString());
		abrirRelatorio();
	}
	/*
	 * gera arquivo html
	 */
	private void gerarArquivoHTML(String texto) {
		FileWriter fw;
		try {
			fw = new FileWriter(new File("Relatorio.html"));
			fw.write(texto);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Abre arquivo html gerado
	 */
	private void abrirRelatorio() {

		String file = System.getProperty("user.dir") + "/Relatorio.html";
		try {
			Desktop.getDesktop().open(new File(file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void main(String[] args) {
		new RelatorioFachada().gerarRelatorio();
	}
}