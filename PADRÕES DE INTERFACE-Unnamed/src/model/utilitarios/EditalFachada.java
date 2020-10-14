package model.utilitarios;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.ProjetoFachada;
import model.projetos.Edital;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistenia.xml.DAOXMLEdital;

//caso de uso 4
public class EditalFachada extends ProjetoFachada {

	private DAOXMLEdital daoEdital = new DAOXMLEdital();

	public EditalFachada(Membro membro, Participacao participacao) throws Exception {
		super(membro, participacao);
		// TODO Auto-generated constructor stub
	}
	public void adcionarEdital(Edital edital) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoEdital.criar(edital))
				throw new Exception("Este grupo ja existe!");
		}
	}

	public void atualizarEdital(Edital editalSubstituivel, Edital editalSubistituto) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoEdital.atualizar(editalSubstituivel, editalSubistituto)) {
				throw new Exception("Este edital não existe!");
			}
		}
	}

	public void removerEdital(Edital edital) throws Exception {
		// TODO Auto-generated method stub
		// decidimos que seria melhor a fachada fazer o uso do instanceof
		if (super.getMembro().isAdministrador()) {
			for (ProjetoComponente projetoComponente : edital.getItens()) {
				if (projetoComponente instanceof Projeto) {
					throw new Exception("Este grupo contem projetos atribuidos a ele!");
				}
			}
			daoEdital.remover(edital);
		}
	}
}
