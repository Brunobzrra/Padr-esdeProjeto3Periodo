package model.cadeiaDeRegistracao;

import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos extends AvaliadorDeRegistro {

	public AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login) {
		Object[] horaEDia=pegarHoraEDia();
		for (HorarioPrevisto horarioPrevisto : super.getParticipacao().getHorarios()) {
			if (horarioPrevisto.getDiaSemana() == (DiaSemana)horaEDia[1]) {
				long horaExata=(long)horaEDia[0];
				if (horarioPrevisto.getHoraInicio() <= horaExata && horarioPrevisto.getHoraInicio()+horarioPrevisto.getToleranciaMinutos()>= horaExata) {
					return super.getProximo().justificarPontoInvalido(ponto, justificativa, login);
				}
				if(horarioPrevisto.getHoraTermino() <= horaExata && horarioPrevisto.getHoraTermino()+horarioPrevisto.getToleranciaMinutos()>= horaExata) {
					return super.getProximo().justificarPontoInvalido(ponto, justificativa, login);
				}
			}
		}
	return false;
	}

}
