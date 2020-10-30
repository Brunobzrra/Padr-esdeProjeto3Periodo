package model.cadeiaDeRegistracao;

import java.util.HashSet;

import model.projetos.Participacao;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(String login) {
		for (Participacao participacao : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(login)) {
			for (PontoTrabalhado ponto : participacao.getPontos()) {
				boolean invalido = true;
				for (HorarioPrevisto horario : participacao.getHorarios()) {
					Object[] horaEDiaEntrada = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraEntrada());
					Object[] horaEDiaSaida = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraSaida());
					if (horario.getDiaSemana() == (DiaSemana) horaEDiaEntrada[1]) {
						if (horario.getHoraInicio() == (long) horaEDiaEntrada[0]
								|| horario.getHoraTermino() == (long) horaEDiaSaida[0]) {
							invalido = false;
						}
					}
				}
				if(invalido) {
					 super.getPontosInvalidos().add(ponto);
				}
			}
		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(login);
	}
}
