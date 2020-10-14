package model.clientes;

import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.TipoProvedorAutenticacao;
import model.casosDeUsofachadas.CasoDeUsoDoisAutenticacaoDeLogin;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso2 {
	public static void main(String[] args) {
	
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		Object[] valores = 	{"brunin", "fan@gmail.com"};
		String[] atributos = {"nome", "email"};
		Set<Membro> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro m = (Membro) recuperados[0];
		CasoDeUsoDoisAutenticacaoDeLogin caso2 = new CasoDeUsoDoisAutenticacaoDeLogin(m);
		caso2.selecionarFormaDeAutenticacao(TipoProvedorAutenticacao.POP3);
	}
}
