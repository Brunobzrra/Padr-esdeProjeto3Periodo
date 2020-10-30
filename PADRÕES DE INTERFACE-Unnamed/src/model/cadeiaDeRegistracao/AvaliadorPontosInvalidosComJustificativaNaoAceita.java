package model.cadeiaDeRegistracao;

import model.projetos.Projeto;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontosInvalidosComJustificativaNaoAceita extends AvaliadorDeRegistro {
	public AvaliadorPontosInvalidosComJustificativaNaoAceita(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}


	@Override
	public boolean getPontosInvalidos(String login) {
		// TODO Auto-generated method stub
		return false;
	}

}
