package model.autenticacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContaEmailIFPB extends ContaEmail {
	/*
	 * Antes de autenticar o metodo valida o email
	 * @see model.autenticacao.ContaEmail#autenticar(java.lang.String, java.lang.String)
	 */
	public Membro autenticar(String login, String senha) {
		if(!validarLogin(login))
			return null;
		return super.getConta().autenticar(login, senha);
	}
	
	@Override
	/**
	 * Nesse metodo primeiro testa se o email é um email academico
	 */
	public boolean validarLogin(String email) {
		// TODO Auto-generated method stub
		boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches() && email.contains("@academico.ifpb.edu.br")) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
	}

}
