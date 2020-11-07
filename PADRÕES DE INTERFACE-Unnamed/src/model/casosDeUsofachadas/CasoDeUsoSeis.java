package model.casosDeUsofachadas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.EnviarEmail;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;

//caso de uso 6
public class CasoDeUsoSeis{

	private DAOXMLProjetoParticipacao DAOProjPart = getDaoProjetoParticipacao();
	private DAOXMLMembroConta daoMembroConta = new DAOXMLMembroConta();

	public CasoDeUsoSeis(long matricula, Date dataInicio, Date dataTermino, float aporteCusteioMensalReais,
			short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador, String nomeDoProjeto) throws Exception {
		super(AutenticadorDePersistencia.verificarMembro(matricula),
				AutenticadorDePersistencia.criarParticipacao(matricula, dataInicio, dataTermino,
						aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos, coordenador));
		super.setProjeto(AutenticadorDePersistencia.verificarProjeto(nomeDoProjeto));
	}

	public void removerParticipacao(long matricula) throws Exception {
		Membro removido = AutenticadorDePersistencia.verificarMembro(matricula);
		Projeto auxiliar = super.getProjeto();
		ArrayList<ProjetoComponente> membroParticipacao = removido.getParticipacoes();
		Boolean foiMudado = null;
		String assunto = "";
		String mensagem = "";
		for (ProjetoComponente participacaoAuxiliar : this.getMembro().getParticipacoes()) {
			for (ProjetoComponente participacaoRemover : membroParticipacao) {
				if (participacaoAuxiliar.equals(participacaoRemover)) {
					if (((Participacao) participacaoAuxiliar).isCoordenador()) {
						auxiliar.remover(removido);
						assunto = "Situacao no projeto: " + auxiliar.getNome() + " Coordenador: "
								+ getMembro().getNome();
						mensagem = "Vc foi removido!";
						foiMudado = true;
					}
				}
			}
		}
		if (foiMudado != null) {
			DAOProjPart.atualizar(this.getProjeto(), auxiliar);
			EnviarEmail.enviarEmail("unnamed!", "fananittadz@gmail.com", "bruno.bzrrasantos@gmail.com", mensagem,
					assunto);
			System.out.println("Participação removida com sucesso!");
			return;
		}
		System.out.println("Participação não pode ser removida!");

	}

	public void adicionar(long matricula, Date dataInicio, Date dataTermino, float aporteCusteioMensalReais,
			short qtdMesesCusteados) throws Exception {
		Membro adicionado = AutenticadorDePersistencia.verificarMembro(matricula);
		Projeto auxiliar = super.getProjeto();
		ArrayList<ProjetoComponente> membroParticipacao = adicionado.getParticipacoes();
		Boolean foiMudado = null;
		String[] atributos = { "nome", "matricula" };
		Object[] valores = { adicionado.getNome(), adicionado.getMatricula() };
		Set<Membro> recuperados = daoMembroConta.consultarAnd(atributos, valores);

		if (recuperados != null) {
			String assunto = "";
			String mensagem = "";
			for (Membro membro : recuperados) {
				if (membro.getConta() != null) {
					for (ProjetoComponente participacaoAuxiliar : this.getMembro().getParticipacoes()) {
						for (int i = 0; i < membroParticipacao.size(); i++) {
							if (participacaoAuxiliar.equals(membroParticipacao.get(i))) {
								if (((Participacao) participacaoAuxiliar).isCoordenador()) {
									foiMudado = true;
									break;
								}
							}
						}

					}
				}
			}
			if (foiMudado != null) {
				short mesesCusteados = Short.parseShort("0");
				Participacao novaParticipacao = new Participacao(dataInicio, dataTermino, aporteCusteioMensalReais,
						qtdMesesCusteados, mesesCusteados, false);
				adicionado.adicionar(novaParticipacao);
				auxiliar.adicionar(adicionado);
				assunto = "Situacao no projeto: " + auxiliar.getNome() + " Coordenador: " + getMembro().getNome();
				mensagem = "Vc foi adicionado";
				DAOProjPart.atualizar(this.getProjeto(), auxiliar);
				EnviarEmail.enviarEmail("0unnamed!", "fananittadz@gmail.com", "bruno.bzrrasantos@gmail.com", mensagem,
						assunto);
				System.out.println("Participação adcionada com sucesso!");
				return;
			}

		}
		System.out.println("Não foi possivel adcionar participação!");
	}
}
