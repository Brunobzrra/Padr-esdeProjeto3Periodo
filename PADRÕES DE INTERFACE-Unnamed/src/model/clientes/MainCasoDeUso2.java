package model.clientes;

import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.TipoProvedorAutenticacao;
import model.casosDeUsofachadas.CasoDeUsoDoisAutenticacaoDeLogin;
import persistencia.xml.DAOXMLMembroConta;

public class MainCasoDeUso2 {
	public static void main(String[] args) {
	
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		Object[] valores = 	{"bruno", "fananitadz@gmail.com"};
		String[] atributos = {"nome", "email"};
		Set<Membro> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro m = (Membro) recuperados[0];
		CasoDeUsoDoisAutenticacaoDeLogin caso2;
		try {
			caso2 = new CasoDeUsoDoisAutenticacaoDeLogin(1);
			caso2.selecionarFormaDeAutenticacao(TipoProvedorAutenticacao.POP3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
