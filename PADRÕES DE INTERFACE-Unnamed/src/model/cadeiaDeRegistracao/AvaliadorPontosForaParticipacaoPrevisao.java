package model.cadeiaDeRegistracao;

import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.flyweight.HorarioPrevistoExatoFlyweight;

public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro) {
		for (Participacao participacao : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(membro)) {
			for (PontoTrabalhado ponto : participacao.getPontos()) {
				boolean invalido = true;
				for (HorarioPrevistoExatoFlyweight horario : participacao.getHorarios()) {
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
		return super.getProximo().getPontosInvalidos(membro);
	}
}
