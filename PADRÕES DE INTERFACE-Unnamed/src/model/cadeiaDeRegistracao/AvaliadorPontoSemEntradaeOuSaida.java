package model.cadeiaDeRegistracao;

import model.projetos.Projeto;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {
	public AvaliadorPontoSemEntradaeOuSaida(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public boolean registarPonto(Projeto projeto, String login) {
		if(recuperarPontos(projeto, login)) {
			return false;
		}
		for (PontoTrabalho ponto : getParticipacao().getPontos()) {
			if (ponto.getDataHoraEntrada() == null || ponto.getDataHoraSaida() == null)
				return false;
		}

		return super.getProximo().registarPonto(projeto, login);
	}


}
