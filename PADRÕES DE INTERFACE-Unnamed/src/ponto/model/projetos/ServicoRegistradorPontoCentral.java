package ponto.model.projetos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Interface de objeto remota para a aplicacao do proxy. contendo todos os
 * metodos que serao ultilizados do objeto remoto
 * 
 * @author bruno
 */
public interface ServicoRegistradorPontoCentral extends Remote {

	public abstract StringBuffer horasTrabalhadasValidas(String login, String nomeDoProjeto)
			throws RemoteException, Exception;

	public abstract ArrayList<String> recuperarProjetos(String email) throws RemoteException, Exception;

	public abstract StringBuffer defcitHoras(String login, String nomeDoProjeto) throws RemoteException, Exception;

	public abstract void registrarPonto(String nomeDoProjeto, String login, String senha)
			throws RemoteException, Exception;

	public abstract StringBuffer getPontosValidos(String login, String nomeDoProjeto) throws RemoteException, Exception;

	public abstract Set<PontoTrabalhado> getPontosInvalidos(String login, String nomeDoProjeto)
			throws RemoteException, Exception;

	public abstract String getDetalhes(String login, String nomeDoProjeto) throws RemoteException, Exception;

}
