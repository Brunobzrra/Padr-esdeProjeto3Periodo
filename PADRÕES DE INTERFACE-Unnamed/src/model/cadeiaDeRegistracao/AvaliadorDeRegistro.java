package model.cadeiaDeRegistracao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;


import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.PontoTrabalhado;

public abstract class AvaliadorDeRegistro {
	private AvaliadorDeRegistro proximo;
	/**
	 * Este HashSet serve para não colocar mais de uma vesz um mesmo ponto,
	 * essa coleção vai acumulando durante a cadeia para guardar pontos invalidos.
	 */
	private HashSet<PontoTrabalhado> pontosInvalidos = new HashSet<PontoTrabalhado>();

	public abstract HashSet<PontoTrabalhado> getPontosInvalidos(String login);

	public AvaliadorDeRegistro getProximo() {
		return proximo;
	}

	public void setProximo(AvaliadorDeRegistro proximo) {
		this.proximo = proximo;
	}


	public HashSet<PontoTrabalhado> getPontosInvalidos() {
		return pontosInvalidos;
	}

	public void setPontosInvalidos(HashSet<PontoTrabalhado> pontosInvalidos) {
		this.pontosInvalidos = pontosInvalidos;
	}
}
