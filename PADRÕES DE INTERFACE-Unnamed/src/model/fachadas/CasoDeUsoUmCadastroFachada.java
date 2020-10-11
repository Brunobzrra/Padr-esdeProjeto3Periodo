package model.fachadas;

import java.util.Set;

import model.autenticacao.ContaAutenticacaoProvedorEmailPOP3;
import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaBridge;
import model.autenticacao.ContaEmail;
import model.autenticacao.ContaEmailIFPB;
import model.autenticacao.ContaEmailLivre;
import model.autenticacao.Membro;
import model.autenticacao.TipoProvedorAutenticacao;
import persistenia.xml.DAOXMLMembroConta;

public class CasoDeUsoUmCadastroFachada {

	DAOXMLMembroConta dao = new DAOXMLMembroConta();

	ContaEmail conta;

	public boolean cadastrarMembro(String nome, long matricula, String email, String senha, String senhaEmail) {
		Membro m = new Membro(matricula, nome, email, senha, senhaEmail);

		

		String[] atributos = { "matricula", "email" };
		Object[] valores = { matricula, email };
		Set<Membro> membroRecuperado = dao.consultarAnd(atributos, valores);
		if (membroRecuperado == null) {
			m.setAtivo(true);
			if (dao.isVazia()) {
				m.setAdministrador(true);
			} else {
				m.setAdministrador(false);
			}
			dao.criar(m);
			return true;
		}
		return false;
	}

	public void atualizarMembro(Membro m, String[] atributosQueroAtualizar, Object[] valores) throws Exception {
		boolean atualizado = false;
		Membro membroAtual = m;
		for (int i = 0; i < atributosQueroAtualizar.length; i++) {
			if (atributosQueroAtualizar[i].equals("nome")) {
				membroAtual.setNome((String) valores[i]);
				atualizado = true;
			}
			if (atributosQueroAtualizar[i].equals("ativo")) {
				membroAtual.setAtivo((boolean) valores[i]);
				atualizado = true;
			}
			if (atributosQueroAtualizar[i].equals("email")) {
				membroAtual.setEmail((String) valores[i]);
				atualizado = true;
			}
			if (atributosQueroAtualizar[i].equals("senha")) {
				membroAtual.setSenha((String) valores[i]);
				atualizado = true;
			}

		}
		if (atualizado) {
			dao.atualizar(m, membroAtual);
		} else {
			throw new Exception("O membro nao pode ser atualizado.");

		}

	}

}
