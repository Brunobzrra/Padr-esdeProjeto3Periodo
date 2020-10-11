package model.fachadas;

import model.autenticacao.Membro;
import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;

public class ProjetoFachada {
	private Projeto projeto;

	public ProjetoFachada(Projeto projeto) {
		this.projeto = projeto;
	}

	public void adcionarGrupo(Grupo grupo) throws Exception {
		projeto.adicionar(grupo);
	}

	public void adcionarEdital(Edital edital) throws Exception {
		projeto.adicionar(edital);
	}

	public void adcionarMembro(Membro membro) throws Exception {
		projeto.adicionar(membro);
	}

	public void removerMembro(Membro membro) throws Exception {
		projeto.remover(membro);
	}
}
