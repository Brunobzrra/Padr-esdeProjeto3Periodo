package model.casosDeUsofachadas;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Grupo;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso3 {

	public static void main(String[] args) {
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		Object[] valores = 	{"brunao", "fanannitadz@gmail.com"};
		String[] atributos = {"nome", "email"};
		Set<Membro> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro m = (Membro) recuperados[0];
		Grupo g = new Grupo();
		GrupoFachada facade = new GrupoFachada();
		facade.setMembro(m);
		g.setLinkCNPq("cnpq.com.br");
		g.setNome("grupo dos mofi");
		Date agora = new Date(System.currentTimeMillis());
		g.setDataCriacao(agora);
		try {
			facade.adcionarGrupo(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
