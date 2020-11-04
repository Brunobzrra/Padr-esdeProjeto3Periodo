package ponto.model.projetos;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AplicacaoServidora {

	public static void main(String[] args) {
		Registry registry = null;
		/* inicia rmi */
		try {
			/* tenta iniciar o registro */
				registry = LocateRegistry.createRegistry(1099);
				System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
				RegistradorPontoCentral remoto = new RegistradorPontoCentral();
						Naming.rebind("ServicoRemotoPontoTrabalhado", remoto);
		} catch (RemoteException | MalformedURLException | UnknownHostException e) {
			/* se não conseguiu criar vê se está rodando */
			try {
				registry = LocateRegistry.getRegistry();
			} catch (RemoteException e2) {
				/* não conseguiu nem criar e nem há rodando, sai do programa */
				System.err.println("Registro não pode ser inicializado");
				System.exit(0);
			}

		}
	}
}
