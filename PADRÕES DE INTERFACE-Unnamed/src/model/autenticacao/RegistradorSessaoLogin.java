package model.autenticacao;

/**
 * Aplicado o singleton pois deveria manter uma unica instancia unversal para
 * todo o sistema para garantir a coesão dos dados
 * 
 * @author Antônio Amorim
 *
 */
public class RegistradorSessaoLogin {
	private static RegistradorSessaoLogin registradorSingleton = new RegistradorSessaoLogin();
	private Membro logado;

	private RegistradorSessaoLogin() {

	}

	public static synchronized RegistradorSessaoLogin getInstance() {
		return registradorSingleton;
	}

	public void registrarOline(Membro membro) {
		if (!isOline(membro.getEmail())) {
			logado = membro;
		}
	}

	public void registrarOffline(String login) {
		if (logado.getEmail().equals(login)) {
			logado = null;
		}
	}

	public boolean isOline(String login) {
		if (logado.getEmail().equals(login)) {
			return true;
		}
		return false;
	}

	public Membro getLogado() {
		return logado;
	}
}
