package model.autenticacao;

public abstract class ContaBridge {

	private TipoProvedorAutenticacao tipo;

	private String login;

	private String senha;

	public abstract Membro autenticar(String login, String senha);

	public TipoProvedorAutenticacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoProvedorAutenticacao tipo) {
		this.tipo = tipo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
