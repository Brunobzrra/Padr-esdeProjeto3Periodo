package model.cadeiaDeRegistracao;

import model.projetos.Projeto;

public class AvaliadorPontosInvalidosComJustificativaNaoAceita extends AvaliadorDeRegistro {
	public AvaliadorPontosInvalidosComJustificativaNaoAceita(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}
	public boolean registarPonto(Projeto projeto, String login) {
		return false;
	}

}
