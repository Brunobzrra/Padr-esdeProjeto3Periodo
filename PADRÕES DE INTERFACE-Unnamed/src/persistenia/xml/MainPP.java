package persistenia.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import model.projetos.Edital;

public class MainPP {

	public static void main(String[] args) {
		DAOXMLEdital xml = new DAOXMLEdital();
		Edital edital = new Edital();
		edital.setNome("Editalzin");
		edital.setAtivo(true);
		edital.setDataInicio(new Date(System.currentTimeMillis()));
		Date data = new Date(System.currentTimeMillis());
		Edital edital2 = new Edital();
		edital2.setNome("editalzao");
		edital2.setAtivo(true);
//		String dataRecebida = "23/11/2015";
//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//		Date dataFormatada = null;
//		try {
//			dataFormatada = formato.parse(dataRecebida);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		edital2.setDataInicio(dataFormatada);
		Date tempo = new Date(System.currentTimeMillis());
		edital2.setDataInicio(tempo);
		DAOXMLEdital xml2 = new DAOXMLEdital();
		String[] atributos = { "nome", "dataDeInicio" };
		Object[] valores = { "Editalzin", data };
//		xml.limpar();
		Edital ed = new Edital();
		ed.setNome("Uva");
		ed.setAtivo(true);
		ed.setDataInicio(tempo);
		xml2.criar(ed);
		xml2.criar(edital);
		xml2.criar(edital2);
		
		Set<Edital> recuperado = xml.consultarAnd(atributos, valores);
		Set<Edital> recuperadoOR = xml.consultarOr(atributos, valores);
		for (Edital edital3 : recuperado) {
			System.out.println(edital3.getNome());
		}
		System.out.println("------------------------------------------------------------------");
		for (Edital edital4 : recuperadoOR) {
			System.out.println(edital4.getNome());
		}
	}

}
