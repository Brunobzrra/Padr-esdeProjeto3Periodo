package persistenia.xml;

import model.projetos.Edital;

public class Main {

	public static void main(String[] args) {
			System.out.println("Test Antonio � corno!!!");
			Edital a = new Edital();
			DAOXMLEdital xml = new DAOXMLEdital();
			System.out.println(xml.criar(a));
			
			
		

	}

}
