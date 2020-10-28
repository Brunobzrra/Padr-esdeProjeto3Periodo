package model.cadeiaDeRegistracao;

import model.projetos.Projeto;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;

public class AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos extends AvaliadorDeRegistro {

	public AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos() {
		setProximo(new AvaliadorPontosInvalidosComJustificativaNaoAceita());
	}

	public boolean registarPonto(Projeto projeto, String login) {
		if (recuperarPontos(projeto, login)) {
			Object[] horaEDia=pegarHoraEDia();
			for (HorarioPrevisto horarioPrevisto : super.getParticipacao().getHorarios()) {
				if (horarioPrevisto.getDiaSemana() == (DiaSemana)horaEDia[1]) {
					long horaExata=(long)horaEDia[0];
					if (horarioPrevisto.getHoraInicio() <= horaExata && horarioPrevisto.getHoraInicio()+horarioPrevisto.getToleranciaMinutos()>= horaExata) {
						return getProximo().registarPonto(projeto, login);
					}
					if(horarioPrevisto.getHoraTermino() <= horaExata && horarioPrevisto.getHoraTermino()+horarioPrevisto.getToleranciaMinutos()>= horaExata) {
						return getProximo().registarPonto(projeto, login);
					}
				}
			}
		}
		return false;
	}

}
