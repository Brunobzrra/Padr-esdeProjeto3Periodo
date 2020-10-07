package model.projetos;

import java.util.ArrayList;

public class Projeto extends IntegracaoDeProjeto {

	private ArrayList<IntegracaoDeProjeto> itens = new ArrayList<IntegracaoDeProjeto>();

	private String nome;

	private float aporteCusteioReais;

	private float aporteCapitalReais;

	private float gastoExecutadoCusteioReais;

	private float gastoExecutadoCapitalReais;

	public void adicionar(IntegracaoDeProjeto integracao) {
		integracao.setProjetoPai(this);
		itens.add(integracao);
	}

	public void remover(IntegracaoDeProjeto integracao) {
		integracao.setProjetoPai(null);
		itens.remove(integracao);
	}

	public void mover(IntegracaoDeProjeto integracao) {
		integracao.setProjetoPai(integracao);
		integracao.adicionar(this);
	}

	// metodos obrigatorios
	@Override
	public void ativar() {
		// TODO Auto-generated method stub
		EscolheUmNomeMelhor.ativar(itens, this);
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		EscolheUmNomeMelhor.destivar(itens, this);
	}

	@Override
	public float getCustoTotal() {
		// TODO Auto-generated method stub
		aporteCusteioReais = 0;

		for (IntegracaoDeProjeto integracaoDeProjeto : itens) {
			aporteCusteioReais += integracaoDeProjeto.getCustoTotal();
		}
		return aporteCusteioReais;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() {
		// TODO Auto-generated method stub
		gastoExecutadoCusteioReais = 0;

		for (IntegracaoDeProjeto integracaoDeProjeto : itens) {
			gastoExecutadoCusteioReais += integracaoDeProjeto.getCustoTotal();
		}
		return getCustoTotal() - gastoExecutadoCusteioReais;
	}

	public float getCapitalReaiNaoGastoTotal() {
		gastoExecutadoCusteioReais = getCustoTotal();
		return aporteCapitalReais - gastoExecutadoCapitalReais;
	}

	// getters e setters
	public ArrayList<IntegracaoDeProjeto> getItens() {
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
