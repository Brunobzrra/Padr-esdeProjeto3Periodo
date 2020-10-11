package model.casosDeUsofachadas;

import model.projetos.Edital;
import persistenia.xml.DAOXMLEdital;

//caso de uso 8
public class RelatorioFachada {
	private DAOXMLEdital daoEdital;

    public void gerarRelatorio() {
        StringBuffer texto = new StringBuffer();
        texto.append(String.format("<html>\n<head>\n<title>Relatorio de custos</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n"));
        String[] consulta = { "*" };
        try {
            for (Edital edital : daoEdital.consultarAnd(consulta, consulta)) {
                texto.append(String.format(
                        "<span>Dados do edital %s -- Valor total de custeio não gasto %s -- valor total de capital não gasto %s</span><br>\n",
                        edital.getNome(), edital.getCusteioReaisNaoGastoTotal(), edital.getCapitalReaiNaoGastoTotal()));
            }
        } catch (Exception e) {
            // TODO: handle exception
                texto.append(String.format("<span>Ainda não à nenhuma valor estabelecido!</span><br>\n"));
        }
        System.out.println(texto.append("</body>\n</html>"));

    }
}

