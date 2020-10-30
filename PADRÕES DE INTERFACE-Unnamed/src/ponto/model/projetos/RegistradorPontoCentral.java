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

	public void justificarPontoInvalido(PontoTrabalhado ponto,String justificar,String login) {

	}
	
	public void justificarPontoNaoBatido(PontoTrabalhado ponto,String justificar,String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public float horasTrabalhadasValidas(long datInicio, long dataTermino, String login) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float defcitHoras(long datInicio, long dataTermino, String login) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<PontoTrabalhado> getPontosInvalidos(String login) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
