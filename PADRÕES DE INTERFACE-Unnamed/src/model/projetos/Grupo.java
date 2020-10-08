package model.projetos;

import java.util.ArrayList;
import java.util.Date;

public class Grupo extends IntegracaoDeProjeto{

	private String nome;

	private Date dataCriacao;

	private String linkCNPq;

	private ArrayList<IntegracaoDeProjeto> itens = new ArrayList<IntegracaoDeProjeto>();
	
	public void adicionar(IntegracaoDeProjeto integracao) {
		integracao.setProjetoPai(this);
		itens.add(integracao);
	}

	public void remover(IntegracaoDeProjeto integracao) {
		integracao.setProjetoPai(null);
		itens.remove(integracao);
	}

	public float getCapitalReaiNaoGastoTotal() {
		return 0;
	}
	//metodos obrigatorios
	@Override
	public void ativar() {
		// TODO Auto-generated method stub
		setAtivo(true);
		for (IntegracaoDeProjeto integracaoDeProjeto : itens) {
			integracaoDeProjeto.ativar();
		}
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		setAtivo(false);
		for (IntegracaoDeProjeto integracaoDeProjeto : itens) {
			integracaoDeProjeto.desativar();
		}
	}

	@Override
	public float getCustoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//getters e setters
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
