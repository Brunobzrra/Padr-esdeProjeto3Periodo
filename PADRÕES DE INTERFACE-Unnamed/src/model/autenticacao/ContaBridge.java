package model.autenticacao;

import java.io.Serializable;

/**
 * Esta é a classe e responsavel por fazer a ponte com Conta email
 * @author Antônio Amorim
 *
 */
public abstract class ContaBridge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoProvedorAutenticacao tipo;

	private String login;

	private String senha;

	/**
	 * Esse metodo todas as subclasses devem implemntar sua propria operação
	 * @param login
	 * @param senha
	 * @return
	 */
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
