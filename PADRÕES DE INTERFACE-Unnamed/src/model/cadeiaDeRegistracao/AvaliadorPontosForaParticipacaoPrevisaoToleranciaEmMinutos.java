package model.cadeiaDeRegistracao;

import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.flyweight.HorarioPrevisto;

public class AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos extends AvaliadorDeRegistro {

	public AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro) {
		for (Participacao participacao : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(membro)) {
			for (PontoTrabalhado ponto : participacao.getPontos()) {
				boolean invalido = true;
				for (HorarioPrevisto horario : participacao.getHorarios()) {
					Object[] horaEDiaEntrada = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraEntrada());
					Object[] horaEDiaSaida = ConversorDeHoraEDia.pegarHoraEDia(ponto.getDataHoraSaida());
					if (horario.getHorarioPrevisto().getDiaSemana() == (DiaSemana) horaEDiaEntrada[1]) {
						if (horario.getHorarioPrevisto().getHoraInicio() <= (long) horaEDiaEntrada[0]
								&& horario.getHorarioPrevisto().getHoraInicio() + horario.getToleranciaMinutos().getToleranciaMinutos() >= (long) horaEDiaEntrada[0]
								&& horario.getHorarioPrevisto().getHoraTermino() <= (long) horaEDiaSaida[0] && horario.getHorarioPrevisto().getHoraTermino()
										+ horario.getToleranciaMinutos().getToleranciaMinutos() >= (long) horaEDiaSaida[0]) {
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
