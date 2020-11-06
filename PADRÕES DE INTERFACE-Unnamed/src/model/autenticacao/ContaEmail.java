package model.autenticacao;

import java.io.Serializable;

/**
 * Essa classe responsaveis por fazer uso da ponte para variação de tratamento
 * de contas
 * 
 * @author Antônio Amorim
 *
 */
public abstract class ContaEmail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContaBridge conta;

	/**
	 * Todas subclasses tem sua propria operações de tratamento de conta
	 * 
	 * @param login
	 * @param senha
	 * @return
	 */
	public abstract Membro autenticar(String login, String senha);

	public abstract boolean validarLogin(String email);

	public void setImplementacaoContaBridge(ContaBridge conta) {
		this.conta = conta;
	}

	public boolean equals(ContaEmail compara) {
		if (conta.getLogin().equals(compara.getLogin())) {
			return true;
		}
		return false;
	}

	public String getLogin(){
		return conta.getLogin();
	}
	/*
	 * TODO 1. Desnecessario no bridge ter um get para implementacao setada nele, no
	 * geral.
	 */
}
