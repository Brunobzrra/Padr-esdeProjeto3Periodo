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
 * Nesta parte do chain é testado se o ponto batido na hora exata
 * 
 * @author Antônio Amorim
 *
 */
public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro) {
		for (Participacao participacao : PegadorDeEmailDoDaoMembro.recuperarParticipacao(membro)) {
			for (PontoTrabalhado ponto : participacao.getPontos()) {
				boolean invalido = true;
				for (HorarioPrevisto horario : participacao.getHorarios()) {
					Object[] horaEDiaEntrada = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraEntrada());
					Object[] horaEDiaSaida = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraSaida());
					LocalDateTime horaInicio = (LocalDateTime) horaEDiaEntrada[0];
					LocalDateTime horaSaida = (LocalDateTime) horaEDiaSaida[0];
					if (horario.getDiaSemana() == (DiaSemana) horaEDiaEntrada[1]) {
						if (horario.getHoraInicio().toLocalTime().equals(horaInicio.toLocalTime())
								|| horario.getHoraTermino().toLocalTime().equals(horaSaida.toLocalTime())) {
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
