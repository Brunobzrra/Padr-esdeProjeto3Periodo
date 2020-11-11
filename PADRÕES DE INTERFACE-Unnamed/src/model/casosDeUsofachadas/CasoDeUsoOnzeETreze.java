package model.casosDeUsofachadas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;

import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.RegistradorPontoCentral;
import ponto.model.projetos.ServicoRegistradorPontoCentral;

//caso de uso 11 e 13
public class CasoDeUsoOnzeETreze extends UnicastRemoteObject implements ServicoRegistradorPontoCentral {

	private static DAOXMLMembroConta daMembro = new DAOXMLMembroConta();
	private static DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();
	private static RegistradorPontoCentral registrador = new RegistradorPontoCentral();
	private static CasoDeUsoOnzeETreze fachadaSingleton;

	protected CasoDeUsoOnzeETreze() throws RemoteException {
		super();
	}

	public static CasoDeUsoOnzeETreze getInstance() throws Exception {
		return fachadaSingleton;
	}

	public ArrayList<String> recuperarProjetos(String email) {
		ArrayList<ProjetoComponente> participacao = daMembro.recuperarPorEmail(email).getParticipacoes();
		ArrayList<String> nomes = new ArrayList<String>();
		for (ProjetoComponente participa : participacao) {
			if (participa.getProjetoPai().getAtivo()) {
				nomes.add(participa.getProjetoPai().getNome());
			}
		}
		return nomes;
	}

	public Set<PontoTrabalhado> getPontosInvalidos(String login, String nomeDoProjeto) throws Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);

		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					return registrador.getPontosInvalidos((Membro) membroDoFor);

				}

			}
		}
		return null;
	}

	public String getDetalhes(String login, String nomeDoProjeto) throws Exception {
		String retorno = "Defict de Horas: " + defcitHoras(login, nomeDoProjeto) + "\n Horas trabalhadas validas: "
				+ horasTrabalhadasValidas(login, nomeDoProjeto) + "\nPontos invalidos: ";

		Set<PontoTrabalhado> pontos = getPontosInvalidos(login, nomeDoProjeto);
		PontoTrabalhado[] recuperados = (PontoTrabalhado[]) pontos.toArray();

		for (int i = 0; i < recuperados.length; i++) {
			retorno += "\n" + " Hora e dia de entrada: "
					+ ConversorDeHoraEDia.pegarHoraEDia(recuperados[i].getDataHoraEntrada()) + " Hora e dia de saida: "
					+ ConversorDeHoraEDia.pegarHoraEDia(recuperados[i].getDataHoraSaida()) + " justificativa: "
					+ recuperados[i].getJustificativa() + "\n";
		}
		return retorno;
	}

	//Metodo caso de uso 11
	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {

		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		Projeto projetoAux = projeto;

		if (projeto != null) {
			if (senha.length() == 0) {
				throw new Exception("Digite uma senha!");
			}
			Membro m = daMembro.recuperarPorEmail(login);
			RegistradorSessaoLogin.getInstance().registrarOline(m);
			Membro aux = m;
			if (m.getSenha().equalsIgnoreCase(senha)) {
				PontoTrabalhado ponto = registrador.registrarPonto((Projeto) projeto, m);
				if (ponto != null) {
					daMembro.atualizar(aux, m);
					daoProjetoParticipacao.atualizar(projetoAux, projeto);
				} else {
					throw new Exception("Não existe horario previsto para isso!");
				}
			} else {
				throw new Exception("Senha incorreta!");
			}
		} else
			throw new Exception("Projeto não existente!");
	}

	// Metodos do caso de uso 13
	public StringBuffer defcitHoras(String login, String nomeDoProjeto) throws RemoteException, Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Deficit de Horas\n");
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						texto.append("O membro " + participacaoDoFor.getMembro().getNome() + " teve um deficit de\n");
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							texto.append(registrador.defcitHoras(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor) + "\n");
						}
					}
				}

			}
		}
		return texto;
	}

	public StringBuffer getPontosValidos(String login, String nomeDoProjeto) {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Ponto inalidas\n");

		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				Membro membro = (Membro) membroDoFor;
				if ((membro).getEmail().equalsIgnoreCase(login)) {
					texto.append("Membro " + membroDoFor.getNome() + " seus pontos validos /n"
							+ membro.getPontosValidos().toString());

				}

			}
		}
		return texto;
	}

	public StringBuffer horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {

		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Horas Trabalhadas Validas\n");
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						texto.append("O membro " + participacaoDoFor.getMembro().getNome() + " trabalhou\n");
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							texto.append(registrador.horasTrabalhadasValidas(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor) + "\n");
						}
					}
				}

			}
		}
		return texto;

	}

}
