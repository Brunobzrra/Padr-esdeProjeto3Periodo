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
 * Nesta parte do chain verifica se o ponto foi batido ate o horario de tolerancia
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
					Object[] horaEDiaSaida = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraSaida());
					if (horario.getDiaSemana() == (DiaSemana) horaEDiaEntrada[1]) {
						int tolerancia =horario.getMinutosTolerante() ;
						LocalDateTime horaEntrada=(LocalDateTime) horaEDiaEntrada[0];
						LocalDateTime horaSaida=(LocalDateTime)horaEDiaSaida[0];
						if (horario.getHoraInicio() == horaEntrada.getHour()
								&& tolerancia >= horaEntrada.getMinute()
								&& horario.getHoraTermino() <= horaSaida.getHour()
								&&  tolerancia >= horaSaida.getMinute() ){
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
