package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import model.projetos.Edital;
import model.projetos.Grupo;
import persistencia.xml.DAOXMLEdital;
import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;
//Server para recuperar objetos persistidos
public class CasoDeUsoExtra {
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();
	private DAOXMLEdital daoEdital = new DAOXMLEdital();

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
	
	public Object[] recuperarEdital(String nome) {
		Edital edital = daoEdital.recuperarPorIndentificador(nome);
		Object[] dados = { edital.getNome()};
		return dados;
	}

}
