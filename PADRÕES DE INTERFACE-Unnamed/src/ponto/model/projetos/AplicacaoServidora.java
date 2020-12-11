package ponto.model.projetos;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.casosDeUsofachadas.CasoDeUsoOnzeETreze;

/**
 * Aplicacao servidora do objeto na rede
 * @author bruno
 * */

public class AplicacaoServidora {

	public void iniciar()throws Exception {
		@SuppressWarnings("unused")
		Registry registry = null;
		/* inicia rmi */
		try {
			/* tenta iniciar o registro */
			registry = LocateRegistry.createRegistry(1099);
			ServicoRegistradorPontoCentral remoto = CasoDeUsoOnzeETreze.getInstance();
			Naming.rebind("//localhost/ServicoRemotoPontoTrabalhado", remoto);
		} catch (Exception e) {
			e.printStackTrace();
			/* se não conseguiu criar vê se está rodando */
			try {
				registry = LocateRegistry.getRegistry();
			} catch (RemoteException e2) {
				/* não conseguiu nem criar e nem há rodando, sai do programa */
				System.err.println("Registro não pode ser inicializado");
				System.exit(0);
				throw new Exception();
			}
			throw new Exception();
		}
	}
}
