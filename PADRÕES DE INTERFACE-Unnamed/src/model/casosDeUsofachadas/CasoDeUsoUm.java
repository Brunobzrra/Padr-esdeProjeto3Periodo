package model.casosDeUsofachadas;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.autenticacao.Membro;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 1
public class CasoDeUsoUm {
	
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void cadastrarMembro(String nome, long matricula, String email, String senha) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Cadastrando Membro");
		if (validarEmail(email) == true) {
			Membro membro;
			if (nome != null && email != null && senha != null) {
				membro = new Membro(matricula, nome, email, senha);
			} else {
				LoggerProjeto.getInstance().getLogger().severe("Dados invalidos para cadastro");
				throw new Exception("Dados inválidos!");
			}
			LoggerProjeto.getInstance().getLogger().info("Verificando se o membro eh elegivel a admin");
			if (daoMembro.isVazia()) {
				membro.setAdministrador(true);
			} else {
				LoggerProjeto.getInstance().getLogger().info("ja existe administrador");
				membro.setAdministrador(false);
			}
			LoggerProjeto.getInstance().getLogger().info("Tentando cadastrar membro");
			daoMembro.criar(membro);
			LoggerProjeto.getInstance().getLogger().warning("Cadastro feito com sucesso");
			return;
		}
		LoggerProjeto.getInstance().getLogger().severe("Email invalido!");
		throw new Exception("Email invalido!");
	}

	public void atualizarMembro(long matricula, long matriculaNovo, String nomeNovo, String emailNovo, String senhaNova)
			throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Atualizando");
		Membro membroAtual = daoMembro.recuperarPorIndentificador(matricula);
		Membro membroAtualizado = membroAtual;
		membroAtualizado.setMatricula(matriculaNovo);
		membroAtualizado.setNome(nomeNovo);
		membroAtualizado.setEmail(emailNovo);
		membroAtualizado.setSenha(senhaNova);
		LoggerProjeto.getInstance().getLogger().info("Tentando atualizar membro");
		daoMembro.atualizar(membroAtual, membroAtualizado);
		LoggerProjeto.getInstance().getLogger().info("Membro atualizado com sucesso");
	}

	private boolean validarEmail(String email) throws Exception {
		boolean isEmailIdValid = false;
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Validando");
		LoggerProjeto.getInstance().getLogger().info("Verificando se o email eh valido");
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
				LoggerProjeto.getInstance().getLogger().info("Email valido");
			}
		}
		return isEmailIdValid;
	}
	public boolean isVazia() {
		return daoMembro.isVazia();
	}
}
