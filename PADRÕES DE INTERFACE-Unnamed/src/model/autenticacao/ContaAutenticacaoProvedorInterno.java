package model.autenticacao;

import java.util.Set;

import persistenia.xml.DAOXMLMembroConta;

public class ContaAutenticacaoProvedorInterno extends ContaBridge {

	public Membro autenticar(String email, String senha) {
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		String[] atributos = { "email", "senha" };
		String[] valores = { email, senha };

		Set<Membro> membros = dao.consultarAnd(atributos, valores);
		for (Membro membro : membros) {
			return membro;
		}
		return null;
	}

}
