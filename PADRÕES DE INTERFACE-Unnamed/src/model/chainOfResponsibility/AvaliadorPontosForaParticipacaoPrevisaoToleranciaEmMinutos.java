package model.chainOfResponsibility;

import java.time.LocalDateTime;
import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalhado;

/**
 * Nesta parte do chain verifica se o ponto foi batido ate o horario de
 * tolerancia
 * 
 * @author Antônio Amorim
 *
 */
public class AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos extends AvaliadorDeRegistro {

	public AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro) {
		for (Participacao participacao : PegadorDeEmailDoDaoMembro.recuperarParticipacao(membro)) {
			for (PontoTrabalhado ponto : participacao.getPontos()) {
				boolean invalido = true;
				for (HorarioPrevisto horario : participacao.getHorarios()) {
					Object[] horaEDiaEntrada = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraEntrada());
					if (horario.getDiaSemana() == (DiaSemana) horaEDiaEntrada[1]) {
						LocalDateTime horaEntrada = (LocalDateTime) horaEDiaEntrada[0];
						if (horaEntrada.isAfter(horario.getHoraInicio().plusMinutes(horario.getMinutosTolerante()))
								&& horaEntrada.isBefore(horario.getHoraInicio().minusMinutes(1))) {
							invalido = false;
						}
					}
				}
				if (invalido) {
					super.getPontosInvalidos().add(ponto);
				}
			}
		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(membro);
	}
}
