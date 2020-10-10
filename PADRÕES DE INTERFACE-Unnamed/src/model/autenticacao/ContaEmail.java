package model.autenticacao;

public abstract class ContaEmail {
	private ContaBridge conta;

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
