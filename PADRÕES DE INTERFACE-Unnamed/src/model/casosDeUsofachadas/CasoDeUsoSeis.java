package model.casosDeUsofachadas;

import java.util.Date;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.utilitarios.EnviarEmail;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 6
public class CasoDeUsoSeis {

	private DAOXMLProjetoParticipacao DAOProjPart = new DAOXMLProjetoParticipacao();
	private DAOXMLMembroConta daoMembroConta = new DAOXMLMembroConta();

	public void adicionarParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerEstrarNoProjeto,
			String nomeDoProjeto, Date dataInicio, float aporteCusteioMensalReais, short qtdMesesCusteados,
			short qtdMesesPagos) throws Exception {
		Projeto projeto = DAOProjPart.recuperarPorIndentificador(nomeDoProjeto);
		Membro membroNovo = daoMembroConta.recuperarPorIndentificador(matriculaDoMembroQueQuerEstrarNoProjeto);
		if (projeto != null && membroNovo != null) {
			Membro membro = projeto.getCordenador();
			if (membro.getMatricula() == matriculaDoCordenador) {
				Participacao participa = new Participacao(dataInicio, aporteCusteioMensalReais, qtdMesesCusteados,
						qtdMesesPagos, false);
				projeto.adicionar(participa);
				membroNovo.adicionar(participa);
				DAOProjPart.atualizar(projeto, projeto);
				daoMembroConta.atualizar(membroNovo, membroNovo);
				EnviarEmail.enviarEmail(membro.getSenha(), membro.getEmail(), membroNovo.getEmail(),
						"Você foi adcionado no projeto " + projeto.getNome() + " pelo coordenador " + membro.getNome(),
						"Você foi adcionado em um projeto");
			}
		}
		throw new Exception("Não foi possivel adcionar a participação!");
	}

	public void removerParticipacao(long matriculaDoCordenador, long matriculaDoMembroQueQuerRemover,
			String nomeDoProjeto)throws Exception {
		Projeto projeto = DAOProjPart.recuperarPorIndentificador(nomeDoProjeto);
		Membro membroNovo = daoMembroConta.recuperarPorIndentificador(matriculaDoMembroQueQuerRemover);
		if (projeto != null && membroNovo != null) {
			Membro membro = projeto.getCordenador();
			if (membro.getMatricula() == matriculaDoCordenador) {
				Participacao participacao=projeto.getMembro(membroNovo.getMatricula());
				membroNovo.remover(participacao);
				projeto.remover(participacao);
				DAOProjPart.atualizar(projeto, projeto);
				daoMembroConta.atualizar(membroNovo, membroNovo);
				EnviarEmail.enviarEmail(membro.getSenha(), membro.getEmail(), membroNovo.getEmail(),
						"Você foi removido no projeto " + projeto.getNome() + " pelo coordenador " + membro.getNome(),
						"Você foi removido em um projeto");
			}
		}
		throw new Exception("Não foi possivel adcionar a participação!");
	}
}
