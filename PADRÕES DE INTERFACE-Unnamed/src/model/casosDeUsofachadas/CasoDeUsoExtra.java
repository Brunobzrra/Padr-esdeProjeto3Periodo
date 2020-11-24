package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import persistencia.xml.DAOXMLMembroConta;

public class CasoDeUsoExtra {
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public Object[] recuperarMembro(long matricula) {
		Membro membro = daoMembro.recuperarPorIndentificador(matricula);
		Object[] dados = { membro.getNome(), membro.getEmail(), membro.getSenha() };
		return dados;
	}
}
