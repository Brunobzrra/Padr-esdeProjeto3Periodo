package model.projetos;

import java.util.ArrayList;
import java.util.Date;

public class Edital extends ProjetoComponente {

	private String nome;

	private Date dataInicio;

	private Date dataTermino;

	private ArrayList<ProjetoComponente> itens = new ArrayList<ProjetoComponente>();
	
	public void adicionar(Grupo integracao) throws Exception {
			integracao.setProjetoPai(this);
			itens.add(integracao);
	}
	public void adicionar(Projeto integracao) throws Exception {
		integracao.setProjetoPai(this);
		itens.add(integracao);
	}

	public void remover(ProjetoComponente integracao) throws Exception {
		itens.remove(integracao);
		integracao.setProjetoPai(null);
	}

	@Override
	public void mover(ProjetoComponente integracao) throws Exception {
		// TODO Auto-generated method stub
		integracao.setProjetoPai(integracao);
		try {
			integracao.adicionar(this);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public float getCapitalReaiNaoGastoTotal() throws Exception {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCapitalReaiNaoGastoTotal();
		}
		return aux;
	}

	@Override
	public float getCustoTotal() throws Exception {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCustoTotal();
		}
		return aux;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCusteioReaisNaoGastoTotal();
		}
		return aux;
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	public ArrayList<ProjetoComponente> getItens() {
		return itens;
	}
	
	public boolean equals(Edital edital) {
		if (edital.getNome().equals(nome)) {
			return true;
		}
		return false;
	}
}