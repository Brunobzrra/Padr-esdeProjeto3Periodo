package model.autenticacao;
/**
 * Essa classe responsaveis por fazer uso da ponte para varia��o de tratamento de contas
 * @author Ant�nio Amorim
 *
 */
public abstract class ContaEmail {
	private ContaBridge conta;
	/**
	 * Todas subclasses tem sua propria opera��es de tratamento de conta
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
		if (conta.getLogin().equals(compara.getConta().getLogin())) {
			return true;
		}
		return false;
	}

	public ContaBridge getConta() {
		return conta;
	}
}
