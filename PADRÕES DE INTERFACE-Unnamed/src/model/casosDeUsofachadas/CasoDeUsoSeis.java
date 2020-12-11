package model.casosDeUsofachadas;

import java.util.Date;
import java.util.logging.Level;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.utilitarios.EnviarEmail;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 6
public class CasoDeUsoSeis {

	private DAOXMLProjetoParticipacao DAOProjPart = new DAOXMLProjetoParticipacao();
	private DAOXMLMembroConta daoMembroConta = new DAOXMLMembroConta();

	public void adicionarParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerEstrarNoProjeto,
			String nomeDoProjeto, Date dataInicio, float aporteCusteioMensalReais, short qtdMesesCusteados,
			short qtdMesesPagos) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Tentando adicionar uma participacao");
		Projeto projeto = DAOProjPart.recuperarPorIndentificador(nomeDoProjeto);
		Membro membroNovo = daoMembroConta.recuperarPorIndentificador(matriculaDoMembroQueQuerEstrarNoProjeto);
		if (projeto != null && membroNovo != null) {
			LoggerProjeto.getInstance().getLogger()
					.info("Verificando se o membro solicitante eh coordenador do projeto");
			Membro membro = projeto.getCordenador();
			if (membro.getMatricula() == matriculaDoCordenador) {
				Participacao participa = new Participacao(dataInicio, aporteCusteioMensalReais, qtdMesesCusteados,
						qtdMesesPagos, false);
				projeto.adicionar(participa);
				membroNovo.adicionar(participa);
				DAOProjPart.atualizar(projeto, projeto);
				daoMembroConta.atualizar(membroNovo, membroNovo);
				LoggerProjeto.getInstance().getLogger().warning("Participacao adicionada para " + membroNovo.getNome());

				EnviarEmail.enviarEmail(membro.getSenha(), membro.getEmail(), membroNovo.getEmail(),
						"Você foi adcionado no projeto " + projeto.getNome() + " pelo coordenador " + membro.getNome(),
						"Você foi adcionado em um projeto");
				return;
			}
		}
		LoggerProjeto.getInstance().getLogger().severe("Participacao nao adicionada");
		throw new Exception("Não foi possivel adicionar a participação!");
	}

	public void removerParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerRemover,
			String nomeDoProjeto) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Tentando remover uma participacao");
		Projeto projeto = DAOProjPart.recuperarPorIndentificador(nomeDoProjeto);
		Membro membroNovo = daoMembroConta.recuperarPorIndentificador(matriculaDoMembroQueQuerRemover);
		if (projeto != null && membroNovo != null) {
			LoggerProjeto.getInstance().getLogger()
					.info("Verificando se o membro solicitante eh coordenador do projeto");
			Membro membro = projeto.getCordenador();
			if (membro.getMatricula() == matriculaDoCordenador) {
				Participacao participacao = projeto.getMembro(membroNovo.getMatricula());
				membroNovo.remover(participacao);
				projeto.remover(participacao);
				DAOProjPart.atualizar(projeto, projeto);
				daoMembroConta.atualizar(membroNovo, membroNovo);
				LoggerProjeto.getInstance().getLogger().warning("Participacao removida para " + membroNovo.getNome());

				EnviarEmail.enviarEmail(membro.getSenha(), membro.getEmail(), membroNovo.getEmail(),
						"Você foi removido no projeto " + projeto.getNome() + " pelo coordenador " + membro.getNome(),
						"Você foi removido em um projeto");
			}
		}
		LoggerProjeto.getInstance().getLogger().severe("Participacao nao removida");
		throw new Exception("Não foi possivel adcionar a participação!");
	}
}
