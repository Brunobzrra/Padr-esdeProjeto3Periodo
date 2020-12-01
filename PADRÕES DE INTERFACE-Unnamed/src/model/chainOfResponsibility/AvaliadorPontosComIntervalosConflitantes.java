package model.chainOfResponsibility;

import java.util.ArrayList;
import java.util.HashSet;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import ponto.model.projetos.PontoTrabalhado;

/**
 * Esta etapa do chain avalia se pontos os pontos tem a mesma hora de entrada e saida 
 * @author Antônio Amorim
 *
 */
public class AvaliadorPontosComIntervalosConflitantes extends AvaliadorDeRegistro {
	public AvaliadorPontosComIntervalosConflitantes(AvaliadorDeRegistro avaliador) {
		setProximo(avaliador);
	}

	public HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro)throws Exception {
		for (Participacao participacoe : PegadorDeEmailDoDaoMembro.recuperarParticipacao(membro)) {
			ArrayList<PontoTrabalhado> aux = participacoe.getPontos();
			for (PontoTrabalhado ponto1 : participacoe.getPontos()) {
				for (PontoTrabalhado ponto2 : participacoe.getPontos()) {
					if (aux == null) {
						break;
					}
					if (ponto1.getDataHoraEntrada() == ponto2.getDataHoraEntrada()
							&& ponto1.getDataHoraSaida() == ponto2.getDataHoraSaida()) {
						aux.remove(ponto1);
						aux.remove(ponto2);
						super.getPontosInvalidos().add(ponto1);
						super.getPontosInvalidos().add(ponto2);
					}
				}
			}
		}
		getProximo().setPontosInvalidos(getPontosInvalidos());
		return super.getProximo().getPontosInvalidos(membro);
	}

}
