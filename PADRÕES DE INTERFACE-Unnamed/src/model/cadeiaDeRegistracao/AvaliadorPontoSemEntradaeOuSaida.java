package model.cadeiaDeRegistracao;

import model.projetos.Projeto;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {
	public AvaliadorPontoSemEntradaeOuSaida() {
		setProximo(new AvaliadorPontosForaParticipacaoPrevisao());
	}

	public boolean registarPonto(Projeto projeto, String login) {
		if(recuperarPontos(projeto, login)) {
			return false;
		}
		for (PontoTrabalho ponto : getParticipacao().getPontos()) {
			if (ponto.getDataHoraEntrada() == 0 || ponto.getDataHoraSaida() == 0)
				return false;
		}

		return super.getProximo().registarPonto(projeto, login);
	}


}
