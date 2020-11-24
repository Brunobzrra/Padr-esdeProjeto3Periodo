package model.casosDeUsofachadas;

import java.util.Date;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 5
public class CasoDeUsoCinco {

	private DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos)
			throws Exception {
		Membro cordenadorDoProjeto = daoMembro.recuperarPorIndentificador(matricula);
		Participacao participacaoProjeto = new Participacao(new Date(System.currentTimeMillis()),
				aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos, true);
		Projeto projeto = new Projeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais);
		cordenadorDoProjeto.adicionar(participacaoProjeto);
		projeto.adicionar(participacaoProjeto);
		daoProjetoParticipacao.criar(projeto);

	}

	// olhem esse metodo
	public void atualizarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais) throws Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nome);
		Projeto projetoAntigo = projeto;
		for (ProjetoComponente participacaoDoFor : projeto.getItens()) {
			Participacao participacao = (Participacao) participacaoDoFor;
			if (participacao.isCoordenador()) {
				projeto.setAporteCapitalReais(aporteCapitalReais);
				projeto.setAporteCusteioReais(aporteCusteioReais);
				projeto.setNome(nome);
				daoProjetoParticipacao.atualizar(projetoAntigo, projeto);
			}
			return;
		}

	}

	public void removerProjeto(String nomeDoProjeto) throws Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		for (ProjetoComponente participacaoDaVez : projeto.getItens()) {
			Participacao participacao = (Participacao) participacaoDaVez;
			Membro membro = participacao.getMembro();
			projeto.remover(participacao);
			membro.remover(participacao);
			daoMembro.atualizar(membro, membro);
			daoProjetoParticipacao.atualizar(projeto, projeto);
			System.out.println("Removido com sucesso!");
			return;
		}
		throw new Exception("O membro que não for cordenador não pode remover!");

	}

}
