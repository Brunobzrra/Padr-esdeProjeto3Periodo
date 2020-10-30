package model.cadeiaDeRegistracao;

import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.ProjetoComponente;
import ponto.model.projetos.PontoTrabalhado;

public class AvaliadorPontoSemEntradaeOuSaida extends AvaliadorDeRegistro {
	public AvaliadorPontoSemEntradaeOuSaida(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

//	public boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login) {
//		if (ponto.getDataHoraEntrada() == null || ponto.getDataHoraSaida() == null)
//			return false;	
//		return super.getProximo().justificarPontoInvalido(ponto, justificativa, login);
//	}
	@Override
	public Set<PontoTrabalhado> getPontosInvalidos(String login) {
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacaoPorEmail(login)) {
			for (PontoTrabalhado object : participacoe.getPontos()) {
				if (object.getDataHoraEntrada() == null || object.getDataHoraSaida() == null)
					 super.getPontosInvalidos().add(object);
			}

		}
		return super.getProximo().getPontosInvalidos(login);
	}

}
