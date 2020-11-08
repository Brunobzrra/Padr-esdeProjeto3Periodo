package model.casosDeUsofachadas;

import java.util.Set;

import model.autenticacao.ContaAutenticacaoProvedorEmailPOP3;
import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaBridge;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.autenticacao.TipoProvedorAutenticacao;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 2
public class CasoDeUsoDois {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	private RegistradorSessaoLogin registrador = RegistradorSessaoLogin.getInstance();

	public boolean fazerLogin(String login, String senha, String tipoProvedor) {
		Object[] email = { login };
		String[] nomeAtributo = { "email" };
		Set<Membro> membroRecuperado = daoMembro.consultarAnd(nomeAtributo, email);
		Membro[] membro = (Membro[]) membroRecuperado.toArray();
		Membro antigo = membro[0];
		ContaBridge contaBridge = null;
		if (tipoProvedor.equalsIgnoreCase(TipoProvedorAutenticacao.POP3.toString())) {
			contaBridge = new ContaAutenticacaoProvedorEmailPOP3();
		} else {
			contaBridge = new ContaAutenticacaoProvedorInterno();
		}
		membro[0].getConta().setImplementacaoContaBridge(contaBridge);
		if (contaBridge.autenticar(login, senha) != null) {
			daoMembro.atualizar(antigo, membro[0]);
			registrador.registrarOline(membro[0]);
			return true;
		}
		return false;

	}

}

