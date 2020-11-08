package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import model.projetos.Grupo;

import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 3
public class CasoDeUsoTres {

	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public boolean adcionarGrupo(String nome, String linkCNPq, long matricula) {
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Grupo grupo = new Grupo(nome, linkCNPq);
			daoGrupo.criar(grupo);
			return true;
		}
		return false;
	}

	public boolean removerGrupo(long matricula, String linkCNPq) {
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Grupo grupoRecuperado = daoGrupo.recuperarPorIndentificador(linkCNPq);
			if (grupoRecuperado.getItens().isEmpty()) {
				daoGrupo.remover(grupoRecuperado);
				return true;
			}
		}
		return false;
	}
	//tirar duvida sobre este metodo
	public boolean atualizarrGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) {
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Grupo grupoRecuperado = daoGrupo.recuperarPorIndentificador(linkCNPq);
			Grupo grupoAntigo = grupoRecuperado;
			grupoRecuperado.setLinkCNPq(nomeNovo);
			grupoRecuperado.setNome(nomeNovo);
			daoGrupo.atualizar(grupoAntigo, grupoRecuperado);
			return true;
		}
		return false;
	}

}
