package model.casosDeUsofachadas.controllerProxy;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaEmail;
import model.autenticacao.ContaEmailLivre;
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
import ponto.model.projetos.ServicoRegistradorPontoCentral;

public class ControllerRegistradorEViewCasoDeUsoOnze {

	private static ControllerRegistradorEViewCasoDeUsoOnze controllerUnico;
	private static DAOXMLMembroConta daMembro = new DAOXMLMembroConta();
	private static DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();

	private ServicoRegistradorPontoCentral proxy;

	private ControllerRegistradorEViewCasoDeUsoOnze()
			throws RemoteException, MalformedURLException, UnknownHostException, NotBoundException {
		proxy = (ServicoRegistradorPontoCentral) Naming
				.lookup("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/ServicoRemotoPontoTrabalhado");

	}

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
				proxy.registrarPonto((Projeto) projeto, m);
				daMembro.atualizar(aux, m);
				daoProjetoParticipacao.atualizar(projetoAux, projeto);
			} else {
				throw new Exception("Senha incorreta!");
			}
		} else
			throw new Exception("Projeto não existente!");
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

	public float horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {

		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							return proxy.horasTrabalhadasValidas(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor);
						}
					}
				}

			}
		}
		return 0;

	}

	public Set<PontoTrabalhado> getPontosInvalidos(String login, String nomeDoProjeto) throws Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);

		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					return proxy.getPontosInvalidos((Membro) membroDoFor);

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

	public float defcitHoras(String login, String nomeDoProjeto) throws Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);

		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							return proxy.defcitHoras(pontoDoFor.getDataHoraEntrada(), pontoDoFor.getDataHoraSaida(),
									(Membro) membroDoFor);
						}
					}
				}

			}
		}
		return 0;
	}

	public static ControllerRegistradorEViewCasoDeUsoOnze getInstance() throws Exception {

		if (controllerUnico == null) {
			return new ControllerRegistradorEViewCasoDeUsoOnze();
		}
		return controllerUnico;
	}

	public static void main(String[] args) {
		Membro joseClaudiu = new Membro(Long.parseLong("111"), "jose claudiu", "fananitadz@gmail.com", "1212");
		ContaEmail conta = new ContaEmailLivre();
		conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
		joseClaudiu.setConta(conta);
		joseClaudiu.setAtivo(true);
		joseClaudiu.setAdministrador(true);
		Participacao participacao = new Participacao(new Date(System.currentTimeMillis()), Float.parseFloat("0"),
				(short) 0, (short) 0, true);
		Projeto projeto1 = new Projeto("Projeto novo", 0, 0, 0, 0);
		Projeto projeto2 = new Projeto("projeto 2", 0, 0, 0, 0);

		DAOXMLProjetoParticipacao daoao = new DAOXMLProjetoParticipacao();
		try {
			DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
			Participacao participacao2 = new Participacao(new Date(System.currentTimeMillis()), Float.parseFloat("0"),
					(short) 0, (short) 0, true);
			joseClaudiu.adicionar(participacao);
			projeto1.adicionar(participacao);
			joseClaudiu.adicionar(participacao2);
			projeto2.adicionar(participacao2);
			daoMembro.criar(joseClaudiu);
			daoao.criar(projeto1);
			daoao.criar(projeto2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
