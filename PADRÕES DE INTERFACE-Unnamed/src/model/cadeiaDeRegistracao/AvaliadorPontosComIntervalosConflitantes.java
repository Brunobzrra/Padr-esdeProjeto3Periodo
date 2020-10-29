package model.cadeiaDeRegistracao;

import model.projetos.Projeto;

public class AvaliadorPontosComIntervalosConflitantes extends AvaliadorDeRegistro {
	public AvaliadorPontosComIntervalosConflitantes(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}
	public boolean registarPonto(Projeto projeto, String login) {
		return false;
	}

}
