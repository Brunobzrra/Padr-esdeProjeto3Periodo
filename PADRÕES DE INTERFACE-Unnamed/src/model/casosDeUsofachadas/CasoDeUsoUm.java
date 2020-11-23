package model.casosDeUsofachadas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.autenticacao.Membro;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 1
public class CasoDeUsoUm {

	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void cadastrarMembro(String nome, long matricula, String email, String senha) throws Exception {
		if (validarEmail(email)) {
			Membro membro;
			if (nome != null && email != null && senha != null) {
				membro = new Membro(matricula, nome, email, senha);
			} else {
				throw new Exception("Dados inválidos!");
			}
			if (daoMembro.isVazia()) {
				membro.setAdministrador(true);
			} else {
				membro.setAdministrador(false);
			}
			daoMembro.criar(membro);
			return;
		}
		throw new Exception("Email invalido!");
	}

	public void atualizarMembro(long matricula, long matriculaNovo, String nomeNovo, String emailNovo, String senhaNova)
			throws Exception {
		Membro membroAtual = daoMembro.recuperarPorIndentificador(matricula);

		Membro membroAtualizado = membroAtual;
		membroAtualizado.setMatricula(matriculaNovo);
		membroAtualizado.setNome(nomeNovo);
		membroAtualizado.setEmail(emailNovo);
		membroAtualizado.setSenha(senhaNova);
		daoMembro.atualizar(membroAtual, membroAtualizado);
		System.out.println("Membro atualizado!");
	}

	public Object[] recuperarMembro(long matricula) {
		Membro membro = daoMembro.recuperarPorIndentificador(matricula);
		Object[] dados = { membro.getNome(), membro.getEmail(), membro.getSenha() };
		return dados;
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
