package model.casosDeUsofachadas;

import model.autenticacao.ContaAutenticacaoProvedorEmailPOP3;
import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaEmail;
import model.autenticacao.ContaEmailIFPB;
import model.autenticacao.ContaEmailLivre;
import model.autenticacao.Membro;
import model.autenticacao.TipoProvedorAutenticacao;
import persistenia.xml.DAOXMLMembroConta;

//caso de uso 2
public class CasoDeUsoDoisAutenticacaoDeLogin {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	private Membro membro;

	private ContaEmail conta;
	
	public CasoDeUsoDoisAutenticacaoDeLogin() {
	}
	
	public CasoDeUsoDoisAutenticacaoDeLogin(Membro membro) {
		this.membro = membro;
	}

	public void selecionarFormaDeAutenticacao(TipoProvedorAutenticacao tipoDeAutenticacao) {
		Membro membroValorAntigo = membro;
		String email = membro.getEmail();
		int indice = email.indexOf("@");
		String dominio = email.substring(indice, email.length());
		if (dominio.equals("@academico.ifpb.edu.br")) {
			conta = new ContaEmailIFPB();
		} else {
			conta = new ContaEmailLivre();
		}
		if (tipoDeAutenticacao == TipoProvedorAutenticacao.INTERNAMENTE) {
			conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
		} else {
			conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorEmailPOP3());
		}
		membro.setConta(conta);
		System.out.println(membro.getConta().toString());
		daoMembro.atualizar(membroValorAntigo, membro);
	}
	public DAOXMLMembroConta getDaoMembro() {
		return daoMembro;
	}


	public Membro getMembro() {
		return membro;
	}
}
