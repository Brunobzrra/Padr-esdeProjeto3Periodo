package model.projetos;

import java.util.ArrayList;

import model.autenticacao.Membro;

public class Projeto extends ProjetoComponente {

	private ArrayList<ProjetoComponente> itens = new ArrayList<ProjetoComponente>();

	private String nome;

	private float aporteCusteioReais;

	private float aporteCapitalReais;

	private float gastoExecutadoCusteioReais;

	private float gastoExecutadoCapitalReais;

	
	public Projeto(String nome, float aporteCusteioReais, float aporteCapitalReais, float gastoExecutadoCusteioReais,
			float gastoExecutadoCapitalReais) {
		this.nome = nome;
		this.aporteCusteioReais = aporteCusteioReais;
		this.aporteCapitalReais = aporteCapitalReais;
		this.gastoExecutadoCusteioReais = gastoExecutadoCusteioReais;
		this.gastoExecutadoCapitalReais = gastoExecutadoCapitalReais;
	}

	public void adicionar(Membro integracao) throws Exception {
		integracao.setProjetoPai(this);
		itens.add(integracao);
	}

	public void remover(ProjetoComponente integracao) throws Exception {
		itens.remove(integracao);
		integracao.setProjetoPai(null);
	}

	public void mover(ProjetoComponente integracao) throws Exception {
		if (integracao instanceof Grupo) {
			integracao.setProjetoPai(integracao);
			try {
				integracao.adicionar(this);
			} catch (Exception e) {
				e.getMessage();
			}
		}
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

	@Override
	public float getCustoTotal() throws Exception {
		return gastoExecutadoCapitalReais;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		return aporteCusteioReais - gastoExecutadoCusteioReais;
	}

	public float getCapitalReaiNaoGastoTotal() throws Exception {
		return aporteCapitalReais - gastoExecutadoCapitalReais;
	}

	// getters e setters
	public ArrayList<ProjetoComponente> getItens() {
		return itens;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getAporteCusteioReais() {
		return aporteCusteioReais;
	}

	public void setAporteCusteioReais(float aporteCusteioReais) {
		this.aporteCusteioReais = aporteCusteioReais;
	}

	public float getAporteCapitalReais() {
		return aporteCapitalReais;
	}

	public void setAporteCapitalReais(float aporteCapitalReais) {
		this.aporteCapitalReais = aporteCapitalReais;
	}

	public float getGastoExecutadoCusteioReais() {
		return gastoExecutadoCusteioReais;
	}

	public void setGastoExecutadoCusteioReais(float gastoExecutadoCusteioReais) {
		this.gastoExecutadoCusteioReais = gastoExecutadoCusteioReais;
	}

	public float getGastoExecutadoCapitalReais() {
		return gastoExecutadoCapitalReais;
	}

	public void setGastoExecutadoCapitalReais(float gastoExecutadoCapitalReais) {
		this.gastoExecutadoCapitalReais = gastoExecutadoCapitalReais;
	}

}
