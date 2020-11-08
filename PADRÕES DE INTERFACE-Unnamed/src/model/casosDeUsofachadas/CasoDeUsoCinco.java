package model.casosDeUsofachadas;

import java.util.Date;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 5
public class CasoDeUsoCinco {
	private DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

//	public Projeto criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
//			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais) throws Exception {
//
//			System.out.println("Projeto criado com sucesso!");
//			return projeto;
//		}
//		throw new Exception("Projeto já existente!");
//	}
//	
//	public void atualizarDado(String nomeDoProjeto,String atributoASerAtualizado, Object novoDado, Object dadoAntigo) {
//		
//			System.out.println("Projeto atualizado com sucesso!");
//			daoProjetoParticipacao.atualizar(projeto, auxiliar);
//			
//		}
//	}
	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais,
			float gastoExecutadoCusteioReais, float gastoExecutadoCapitalReais, long matricula,
			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador)
			throws Exception {
		String[] atributos = { "matricula" };
		Object[] valor = { matricula };
		Membro cordenadorDoProjeto = daoMembro.consultarAnd(atributos, valor).iterator().next();
		Participacao participacaoProjeto = new Participacao(new Date(System.currentTimeMillis()),
				aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos, true);
		Projeto projeto = new Projeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais,
				gastoExecutadoCapitalReais);
		cordenadorDoProjeto.adicionar(participacaoProjeto);
		projeto.adicionar(participacaoProjeto);
		daoProjetoParticipacao.criar(projeto);

	}

	// olhem esse metodo
	public void atualizarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais) {

		String[] atributosProjeto = { "nome" };
		Object[] valorProjeto = { nome };
		Projeto projeto = daoProjetoParticipacao.consultarAnd(atributosProjeto, valorProjeto).iterator().next();
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
		String[] atributos = { "nome" };
		Object[] valor = { nomeDoProjeto };
		Projeto projeto = daoProjetoParticipacao.consultarAnd(atributos, valor).iterator().next();
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
