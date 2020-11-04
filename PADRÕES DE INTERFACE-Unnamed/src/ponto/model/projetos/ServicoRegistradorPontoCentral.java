package ponto.model.projetos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Projeto;

public interface ServicoRegistradorPontoCentral extends Remote {

	public abstract float horasTrabalhadasValidas(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro)
			throws RemoteException, Exception;

	public abstract float defcitHoras(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro) throws RemoteException, Exception;
	public abstract PontoTrabalhado registrarPonto(Projeto projeto, String login) throws Exception;

	public abstract Set<PontoTrabalhado> getPontosInvalidos(Membro membro) throws RemoteException,Exception;
}
