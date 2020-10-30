package ponto.model.projetos;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AplicacaoServidora {
	
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname","10.0.0.7");
			LocateRegistry.createRegistry(10001);
			RegistradorPontoCentral remoto = new RegistradorPontoCentral();	
			Naming.rebind("ServicoRemotoPontoTrabalhado", (Remote)remoto);
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
