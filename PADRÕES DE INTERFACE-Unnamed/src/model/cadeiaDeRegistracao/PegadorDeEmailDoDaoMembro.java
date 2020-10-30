package model.cadeiaDeRegistracao;

import java.util.ArrayList;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.ProjetoComponente;
import persistenia.xml.DAOXMLMembroConta;

public class PegadorDeEmailDoDaoMembro {

	private static DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public static ArrayList<Participacao> recuperarParticipacaoPorEmail(String login) {
		String[] nomeAtributos = { "email" };
		Object[] valores = { login };
		Set<Membro> recuperado = daoMembro.consultarAnd(nomeAtributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro m = (Membro) recuperados[0];
		ArrayList<Participacao> participacoes = new ArrayList<Participacao>();
		try {
			for (ProjetoComponente participacaoFor : m.getParticipacoes()) {
				Participacao participacao = (Participacao) participacaoFor;
				participacoes.add(participacao);
			}
			
		} catch (Exception e) {
			System.out.println("este email não esta cadastrado ! ! !");
		}
		return participacoes;
	
	}


}
