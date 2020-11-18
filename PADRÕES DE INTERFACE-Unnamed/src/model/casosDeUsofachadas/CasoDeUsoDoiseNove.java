package model.casosDeUsofachadas;

import model.autenticacao.ContaAutenticacaoProvedorEmailPOP3;
import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaBridge;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.autenticacao.TipoProvedorAutenticacao;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 2 e 9
public class CasoDeUsoDoiseNove {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	private RegistradorSessaoLogin registrador = RegistradorSessaoLogin.getInstance();

	public boolean fazerLogin(String login, String senha, String tipoProvedor) throws Exception {
		Membro membroAtual = daoMembro.recuperarPorEmail(login);

		Membro antigo = membroAtual;
		ContaBridge contaBridge = null;
		if (tipoProvedor.equalsIgnoreCase(TipoProvedorAutenticacao.POP3.toString())) {
			contaBridge = new ContaAutenticacaoProvedorEmailPOP3();
		} else if(tipoProvedor.equalsIgnoreCase(TipoProvedorAutenticacao.INTERNAMENTE.toString())) {
			contaBridge = new ContaAutenticacaoProvedorInterno();
		}
		membroAtual.getConta().setImplementacaoContaBridge(contaBridge);
		if (contaBridge.autenticar(login, senha) != null) {
			daoMembro.atualizar(antigo, membroAtual);
			registrador.registrarOline(membroAtual);
			return true;
		}
		return false;

	}

	public void fazerLogout(String login) throws Exception {
		Membro membroAtual = daoMembro.recuperarPorEmail(login);
		registrador.registrarOline(membroAtual);
	}
}
