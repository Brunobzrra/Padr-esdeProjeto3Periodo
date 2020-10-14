package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import model.projetos.Grupo;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistenia.xml.DAOXMLGrupo;

//caso de uso 3
public class GrupoFachada extends ProjetoFachada {
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();

	public GrupoFachada(Membro membro, Participacao participacao) throws Exception {
		super(membro, participacao);
		// TODO Auto-generated constructor stub
	}

	public void adcionarGrupo(Grupo grupo) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoGrupo.criar(grupo))
				throw new Exception("Este grupo ja existe!");
		}
	}

	public void atualizarGrupo(Grupo grupoSubstituivel, Grupo grupoSubistituto) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoGrupo.atualizar(grupoSubstituivel, grupoSubistituto)) {
				throw new Exception("Este grupo não existe!");
			}
		}
	}

	public void removerGrupo(Grupo grupo) throws Exception {
		// TODO Auto-generated method stub
		// decidimos que seria melhor a fachada fazer o uso do instanceof
		if (super.getMembro().isAdministrador()) {
			for (ProjetoComponente projetoComponente : grupo.getItens()) {
				if (projetoComponente instanceof Projeto) {
					throw new Exception("Este grupo contem projetos atribuidos a ele!");
				}
			}
			daoGrupo.remover(grupo);
		}
	}
}
