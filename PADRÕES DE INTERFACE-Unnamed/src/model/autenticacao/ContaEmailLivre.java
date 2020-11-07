package model.autenticacao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContaEmailLivre extends ContaEmail {

	public Membro autenticar(String login, String senha) {
		senha = criptografarSenha(senha);
		if (!validarLogin(login))
			return null;
		return super.getConta().autenticar(login, senha);
	}
	/**
	 * metodo que criptografica a senha para segurança
	 * @param senha
	 * @return
	 */
	public String criptografarSenha(String senha) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] textBytes = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < textBytes.length; i++) {
				buffer.append(Integer.toString((textBytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			senha = buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return senha;
	}

	@Override
	/**
	 * Verifica se o email tem os requisitos nescessarios ex: @email.com
	 */
	public boolean validarLogin(String email) {
		// TODO Auto-generated method stub
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
