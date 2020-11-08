package model.casosDeUsofachadas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.autenticacao.Membro;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 1
public class CasoDeUsoUm {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public boolean cadastrarMembro(String nome, long matricula, String email, String senha) {
		if(validarEmail(email)) {
			Membro membro = new Membro(matricula, nome, email, senha);
			membro.setAtivo(true);
			if (daoMembro.isVazia()) {
				membro.setAdministrador(true);
			} else {
				membro.setAdministrador(false);
			}
			System.out.println("membro cadastrado!");
			return daoMembro.criar(membro);			
		}
		return false;
	}

	public void atualizarMembro(long matricula,long matriculaNovo, String nomeNovo, String emailNovo, String senhaNova) throws Exception {
		boolean atualizado = false;
		String[] atributo = { "matricula" };
		Object[] valor = { matricula };
		Membro membroAtual = daoMembro.consultarAnd(atributo, valor).iterator().next();

		Membro membroAtualizado = membroAtual;
		membroAtualizado.setMatricula(matriculaNovo);
		membroAtualizado.setNome(nomeNovo);
		membroAtualizado.setEmail(emailNovo);
		membroAtualizado.setSenha(senhaNova);
		if (atualizado) {
			daoMembro.atualizar(membroAtual, membroAtualizado);
			System.out.println("Membro atualizado!");
		} else {
			throw new Exception("O membro nao pode ser atualizado.");

		}

	}

	private boolean validarEmail(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

}
