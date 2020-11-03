package model.utilitarios;

import java.util.ArrayList;
import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.ProjetoComponente;
/**
 * Classe responsavel por recuperar as participações de um membro
 * @author Antônio Amorim
 *
 */
public class PegadorDeEmailDoDaoMembro {

	public static ArrayList<Participacao> recuperarParticipacao(Membro membro) {
		ArrayList<Participacao> participacoes = new ArrayList<Participacao>();
		for (ProjetoComponente participacaoFor : membro.getParticipacoes()) {
			Participacao participacao = (Participacao) participacaoFor;
			participacoes.add(participacao);
		}
		return participacoes;
	}
}
