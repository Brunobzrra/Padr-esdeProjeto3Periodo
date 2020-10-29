package model.cadeiaDeRegistracao;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import model.projetos.Projeto;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontosComIntervalosConflitantes extends AvaliadorDeRegistro {
	public AvaliadorPontosComIntervalosConflitantes(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}
	public boolean registarPonto(Projeto projeto, String login) {
		DateTime registro = DateTime.now();

		if(super.recuperarPontos(projeto, login)) {
			
			for (PontoTrabalho element : getParticipacao().getPontos()) {
				Period p = new Period(element.getDataHoraEntrada(), element.getDataHoraSaida());
				
				if() {
					
				}
			}
			getParticipacao().getPontos();
		}
		
		
		return false;
	}

}
