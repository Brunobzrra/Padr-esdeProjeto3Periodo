package model.autenticacao;

import java.util.ArrayList;
/**
 * Aplicado o singleton pois deveria manter uma unica instancia unversal para todo o sistema 
 * para garantir a coesão dos dados
 * @author Antônio Amorim
 *
 */
public class RegistradorSessaoLogin {
	private static RegistradorSessaoLogin registradorSingleton = new RegistradorSessaoLogin();
	private ArrayList<Membro> logados= new ArrayList<Membro>();
	
	private RegistradorSessaoLogin() {

	}

	public static synchronized RegistradorSessaoLogin getInstance() {
		return registradorSingleton;
	}

	public void registrarOline(Membro membro) {
		if(!isOline(membro.getEmail())) {
			logados.add(membro);
		}
	}

	public void registrarOffline(String login) {
		for (int i = 0; i < logados.size(); i++) {
			if (logados.get(i).getEmail().equals(login)) {
				logados.remove(i);
			}
		}
	}

	public boolean isOline(String login) {
		for (Membro membro : logados) {
			if(membro.getEmail().equals(login)) {
				return true;
			}
		}
		return false;
	}
}
