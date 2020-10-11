package model.casosDeUsofachadas;

import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Grupo;
import model.projetos.Participacao;
import model.projetos.Projeto;
import persistenia.xml.DAOXMLProjetoParticipacao;

//caso de uso 5
public class ProjetoFachada {
	private Membro membro;
	private Participacao participacao;
	private DAOXMLProjetoParticipacao projetoParticipacao;
	
	public ProjetoFachada(Membro membro, Participacao participacao) throws Exception {
		this.membro = membro;
		this.participacao=participacao;
		membro.adicionar(participacao);
	}

	public void adcionarGrupo(Grupo grupo) throws Exception {
		membro.adicionar(grupo);
	}

	public void criarProjeto(String nome, float aporteCusteioReais, float aporteCapitalReais, float gastoExecutadoCusteioReais,
			float gastoExecutadoCapitalReais) throws Exception {
		String[] atributo={"nome"};
		Object[] valores= {nome};
		Set<Projeto> projetoRecuperados=projetoParticipacao.consultarAnd(atributo,valores);
		if(projetoRecuperados.size()==0) {
			Projeto projeto = new Projeto(nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais, gastoExecutadoCapitalReais);
			projeto.adicionar(membro);
			participacao.setCoordenador(true);
			membro.adicionar(participacao);
			return;
		}
		throw new Exception("Projeto já existente1");
	}

	public void adcionarMembro(Membro membro) throws Exception {
		membro.adicionar(membro);
	}

	public void removerMembro(Membro membro) throws Exception {
		membro.remover(membro);
	}
}
