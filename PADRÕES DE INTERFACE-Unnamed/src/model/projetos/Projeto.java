package model.projetos;

import java.util.ArrayList;

public class Projeto extends IntegracaoDeProjeto {

	private ArrayList<IntegracaoDeProjeto> itens = new ArrayList<IntegracaoDeProjeto>();

	private String nome;

	private float aporteCusteioReais;

	private float aporteCapitalReais;

	private float gastoExecutadoCusteioReais;

	private float gastoExecutadoCapitalReais;

	public void adicionar(Edital integracao) throws Exception {
			integracao.setProjetoPai(this);
			itens.add(integracao);
	}

	public void remover(IntegracaoDeProjeto integracao) throws Exception {
		itens.remove(integracao);
		integracao.setProjetoPai(null);
	}

	public void mover(IntegracaoDeProjeto integracao) throws Exception {
		integracao.setProjetoPai(integracao);
		try {
			integracao.adicionar(this);
		} catch (Exception e) {
			e.getMessage();
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
		// TODO Auto-generated method stub
		aporteCusteioReais = 0;

		for (IntegracaoDeProjeto integracaoDeProjeto : itens) {
			try {
				aporteCusteioReais += integracaoDeProjeto.getCustoTotal();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
		return aporteCusteioReais;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		gastoExecutadoCusteioReais = 0;

		for (IntegracaoDeProjeto integracaoDeProjeto : itens) {
			try {
				gastoExecutadoCusteioReais += integracaoDeProjeto.getCusteioReaisNaoGastoTotal();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
		return getCustoTotal() - gastoExecutadoCusteioReais;
	}

	public float getCapitalReaiNaoGastoTotal() throws Exception {
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
