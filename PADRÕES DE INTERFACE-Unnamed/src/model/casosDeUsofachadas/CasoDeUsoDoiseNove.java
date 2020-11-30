package model.casosDeUsofachadas;

import java.util.logging.Level;

import model.autenticacao.ContaAutenticacaoProvedorEmailPOP3;
import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaBridge;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.autenticacao.TipoProvedorAutenticacao;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 2 e 9
public class CasoDeUsoDoiseNove {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	private RegistradorSessaoLogin registrador = RegistradorSessaoLogin.getInstance();

	public void fazerLogin(String login, String senha, String tipoProvedor) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Fazer login");
		Membro membroAtual = daoMembro.recuperarPorEmail(login);

		Membro antigo = membroAtual;
		ContaBridge contaBridge = null;
		LoggerProjeto.getInstance().getLogger().info("Selecionando tipo de autenticacao");
		if (tipoProvedor.equalsIgnoreCase("INTERNAMENTE")) {
			contaBridge = new ContaAutenticacaoProvedorInterno();
		}
		else{
			contaBridge = new ContaAutenticacaoProvedorEmailPOP3();
		}
		membroAtual.getConta().setImplementacaoContaBridge(contaBridge);
		LoggerProjeto.getInstance().getLogger().info("Autenticando a conta de acoro com o tipo");
		if (contaBridge.autenticar(login, senha) != null) {
			daoMembro.atualizar(antigo, membroAtual);
			registrador.registrarOline(membroAtual);
			LoggerProjeto.getInstance().getLogger().warning("Conta autenticada com sucesso");
			return;
		} else {
			LoggerProjeto.getInstance().getLogger().severe("Nao foi possivel realizar o login");
			throw new Exception("Login não realizado");
		}

	}

	public void fazerLogout(String login) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Realizando logout");
		Membro membroAtual = daoMembro.recuperarPorEmail(login);
		registrador.registrarOffline(membroAtual.getEmail());
		LoggerProjeto.getInstance().getLogger().warning("Logout feito");
	}
	public boolean isAdmin() {
        return registrador.isAdmin();
    }
}
