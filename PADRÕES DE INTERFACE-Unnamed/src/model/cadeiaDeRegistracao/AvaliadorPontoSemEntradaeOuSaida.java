package model.cadeiaDeRegistracao;

import java.util.HashSet;

import model.projetos.Participacao;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {
	public AvaliadorPontoSemEntradaeOuSaida(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(String login) {
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(login)) {
			for (PontoTrabalhado ponto : participacoe.getPontos()) {
				if (ponto.getDataHoraEntrada() == null || ponto.getDataHoraSaida() == null)
					 super.getPontosInvalidos().add(ponto);
			}

		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(login);
	}

}
