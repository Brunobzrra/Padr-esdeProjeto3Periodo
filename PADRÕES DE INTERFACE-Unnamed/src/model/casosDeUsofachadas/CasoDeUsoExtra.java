package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import model.projetos.Grupo;
import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;
//Server para recuperar objetos persistidos
public class CasoDeUsoExtra {
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();

	public Object[] recuperarMembro(long matricula) {
		Membro membro = daoMembro.recuperarPorIndentificador(matricula);
		Object[] dados = { membro.getNome(), membro.getEmail(), membro.getSenha() };
		return dados;
	}

	public Object[] recuperarGrupo(String linkCNPq) {
		Grupo grupo = daoGrupo.recuperarPorIndentificador(linkCNPq);
		Object[] dados = { grupo.getNome(), grupo.getLinkCNPq()};
		return dados;
	}

}
