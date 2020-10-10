package model.projetos;

import java.util.ArrayList;
import java.util.Date;

import model.autenticacao.Membro;

public class Grupo extends IntegracaoDeProjeto {

	private String nome;

	private Date dataCriacao;

	private String linkCNPq;

	private ArrayList<IntegracaoDeProjeto> itens = new ArrayList<IntegracaoDeProjeto>();

	public void adicionar(Membro integracao) throws Exception {	
			integracao.setProjetoPai(this);
			itens.add(integracao);
	}
	public void adicionar(Projeto integracao) throws Exception {
			integracao.setProjetoPai(this);
			itens.add(integracao);
	}

	public void remover(IntegracaoDeProjeto integracao) {
		itens.remove(integracao);
		integracao.setProjetoPai(null);
	}

	@Override
	public void mover(IntegracaoDeProjeto integracao) throws Exception {
		// TODO Auto-generated method stub
		integracao.setProjetoPai(integracao);
		try {
			integracao.adicionar(this);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public float getCapitalReaiNaoGastoTotal()  throws Exception {
		return 0;
	}

	@Override
	public float getCustoTotal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	// metodos obrigatorios
	@Override
	public void ativar() {
		// TODO Auto-generated method stub
		Utilidade.ativar(itens, this);
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		Utilidade.desativar(itens, this);
	}


	// getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getLinkCNPq() {
		return linkCNPq;
	}

	public void setLinkCNPq(String linkCNPq) {
		this.linkCNPq = linkCNPq;
	}

}
