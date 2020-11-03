package model.chainOfResponsibility;

import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.PontoTrabalhado;

/**
 * Verfica se pontos tem sua justicativa de falta aceita ou não
 * @author Antônio Amorim
 *
 */
public class AvaliadorPontosInvalidosComJustificativaNaoAceita extends AvaliadorDeRegistro {
	public AvaliadorPontosInvalidosComJustificativaNaoAceita(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro) {
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacao(membro)) {
			for (PontoTrabalhado ponto : participacoe.getPontos()) {
				if (!ponto.isJustificativaAceita()) 
					super.getPontosInvalidos().add(ponto);
				}

		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(membro);
	}

}
