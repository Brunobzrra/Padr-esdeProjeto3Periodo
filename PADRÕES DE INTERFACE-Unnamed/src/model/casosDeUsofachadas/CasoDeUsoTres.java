package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import model.projetos.Grupo;

import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 3
public class CasoDeUsoTres {

	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception {
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Grupo grupo = new Grupo(nome, linkCNPq);
			daoGrupo.criar(grupo);
			return;
		}
		throw new Exception("Membros que não forem administradores não podem criar grupos");
	}

	public void removerGrupo(long matricula, String linkCNPq) throws Exception{
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Grupo grupoRecuperado = daoGrupo.recuperarPorIndentificador(linkCNPq);
			if (grupoRecuperado.getItens().isEmpty()) {
				daoGrupo.remover(grupoRecuperado);
				return;
			}
		}
		throw new Exception("Membros que não forem administradores não podem remover grupos");
	}
	//tirar duvida sobre este metodo
	public void atualizarrGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) throws Exception {
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Grupo grupoRecuperado = daoGrupo.recuperarPorIndentificador(linkCNPq);
			Grupo grupoAntigo = grupoRecuperado;
			grupoRecuperado.setLinkCNPq(nomeNovo);
			grupoRecuperado.setNome(nomeNovo);
			daoGrupo.atualizar(grupoAntigo, grupoRecuperado);
			return;
		}
		throw new Exception("Membros que não forem administradores não podem atualizar grupos");
	}

}
