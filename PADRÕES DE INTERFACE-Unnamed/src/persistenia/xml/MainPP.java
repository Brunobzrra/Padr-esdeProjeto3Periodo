package persistenia.xml;

import model.projetos.Edital;

public class MainPP {

	public static void main(String[] args) {
			Edital edital = new Edital();
			DAOXMLEdital xml = new DAOXMLEdital();
			System.out.println(xml.criar(edital));
			System.out.println(xml.criar(new Edital()));
			xml.remover(1);
			xml.criar(edital);
			xml.atualizar(2, new Edital());

	}

}
