package persistenia.xml;

import model.projetos.Edital;

public class MainPP {

	public static void main(String[] args) {
			System.out.println("Test Antonio é corno!!!");
			Edital a = new Edital();
			DAOXMLEdital xml = new DAOXMLEdital();
			System.out.println(xml.criar(a));
			System.out.println(xml.criar(new Edital()));
			xml.remover(1);
			xml.criar(a);
			xml.atualizar(2, new Edital());
			
		

	}

}
