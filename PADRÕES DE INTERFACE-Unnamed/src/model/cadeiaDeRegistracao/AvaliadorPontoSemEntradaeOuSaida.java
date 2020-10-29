package model.cadeiaDeRegistracao;

import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {
	public AvaliadorPontoSemEntradaeOuSaida(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}
	public boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login) {
		if (ponto.getDataHoraEntrada() == null || ponto.getDataHoraSaida() == null)
			return false;	
		return super.getProximo().justificarPontoInvalido(ponto, justificativa, login);
	}


}
