package model.cadeiaDeRegistracao;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontosComIntervalosConflitantes extends AvaliadorDeRegistro {
	public AvaliadorPontosComIntervalosConflitantes(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}



	@Override
	public boolean getPontosInvalidos(String login) {
		for (PontoTrabalhado element : getParticipacao().getPontos()) {
			Period p = new Period(element.getDataHoraEntrada(), element.getDataHoraSaida());
			
		}

		
		
		
		return false;
	}

}
