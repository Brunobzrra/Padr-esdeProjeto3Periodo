package model.cadeiaDeRegistracao;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontosComIntervalosConflitantes extends AvaliadorDeRegistro {
	public AvaliadorPontosComIntervalosConflitantes(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login) {
		DateTime registro = DateTime.now();
		
		
		for (PontoTrabalho element : getParticipacao().getPontos()) {
			Period p = new Period(element.getDataHoraEntrada(), element.getDataHoraSaida());
			
		}
		getParticipacao().getPontos();
		
		
		return false;
	}

}
