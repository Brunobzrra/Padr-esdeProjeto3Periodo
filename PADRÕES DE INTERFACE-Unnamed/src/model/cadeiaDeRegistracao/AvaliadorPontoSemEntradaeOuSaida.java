package model.cadeiaDeRegistracao;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {

	public AvaliadorPontoSemEntradaeOuSaida() {
		setProximo(new AvaliadorPontosForaParticipacaoPrevisao());
	}

	public boolean registarPonto(Projeto projeto, String login) {
		for (ProjetoComponente membro1 : projeto.getItens()) {
			Membro membro2= (Membro) membro1;
			for (ProjetoComponente participacao1 : membro2.getParticipacoes()) {
				Participacao particpacao2= (Participacao) participacao1;
				for (PontoTrabalho ponto : particpacao2.getPontos()) {
					if(ponto.getDataHoraEntrada()==0 || ponto.getDataHoraSaida()==0)
						return false;
				}				
			}
		}
		return super.getProximo().registarPonto(projeto, login);
	}

}
