package persistenia.xml;

import java.util.Date;
import java.util.Set;

import model.projetos.Edital;

public class MainPP {

	public static void main(String[] args) {
			Edital edital = new Edital();
			edital.setNome("Editalzin");
			edital.setAtivo(true);
			edital.setDataInicio(new Date(System.currentTimeMillis()));
			Date data = new Date(System.currentTimeMillis());
			DAOXMLEdital xml = new DAOXMLEdital();
			String[] atributos = {"nome", "dataDeInicio"};
			Object[] valores = {"Editalzin", data};
//			xml.limpar();
			xml.criar(edital);
			xml.criar(edital);
			Set<Edital> recuperado = xml.consultarAnd(atributos, valores);
			for (Edital edital2 : recuperado) {
				System.out.println(edital2.getNome());
			}
	}

}
