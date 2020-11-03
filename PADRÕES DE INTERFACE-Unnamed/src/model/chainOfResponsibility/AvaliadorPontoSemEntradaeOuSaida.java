package model.chainOfResponsibility;

import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.PontoTrabalhado;

/**
 * Nesta parte do chain é testado se o ponto foi criado e tem seus pontos de entrada e saida corretos
 * @author Antônio Amorim
 *
 */
public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {
	public AvaliadorPontoSemEntradaeOuSaida(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro) {
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacao(membro)) {
			for (PontoTrabalhado ponto : participacoe.getPontos()) {
				if (ponto.getDataHoraEntrada() == null || ponto.getDataHoraSaida() == null)
					 super.getPontosInvalidos().add(ponto);
			}

		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(membro);
	}

}
