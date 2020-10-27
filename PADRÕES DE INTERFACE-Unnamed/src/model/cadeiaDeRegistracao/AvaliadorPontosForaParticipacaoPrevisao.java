package model.cadeiaDeRegistracao;

import model.projetos.Projeto;

public class AvaliadorPontosForaParticipacaoPrevisao extends AvaliadorDeRegistro {
	public AvaliadorPontosForaParticipacaoPrevisao() {
		setProximo(new AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos());
	}
	public boolean registarPonto(Projeto projeto, String login) {
		return false;
	}
}
