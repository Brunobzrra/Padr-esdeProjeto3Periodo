package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso6 {
	public static void main(String[] args) {
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		Object[] valores = { "brunin", "fan@gmail.com" };
		String[] atributos = { "nome", "email" };
		Set<Membro> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro membro = (Membro) recuperados[0];
		Date agora = new Date(System.currentTimeMillis());
		Date termino = new Date("02/02/2021");
		Participacao participacao = new Participacao(agora, termino, (short) 200, (short) 10, (short) 30, true);

		
	}
}
