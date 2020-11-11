package view.controller;

import java.rmi.RemoteException;

import model.casosDeUsofachadas.CasoDeUsoOnzeETreze;

public class ControllerRegistradorEView {

	private CasoDeUsoOnzeETreze fachada;
	
	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {
		fachada.registrarPonto(nomeDoProjeto, login, senha);
	}
	public StringBuffer defcitHoras(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return fachada.defcitHoras(login, nomeDoProjeto);
	}
	public StringBuffer getPontosValidos(String login, String nomeDoProjeto) {
		return fachada.getPontosValidos(login, nomeDoProjeto);
	}
	public StringBuffer horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return fachada.horasTrabalhadasValidas(login, nomeDoProjeto);
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
}
