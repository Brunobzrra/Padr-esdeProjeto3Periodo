package model.chainOfResponsibility;

import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.flyweight.HorarioPrevistoExatoFlyweight;

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
				for (HorarioPrevistoExatoFlyweight horario : participacao.getHorarios()) {
					Object[] horaEDiaEntrada = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraEntrada());
					Object[] horaEDiaSaida = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraSaida());
					if (horario.getDiaSemana() == (DiaSemana) horaEDiaEntrada[1]) {
						int tolerancia =horario.getMinutosTolerantes().getToleranciaMinutos() ;
						if (horario.getHoraInicio() <= (long) horaEDiaEntrada[0]
								&& horario.getHoraInicio()
										+ tolerancia >= (long) horaEDiaEntrada[0]
								&& horario.getHoraTermino() <= (long) horaEDiaSaida[0]
								&& horario.getHoraTermino()
										+ tolerancia >= (long) horaEDiaSaida[0]) {
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
