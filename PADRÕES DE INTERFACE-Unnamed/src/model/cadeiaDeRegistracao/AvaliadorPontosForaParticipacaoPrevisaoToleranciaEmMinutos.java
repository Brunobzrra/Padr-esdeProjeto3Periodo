package model.cadeiaDeRegistracao;

import model.projetos.Participacao;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos extends AvaliadorDeRegistro {

	public AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	@Override
	public boolean getPontosInvalidos(String login) {
		Object[] horaEDia = pegarHoraEDia();
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(login)) {
			for (HorarioPrevisto horarioPrevisto : participacoe.getHorarios()) {
				if (horarioPrevisto.getDiaSemana() == (DiaSemana) horaEDia[1]) {
					long horaExata = (long) horaEDia[0];
					if (horarioPrevisto.getHoraInicio() <= horaExata
							&& horarioPrevisto.getHoraInicio() + horarioPrevisto.getToleranciaMinutos() >= horaExata) {
						return super.getProximo().getPontosInvalidos(login);
					}
					if (horarioPrevisto.getHoraTermino() <= horaExata
							&& horarioPrevisto.getHoraTermino() + horarioPrevisto.getToleranciaMinutos() >= horaExata) {
						return super.getProximo().getPontosInvalidos(login);
					}
				}
			}
		}
		return super.add(login);

	}

}
