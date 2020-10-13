package model.casosDeUsofachadas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistenia.xml.DAOXMLMembroConta;
import persistenia.xml.DAOXMLProjetoParticipacao;

public class GerenciadorMembroFachada extends ProjetoFachada {

	private DAOXMLProjetoParticipacao DAOProjPart = getDaoProjetoParticipacao();
	private DAOXMLMembroConta daoMembroConta = new DAOXMLMembroConta();

	public GerenciadorMembroFachada(Membro membro, Participacao participacao, Projeto projetoASerGerenciado)
			throws Exception {
		super(membro, participacao);
		super.setProjeto(projetoASerGerenciado);
	}

	public void removerParticipacao(Membro removido) throws Exception {
		Projeto auxiliar = super.getProjeto();
		ArrayList<ProjetoComponente> membroParticipacao = removido.getParticipacoes();
		Boolean foiMudado = null;
		for (ProjetoComponente participacaoAuxiliar : this.getMembro().getParticipacoes()) {
			for (ProjetoComponente participacaoRemover : membroParticipacao) {
				if (participacaoAuxiliar.equals(participacaoRemover)) {
					if (((Participacao) participacaoAuxiliar).isCoordenador()) {
						auxiliar.remover(removido);
						foiMudado = true;
					}
				}
			}
		}
		if (foiMudado != null) {
			DAOProjPart.atualizar(this.getProjeto(), auxiliar);
		}
	}

	public void adicionar(Membro adicionado, Date dataInicio, Date dataTermino, float aporteCusteioMensalReais,
			short qtdMesesCusteados) throws Exception {
		Projeto auxiliar = super.getProjeto();
		ArrayList<ProjetoComponente> membroParticipacao = adicionado.getParticipacoes();
		Boolean foiMudado = null;
		String[] atributos = { "nome", "conta" };
		Object[] valores = { adicionado.getNome(), adicionado.getConta() };
		Set<Membro> recuperados = daoMembroConta.consultarAnd(atributos, valores);

		if (recuperados != null) {
			for (Membro membro : recuperados) {
				if (membro.getConta() != null) {
					for (ProjetoComponente participacaoAuxiliar : this.getMembro().getParticipacoes()) {
						for (ProjetoComponente participacaoRemover : membroParticipacao) {
							if (participacaoAuxiliar.equals(participacaoRemover)) {
								if (((Participacao) participacaoAuxiliar).isCoordenador()) {
									short mesesCusteados = Short.parseShort("0");
									Participacao novaParticipacao = new Participacao(dataInicio, dataTermino,
											aporteCusteioMensalReais, qtdMesesCusteados, mesesCusteados, false);
									adicionado.adicionar(novaParticipacao);
									auxiliar.adicionar(adicionado);
									foiMudado = true;
								}
							}
						}
					}
					if (foiMudado != null) {
						DAOProjPart.atualizar(this.getProjeto(), auxiliar);
					}

				}
			}
		}
	}

}
