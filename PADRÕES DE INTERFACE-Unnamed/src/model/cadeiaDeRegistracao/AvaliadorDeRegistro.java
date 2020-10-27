package model.cadeiaDeRegistracao;

import model.projetos.Projeto;

public abstract class AvaliadorDeRegistro {
	private AvaliadorDeRegistro proximo;
	public abstract boolean registarPonto(Projeto projeto, String login);
	
	public AvaliadorDeRegistro getProximo() {
		return proximo;
	}
	public void setProximo(AvaliadorDeRegistro proximo) {
		this.proximo = proximo;
	}
	
}
