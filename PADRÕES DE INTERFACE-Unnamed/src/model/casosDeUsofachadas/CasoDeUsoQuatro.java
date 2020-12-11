package model.casosDeUsofachadas;

import java.util.Date;
import java.util.logging.Level;

import model.autenticacao.Membro;
import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLEdital;
import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 4
public class CasoDeUsoQuatro {

	private DAOXMLEdital daoEdital = new DAOXMLEdital();
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();
	private DAOXMLProjetoParticipacao daoProjeto = new DAOXMLProjetoParticipacao();

	public void adcionarEdital(String nomeEdital, Date dataInicio, Date dataTermino, long matricula) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Adicionando novo edital");
		LoggerProjeto.getInstance().getLogger().info("Verificando permissoes.");
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			if (!daoEdital.criar(nomeEdital, dataTermino, dataTermino,membro)) {
				LoggerProjeto.getInstance().getLogger().severe("Edital existente");
				throw new Exception("Este edital ja existe!");
			}
		}
		LoggerProjeto.getInstance().getLogger().warning("Edital adicionado");
	}

	public void atualizarEdital(String nomeEdital, String novoNome, Date dataInicio, Date dataTermino, long matricula)
			throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Atualizando um edital existente");
		LoggerProjeto.getInstance().getLogger().info("Verificando permissoes.");
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			Edital editalAntigo = daoEdital.recuperarPorIndentificador(nomeEdital);
			Edital editalAtualizado = daoEdital.recuperarPorIndentificador(nomeEdital);
			editalAtualizado.setNome(novoNome);
			editalAtualizado.setDataInicio(dataInicio);
			editalAtualizado.setDataTermino(dataTermino);
			if (!daoEdital.atualizar(editalAntigo, editalAtualizado)) {
				LoggerProjeto.getInstance().getLogger().severe("Edital inexistente.");
				throw new Exception("Este edital não existe!");
			}
		}
		LoggerProjeto.getInstance().getLogger().warning("Edital adicionado com sucesso");
	}

	public void removerEdital(String nomeEdital, long matricula) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Removendo um edital");
		LoggerProjeto.getInstance().getLogger().info("Verificando permissoes.");
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		Edital edital = daoEdital.recuperarPorIndentificador(nomeEdital);
		if (membro != null) {
			for (ProjetoComponente projetoComponente : edital.getItens()) {
				if (projetoComponente.getTipo() == TipoProjetoComponente.PROJETO) {
					LoggerProjeto.getInstance().getLogger().severe("edital possui projetos");
					throw new Exception("Este edital contem projetos atribuidos a ele!");
				}
			}
			LoggerProjeto.getInstance().getLogger().warning("edital removido com sucesso");

			daoEdital.remover(edital);
		}
	}
	public void adcionarGrupo(String nomeGrupo,String nomeEdital) throws Exception {
		Grupo grupo=daoGrupo.recuperarPorNome(nomeGrupo);
		Edital edital= daoEdital.recuperarPorIndentificador(nomeEdital);
		edital.adicionar(grupo);
		daoGrupo.atualizar(grupo, grupo);
		daoEdital.atualizar(edital, edital);
	}
	public void removerGrupo(String nomeGrupo,String nomeEdital) throws Exception {
		Grupo grupo=daoGrupo.recuperarPorNome(nomeGrupo);
		Edital edital= daoEdital.recuperarPorIndentificador(nomeEdital);
		edital.remover(grupo);
		daoGrupo.atualizar(grupo, grupo);
		daoEdital.atualizar(edital, edital);
	}
	public void adcionarProjeto(String nomeProjeto,String nomeEdital) throws Exception {
		Projeto projeto=daoProjeto.recuperarPorIndentificador(nomeProjeto);
		Edital edital= daoEdital.recuperarPorIndentificador(nomeEdital);
		edital.adicionar(projeto);
		daoProjeto.atualizar(projeto, projeto);
		daoEdital.atualizar(edital, edital);
	}
	public void removerProjeto(String nomeProjeto,String nomeEdital) throws Exception {
		Projeto projeto=daoProjeto.recuperarPorIndentificador(nomeProjeto);
		Edital edital= daoEdital.recuperarPorIndentificador(nomeEdital);
		edital.remover(projeto);
		daoProjeto.atualizar(projeto, projeto);
		daoEdital.atualizar(edital, edital);
	}	
}
