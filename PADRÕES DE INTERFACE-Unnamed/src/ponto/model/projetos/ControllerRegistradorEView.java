package ponto.model.projetos;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;

import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaEmail;
import model.autenticacao.ContaEmailLivre;
import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import persistenia.xml.DAOXMLMembroConta;
import persistenia.xml.DAOXMLProjetoParticipacao;

public class ControllerRegistradorEView {

	private static ControllerRegistradorEView controllerUnico;

	private static RegistradorPontoCentral registrador;

	private static DAOXMLProjetoParticipacao dao = new DAOXMLProjetoParticipacao();

	private ControllerRegistradorEView() throws RemoteException {
		registrador = new RegistradorPontoCentral();
	}

	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {

		Object[] valores = { nomeDoProjeto };
		String[] atributos = { "nome" };
		Set<Projeto> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		registrador.registrarPonto((Projeto) recuperados[0], login);
	}

	public void horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {
		Object[] valores = { nomeDoProjeto };
		String[] atributos = { "nome" };
		Set<Projeto> recuperado = dao.consultarAnd(atributos, valores);
		Projeto[] recuperados = (Projeto[]) recuperado.toArray();
		dao.consultarAnd(atributos, valores);
		for (ProjetoComponente membroDoFor : recuperados[0].getItens()) {
			if (membroDoFor instanceof Membro) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							registrador.horasTrabalhadasValidas(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor);
						}
					}
				}

			}
		}

	}

	public void getPontosInvalidos(String login, String nomeDoProjeto) throws RemoteException {
		Object[] valores = { nomeDoProjeto };
		String[] atributos = { "nome" };
		Set<Projeto> recuperado = dao.consultarAnd(atributos, valores);
		Projeto[] recuperados = (Projeto[]) recuperado.toArray();
		dao.consultarAnd(atributos, valores);
		for (ProjetoComponente membroDoFor : recuperados[0].getItens()) {
			if (membroDoFor instanceof Membro) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					registrador.getPontosInvalidos((Membro) membroDoFor);

				}

			}
		}
	}

	public void defcitHoras(String login, String nomeDoProjeto) throws RemoteException {
		Object[] valores = { nomeDoProjeto };
		String[] atributos = { "nome" };
		Set<Projeto> recuperado = dao.consultarAnd(atributos, valores);
		Projeto[] recuperados = (Projeto[]) recuperado.toArray();
		dao.consultarAnd(atributos, valores);
		for (ProjetoComponente membroDoFor : recuperados[0].getItens()) {
			if (membroDoFor instanceof Membro) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							registrador.defcitHoras(pontoDoFor.getDataHoraEntrada(), pontoDoFor.getDataHoraSaida(),
									(Membro) membroDoFor);
						}
					}
				}

			}
		}
	}

	public static ControllerRegistradorEView getInstance() {

		if (controllerUnico == null) {
			try {
				return new ControllerRegistradorEView();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return controllerUnico;
	}

	public static void main(String[] args) {
		Membro joseClaudiu = new Membro(Long.parseLong("111"), "jose claudiu", "fananitadz@gmail.com", "1212",
				"unnamed!");
		ContaEmail conta = new ContaEmailLivre();
		conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
		joseClaudiu.setConta(conta);
		joseClaudiu.setAtivo(true);
		joseClaudiu.setAdministrador(true);
		Participacao participacao = new Participacao(new Date(System.currentTimeMillis()), new Date(03 / 12 / 2020),
				Float.parseFloat("0"), (short) 0, (short) 0, true);
		try {
			participacao.adicionar(joseClaudiu);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Projeto projeto1 = new Projeto("MeuCu", 0, 0, 0, 0);
		DAOXMLProjetoParticipacao daoao = new DAOXMLProjetoParticipacao();
		try {
			DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
			daoMembro.criar(joseClaudiu);
			projeto1.adicionar(participacao);
			daoao.criar(projeto1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
