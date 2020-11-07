package model.casosDeUsofachadas;

import java.util.Set;

import model.autenticacao.ContaAutenticacaoProvedorEmailPOP3;
import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaEmail;
import model.autenticacao.ContaEmailIFPB;
import model.autenticacao.ContaEmailLivre;
import model.autenticacao.Membro;
import model.autenticacao.TipoProvedorAutenticacao;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 2
public class CasoDeUsoDoisAutenticacaoDeLogin {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	private Membro membro;

	private ContaEmail conta;

	public CasoDeUsoDoisAutenticacaoDeLogin(long matricula) throws Exception {
		Object[] mat = { matricula };
		String[] atr = { "matricula" };
		Set<Membro> membros = daoMembro.consultarAnd(atr, mat);
		if (membros != null) {
			Membro[] retorno = (Membro[]) membros.toArray();
			membro = retorno[0];
		} else {
			throw new Exception("Membro não existente!");
		}
	}

	public void selecionarFormaDeAutenticacao(String tipoDeAutenticacao) {
		Membro membroValorAntigo = membro;
		String email = membro.getEmail();
		int indice = email.indexOf("@");
		String dominio = email.substring(indice, email.length());
		if (dominio.equals("@academico.ifpb.edu.br")) {
			conta = new ContaEmailIFPB();
		} else {
			conta = new ContaEmailLivre();
		}
		if (tipoDeAutenticacao.toLowerCase().equals(TipoProvedorAutenticacao.POP3.toString().toLowerCase())) {
			conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorEmailPOP3());

		} else {
			conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
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
