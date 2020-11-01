package ponto.model.projetos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Set;

import model.autenticacao.Membro;

public interface ServicoRemotoPontoTrabalhado extends Remote {

	public abstract float horasTrabalhadasValidas(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro)
			throws RemoteException, Exception;

	public abstract float defcitHoras(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro) throws RemoteException, Exception;

	public abstract Set<PontoTrabalhado> getPontosInvalidos(Membro membro) throws RemoteException,Exception;
}
