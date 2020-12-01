package model.chainOfResponsibility;

import java.util.HashSet;

import model.autenticacao.Membro;
import ponto.model.projetos.PontoTrabalhado;
/**
 * superclasse da cadeia do padr�o chain of responsability
 * @author Ant�nio Amorim
 *
 */
public abstract class AvaliadorDeRegistro {
	private AvaliadorDeRegistro proximo;
	/**
	 * Este HashSet serve para n�o colocar mais de uma vesz um mesmo ponto,
	 * essa cole��o vai acumulando durante a cadeia para guardar pontos invalidos.
	 */
	private HashSet<PontoTrabalhado> pontosInvalidos = new HashSet<PontoTrabalhado>();

	public abstract HashSet<PontoTrabalhado> getPontosInvalidos(Membro membro)throws Exception;

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
