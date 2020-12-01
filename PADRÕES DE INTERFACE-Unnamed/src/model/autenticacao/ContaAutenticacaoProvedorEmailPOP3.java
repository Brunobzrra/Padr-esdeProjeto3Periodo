package model.autenticacao;

import java.util.Properties;
import java.util.Set;

import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import persistencia.xml.DAOXMLMembroConta;

/**
 * Classe que tem como funcao autenticar o email cadastrado de um membro de
 * acordo com as configuracoes fornecidas.
 * 
 * @author bruno
 */

public class ContaAutenticacaoProvedorEmailPOP3 extends ContaBridge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String provedorHost = "pop.gmail.com";

	private String provedorPorta = "995";

	/**
	 * Autentica um membro via POP3, validando seu email caso existente, e
	 * consultando ele o mesmo via BD posteriormente, caso ele passe pelos dois
	 * testes, é retornado
	 * 
	 * @param email, senha
	 */
	public Membro autenticar(String email, String senha) {
		if (check(email, senha, provedorHost, provedorPorta)) {
			DAOXMLMembroConta dao = new DAOXMLMembroConta();
			String[] atributos = { "email", "senha" };
			String[] valores = { email, senha };

			Set<Membro> membros = dao.consultarAnd(atributos, valores);
			for (Membro membro : membros) {
				return membro;
			}
		}
		return null;
	}

	/**
	 * Método que ira autenticar um membro via protocolo de visualizacao de email
	 * POP3, caso autenticado retorna true, e isso significa que o email é realmente
	 * válido, e é possivel usa=lo no sistema.
	 * 
	 * @params login, senha, provedor, porta
	 */
	private static boolean check(String login, String senha, String provedor, String porta) {

		Properties properties = new Properties();

		properties.put("mail.pop3s.host", provedor);
		properties.put("mail.pop3s.port", porta);
		properties.put("mail.pop3s.starttls.enable", "true");

		Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, senha);
			}
		});
		emailSession.setDebug(true);

		Store store;
		try {
			store = emailSession.getStore("pop3s");
			store.connect();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setProvedorHost(String provedorHost) {
		this.provedorHost = provedorHost;
	}

	public void setProvedorPorta(String provedorPorta) {
		this.provedorPorta = provedorPorta;
	}

}
