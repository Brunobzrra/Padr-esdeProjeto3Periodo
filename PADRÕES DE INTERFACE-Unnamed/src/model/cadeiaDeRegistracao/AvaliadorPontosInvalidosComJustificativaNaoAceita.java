package model.cadeiaDeRegistracao;

import model.projetos.Projeto;
import ponto.model.projetos.PontoTrabalho;

public class AvaliadorPontosInvalidosComJustificativaNaoAceita extends AvaliadorDeRegistro {
	public AvaliadorPontosInvalidosComJustificativaNaoAceita(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login) {
		return false;
	}

}
