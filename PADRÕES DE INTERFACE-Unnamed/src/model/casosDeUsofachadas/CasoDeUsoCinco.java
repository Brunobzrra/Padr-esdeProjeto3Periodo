package model.casosDeUsofachadas;

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

	public void remover(String nomeDoProjeto) throws Exception {
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
