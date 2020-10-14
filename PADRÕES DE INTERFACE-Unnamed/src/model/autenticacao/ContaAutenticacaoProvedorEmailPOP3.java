package model.autenticacao;

import java.util.Set;

import model.utilitarios.AutenticacaoPOP3;
import persistenia.xml.DAOXMLMembroConta;

public class ContaAutenticacaoProvedorEmailPOP3 extends ContaBridge {

	private String provedorHost;

	private String provedorPorta;
	
	/*
	 *Autentica um membro via POP3, validando seu email caso existente, e consultando ele o
	 *mesmo via BD posteriormente, caso ele passe pelos dois testes, é retornado 
	 *@param email, senha*/

	public Membro autenticar(String email, String senha) {
		if (AutenticacaoPOP3.check(email, senha, provedorHost, provedorPorta)) {
			DAOXMLMembroConta dao = new DAOXMLMembroConta();
			String[] atributos = { "email", "senha" };
			String[] valores = { email, senha };

			Set<Membro> membros = dao.consultarAnd(atributos, valores);
			for (Membro membro : membros) {
				return membro;
			}
		}
		return null;
	}

}
