package view.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.ServicoRegistradorPontoCentral;

public class ControllerRegistradorEView {

	private ServicoRegistradorPontoCentral proxy;

	public ControllerRegistradorEView() throws MalformedURLException, RemoteException, NotBoundException {
		proxy = (ServicoRegistradorPontoCentral) Naming.lookup("//localhost/ServicoRemotoPontoTrabalhado");
	}

	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {
		proxy.registrarPonto(nomeDoProjeto, login, senha);
	}

	public StringBuffer defcitHoras(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return proxy.defcitHoras(login, nomeDoProjeto);
	}

	public StringBuffer getPontosValidos(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return proxy.getPontosValidos(login, nomeDoProjeto);
	}
	public Set<PontoTrabalhado> getPontosInvalidos(String login, String nomeDoProjeto) throws Exception{
		return proxy.getPontosInvalidos(login, nomeDoProjeto);
	}
	public String getDetalhes(String login, String nomeDoProjeto) throws Exception {
		return proxy.getDetalhes(login, nomeDoProjeto);
	}

	public StringBuffer horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return proxy.horasTrabalhadasValidas(login, nomeDoProjeto);
	}

	public ArrayList<String> recuperarProjetos(String email) throws RemoteException, Exception {
		return proxy.recuperarProjetos(email);
	}
}
//	public static void main(String[] args) {
//		Membro joseClaudiu = new Membro(Long.parseLong("111"), "jose claudiu", "fananitadz@gmail.com", "121212");
//		ContaEmail conta = new ContaEmailLivre();
//		conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
//		joseClaudiu.setConta(conta);
//		joseClaudiu.setAtivo(true);
//		joseClaudiu.setAdministrador(true);
//		Participacao participacao = new Participacao(new Date(System.currentTimeMillis()), Float.parseFloat("0"),
//				(short) 0, (short) 0, true);
//		Projeto projeto1 = new Projeto("Projeto novo", 1, 2, 2, 3);
//		Projeto projeto2 = new Projeto("projeto 2", 1, 2, 3, 5);
//		HorarioPrevisto horaprt = new HorarioPrevisto(DiaSemana.QUA, (short) 16, (short) 22, (short) 30);
//		HorarioPrevisto horapr2 = new HorarioPrevisto(DiaSemana.SEX, (short) 16, (short) 22, (short) 30);
//
//		participacao.adcionarHorarioPrevisto(horaprt);
//		participacao.adcionarHorarioPrevisto(horapr2);
//
//		DAOXMLProjetoParticipacao daoao = new DAOXMLProjetoParticipacao();
//		try {
//			DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
//			Participacao participacao2 = new Participacao(new Date(System.currentTimeMillis()), Float.parseFloat("0"),
//					(short) 0, (short) 0, true);
//			joseClaudiu.adicionar(participacao);
//			projeto1.adicionar(participacao);
//			joseClaudiu.adicionar(participacao2);
//			projeto2.adicionar(participacao2);
//			daoMembro.criar(joseClaudiu);
//			daoao.criar(projeto1);
//			daoao.criar(projeto2);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//	}
//}
