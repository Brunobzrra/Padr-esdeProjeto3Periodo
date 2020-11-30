package model.casosDeUsofachadas;

import java.util.Date;
import java.util.logging.Level;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 5
public class CasoDeUsoCinco {

	private DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "criando Projeto");
		LoggerProjeto.getInstance().getLogger().info("Verificando o membro que ira coordenar");
		Membro cordenadorDoProjeto = daoMembro.recuperarPorIndentificador(matricula);
		Participacao participacaoProjeto = new Participacao(new Date(System.currentTimeMillis()),
				aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos, true);
		Projeto projeto = new Projeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais);
		LoggerProjeto.getInstance().getLogger().info("Preenchendo dados e participacaoo do membro que ira coordenar");
		cordenadorDoProjeto.adicionar(participacaoProjeto);
		projeto.adicionar(participacaoProjeto);
		if(!daoProjetoParticipacao.criar(projeto)) {
			throw new Exception("Projeto já existe!");
		}
		LoggerProjeto.getInstance().getLogger().warning("Projeto criado");

	}

	// olhem esse metodo
	public void atualizarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Atualizando projeto");
		LoggerProjeto.getInstance().getLogger().info("Tentando recuperar projeto do BD");
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nome);
		Projeto projetoAntigo = projeto;
		for (ProjetoComponente participacaoDoFor : projeto.getItens()) {
			Participacao participacao = (Participacao) participacaoDoFor;
			if (participacao.isCoordenador()) {
				projeto.setAporteCapitalReais(aporteCapitalReais);
				projeto.setAporteCusteioReais(aporteCusteioReais);
				projeto.setNome(nome);
				LoggerProjeto.getInstance().getLogger().warning("Projeto atualizado");
				daoProjetoParticipacao.atualizar(projetoAntigo, projeto);
			}
			return;
		}
		LoggerProjeto.getInstance().getLogger().severe("Nao foi possivel atualizar o projeto");

	}

	public void removerProjeto(String nomeDoProjeto) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Removendo projeto");
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		LoggerProjeto.getInstance().getLogger().info("Projeto recuperado dos dados internos");
		for (ProjetoComponente participacaoDaVez : projeto.getItens()) {
			Participacao participacao = (Participacao) participacaoDaVez;
			Membro membro = participacao.getMembro();
			projeto.remover(participacao);
			membro.remover(participacao);
			daoProjetoParticipacao.remover(projeto);
			daoMembro.atualizar(membro, membro);
			daoProjetoParticipacao.atualizar(projeto, projeto);
			LoggerProjeto.getInstance().getLogger().warning("Removido com sucesso");
			return;
		}
		LoggerProjeto.getInstance().getLogger().severe("Coordenador nao pode ser removido");
		throw new Exception("O membro que não for cordenador não pode remover!");

	}

}
