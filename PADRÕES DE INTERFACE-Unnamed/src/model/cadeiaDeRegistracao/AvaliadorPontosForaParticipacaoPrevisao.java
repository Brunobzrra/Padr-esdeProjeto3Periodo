package model.cadeiaDeRegistracao;

import java.util.Set;

import model.projetos.Participacao;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	@Override
	public Set<PontoTrabalhado> getPontosInvalidos(String login) {
		Object[] horaEDia = pegarHoraEDia();
		PontoTrabalhado ponto = null;
		for (Participacao participacao : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(login)) {
			for (PontoTrabalhado pontoFor : participacao.getPontos()) {
				for (HorarioPrevisto horarioPrevisto : participacao.getHorarios()) {
					if (horarioPrevisto.getDiaSemana() == (DiaSemana) horaEDia[1]) {
						if (horarioPrevisto.getHoraInicio() == (long) horaEDia[0]
								|| horarioPrevisto.getHoraTermino() == (long) horaEDia[0]) {
							return getProximo().getPontosInvalidos(login);
						}

						
					}

				}
				ponto = pontoFor;
			}
			
		}
		super.getPontosInvalidos().add(ponto);

	}

}
