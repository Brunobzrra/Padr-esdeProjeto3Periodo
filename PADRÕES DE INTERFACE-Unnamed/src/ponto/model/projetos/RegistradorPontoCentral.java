package ponto.model.projetos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import model.projetos.Projeto;

public class RegistradorPontoCentral extends UnicastRemoteObject implements ServicoRemotoPontoTrabalhado {

	protected RegistradorPontoCentral() throws RemoteException {
		super();
	}
	private static final long serialVersionUID = 1L;
	public boolean registrarPonto(Projeto projeto, String login) {
		return true;
	}

	public Set<Projeto> getProjetosAtivos(String login) {
		return null;
	}

	public float horasTrabalhadasValidas(long datInicio, long dataTermino, String login) {
		return 0;
	}

	public float defcitHoras(long datInicio, long dataTermino, String login) {
		return 0;
	}

	public Set<PontoTrabalhado> getPontosInvalidos(String login) {
		return null;
	}
	public void justificarPontoInvalido(PontoTrabalhado ponto,String justificar,String login) {

	}
	public void justificarPontoNaoBatido(PontoTrabalhado ponto,String justificar,String login) {
		// TODO Auto-generated method stub

	}
}
