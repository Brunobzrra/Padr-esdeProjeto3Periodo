package model.cadeiaDeRegistracao;

import java.util.HashSet;

import model.projetos.Participacao;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontosInvalidosComJustificativaNaoAceita extends AvaliadorDeRegistro {
	public AvaliadorPontosInvalidosComJustificativaNaoAceita(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(String login) {
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(login)) {
			for (PontoTrabalhado ponto : participacoe.getPontos()) {
				if (!ponto.isJustificativaAceita()) 
					super.getPontosInvalidos().add(ponto);
				}

		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(login);
	}

}
