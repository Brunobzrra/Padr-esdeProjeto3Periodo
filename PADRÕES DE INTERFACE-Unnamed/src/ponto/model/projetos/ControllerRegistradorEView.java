package ponto.model.projetos;

import java.rmi.RemoteException;

import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.PegadorDeEmailDoDaoMembro;

import persistenia.xml.DAOXMLProjetoParticipacao;

import view.TelaPonto;

public class ControllerRegistradorEView {

	private static ControllerRegistradorEView controllerUnico;

	private TelaPonto tela;

	private static RegistradorPontoCentral registrador;

	private static DAOXMLProjetoParticipacao dao;

	private ControllerRegistradorEView() {

	}

	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {

		Object[] valores = { nomeDoProjeto };
		String[] atributos = { "nome" };
		Set<Projeto> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		dao.consultarAnd(atributos, valores);
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
			return new ControllerRegistradorEView();
		}
		return controllerUnico;
	}
}
