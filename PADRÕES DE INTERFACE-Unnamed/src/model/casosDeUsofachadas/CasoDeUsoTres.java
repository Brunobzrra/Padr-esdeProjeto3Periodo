package model.casosDeUsofachadas;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.autenticacao.Membro;
import model.projetos.Grupo;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 3
public class CasoDeUsoTres {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Adicionando um novo grupo");
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		LoggerProjeto.getInstance().getLogger().info("Verificando se o membro recuperado eh admin");
		if (membro != null) {
			Grupo grupo = new Grupo(nome, linkCNPq);
			daoGrupo.criar(grupo);
			LoggerProjeto.getInstance().getLogger().warning("Grupo criado com sucesso");
			return;
		}
		LoggerProjeto.getInstance().getLogger().severe("erro, membro nao administrador nao pode adicionargrupo");
		throw new Exception("Membros que não forem administradores não podem criar grupos");
	}

	public void removerGrupo(long matricula, String linkCNPq) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Removendo um grupo");
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		LoggerProjeto.getInstance().getLogger().info("Verificando se o membro eh adm");
		if (membro != null) {
			Grupo grupoRecuperado = daoGrupo.recuperarPorIndentificador(linkCNPq);
			if (grupoRecuperado.getItens().isEmpty()) {
				daoGrupo.remover(grupoRecuperado);
				LoggerProjeto.getInstance().getLogger().warning("Grupo removido");
				return;
			}
		}
		throw new Exception("Membros que não forem administradores não podem remover grupos");
	}

	// tirar duvida sobre este metodo
	public void atualizarrGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo)
			throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Atualizando grupo");
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		LoggerProjeto.getInstance().getLogger().info("Verificando se o membro eh adm");
		if (membro != null) {
			Grupo grupoRecuperado = daoGrupo.recuperarPorIndentificador(linkCNPq);
			Grupo grupoAntigo = grupoRecuperado;
			grupoRecuperado.setLinkCNPq(nomeNovo);
			grupoRecuperado.setNome(nomeNovo);
			daoGrupo.atualizar(grupoAntigo, grupoRecuperado);
			logger.warning("Grupo atualizado com sucesso");
			return;
		}
		LoggerProjeto.getInstance().getLogger().severe("Membro comum nao pode atualizar grupo");
		throw new Exception("Membros que não forem administradores não podem atualizar grupos");
	}

}
