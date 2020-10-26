package model.autenticacao;

public class RegistradorSessaoLogin {
	private static RegistradorSessaoLogin registradorSingleton = new RegistradorSessaoLogin();

	private RegistradorSessaoLogin() {

	}

	public static synchronized RegistradorSessaoLogin getInstance() {
		return registradorSingleton;
	}

	public void registrarOline(Membro membro) {

	}

	public void registrarOline(String login) {

	}

	public boolean isOline(String login) {
		return true;
	}
}
