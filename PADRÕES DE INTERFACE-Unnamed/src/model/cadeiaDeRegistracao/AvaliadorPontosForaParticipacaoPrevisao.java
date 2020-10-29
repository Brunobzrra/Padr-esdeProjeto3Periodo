package model.cadeiaDeRegistracao;

import model.projetos.Projeto;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;

public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public boolean registarPonto(Projeto projeto, String login) {
		if (recuperarPontos(projeto, login)) {
			Object[] horaEDia=pegarHoraEDia();
			for (HorarioPrevisto horarioPrevisto : super.getParticipacao().getHorarios()) {
				if (horarioPrevisto.getDiaSemana() == (DiaSemana)horaEDia[1]) {
					if (horarioPrevisto.getHoraInicio() == (long)horaEDia[0] || horarioPrevisto.getHoraTermino() == (long)horaEDia[0]) {
						return getProximo().registarPonto(projeto, login);
					}
				}
			}
		}
		return false;
	}

}
