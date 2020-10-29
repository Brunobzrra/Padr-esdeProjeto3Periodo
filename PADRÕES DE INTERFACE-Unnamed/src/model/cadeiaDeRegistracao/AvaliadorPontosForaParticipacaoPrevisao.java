package model.cadeiaDeRegistracao;

import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login) {
		Object[] horaEDia = pegarHoraEDia();
		for (HorarioPrevisto horarioPrevisto : super.getParticipacao().getHorarios()) {
			if (horarioPrevisto.getDiaSemana() == (DiaSemana) horaEDia[1]) {
				if (horarioPrevisto.getHoraInicio() == (long) horaEDia[0]
						|| horarioPrevisto.getHoraTermino() == (long) horaEDia[0]) {
					return getProximo().justificarPontoInvalido(ponto, justificativa, login);
				}
			}
		}
		
		return false;
	}

}
