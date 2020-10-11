package model.fachadas;

import model.autenticacao.ContaBridge;
import model.autenticacao.ContaEmail;
import model.autenticacao.Membro;

public class CadastroFachada {
	private ContaEmail conta;

	public CadastroFachada(ContaEmail conta) {
		this.conta = conta;
	}
	public void setTipoDeConta(ContaBridge tipoConta) {
		conta.setImplementacaoContaBridge(tipoConta);
	}
	public Membro cadastrar(String login, String senha) {
		return conta.autenticar(login, senha);
	}
}
