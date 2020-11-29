package view.autenticacao;

public interface InterfaceTelaAutenticacao {
	
	public abstract void fazerLogin(String login, String senha, String tipoProvedor) throws Exception;

	public abstract void fazerLogout(String login) throws Exception;
}
