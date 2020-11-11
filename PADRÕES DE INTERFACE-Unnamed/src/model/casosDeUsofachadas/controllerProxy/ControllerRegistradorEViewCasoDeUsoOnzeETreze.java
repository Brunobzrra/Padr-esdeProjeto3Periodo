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
//Caso de uso 11 e 13
public class ControllerRegistradorEViewCasoDeUsoOnzeETreze {

	private static ControllerRegistradorEViewCasoDeUsoOnzeETreze controllerUnico;
	private static DAOXMLMembroConta daMembro = new DAOXMLMembroConta();
	private static DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();

	private ServicoRegistradorPontoCentral proxy;

	private ControllerRegistradorEViewCasoDeUsoOnzeETreze()
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
				PontoTrabalhado o = proxy.registrarPonto((Projeto) projeto, m);
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
							texto.append(proxy.horasTrabalhadasValidas(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor) + "\n");
						}
					}
				}

			}
		}
		return texto;

	}

	public StringBuffer getPontosValidos(String login, String nomeDoProjeto) {
		// TODO Auto-generated method stub
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Ponto inalidas\n");

		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				Membro membro=(Membro) membroDoFor;
				if ((membro).getEmail().equalsIgnoreCase(login)) {
					texto.append("Membro " + membroDoFor.getNome() + " seus pontos validos /n"+membro.getPontosValidos().toString());
			
				}

			}
		}
		return texto;
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

	public StringBuffer defcitHoras(String login, String nomeDoProjeto) throws Exception {
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Deficit de Horas\n");
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						texto.append("O membro " + participacaoDoFor.getMembro().getNome() + " teve um deficit de\n");
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							texto.append(proxy.defcitHoras(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor) + "\n");
						}
					}
				}

			}
		}
		return texto;
	}

	public static ControllerRegistradorEViewCasoDeUsoOnzeETreze getInstance() throws Exception {

		if (controllerUnico == null) {
			return new ControllerRegistradorEViewCasoDeUsoOnzeETreze();
		}
		return controllerUnico;
	}

	public static void main(String[] args) {
		Membro joseClaudiu = new Membro(Long.parseLong("111"), "jose claudiu", "fananitadz@gmail.com", "121212");
		ContaEmail conta = new ContaEmailLivre();
		conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
		joseClaudiu.setConta(conta);
		joseClaudiu.setAtivo(true);
		joseClaudiu.setAdministrador(true);
		Participacao participacao = new Participacao(new Date(System.currentTimeMillis()), Float.parseFloat("0"),
				(short) 0, (short) 0, true);
		Projeto projeto1 = new Projeto("Projeto novo", 1, 2, 2, 3);
		Projeto projeto2 = new Projeto("projeto 2", 1, 2, 3, 5);

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
