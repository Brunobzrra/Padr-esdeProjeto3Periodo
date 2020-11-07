package model.projetos;

import java.util.ArrayList;

public class Projeto extends ProjetoComponente  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * coleção de membro
	 */
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
		setTipo(TipoProjetoComponente.MEMBRO);
	}

	public void adicionar(ProjetoComponente item) throws Exception {
		if(item.getTipo()==TipoProjetoComponente.PARTICIPACAO) {
			for (ProjetoComponente projetoComponente : itens) {
				if(item.equals(projetoComponente)) {
					throw new Exception("Este item ja existe aqui!");
				}
			}
		}
		itens.add(item);
	}

	public void remover(ProjetoComponente integracao) throws Exception {
		itens.remove(integracao);
		integracao.setProjetoPai(null);
	}

	public void mover(ProjetoComponente integracao) throws Exception {
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
		modificarAtivo(itens, this,true);
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		modificarAtivo(itens, this,false);
	}

	@Override
	public float getCustoTotal() throws Exception {
		float valorFinal= 0;
		for (ProjetoComponente projetoComponente : itens) {
			valorFinal+=projetoComponente.getCustoTotal();
		}
		return valorFinal;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		float valorFinal= 0;
		for (ProjetoComponente projetoComponente : itens) {
			valorFinal+=projetoComponente.getCapitalReaiNaoGastoTotal();
		}
		return valorFinal+(aporteCusteioReais-gastoExecutadoCusteioReais-valorFinal);
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

	public void setGastoExecutadoCusteioReais(float gastoExecutadoCusteioReais) {
		this.gastoExecutadoCusteioReais = gastoExecutadoCusteioReais;
	}

	public void setGastoExecutadoCapitalReais(float gastoExecutadoCapitalReais) {
		this.gastoExecutadoCapitalReais = gastoExecutadoCapitalReais;
	}
	public boolean equals(Projeto projeto) {
		if (projeto.getNome().equals(nome)) {
			return true;
		}
		return false;
	}

	public float getGastoTotal() throws Exception {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getGastoTotal();
		}
		return aux+getCusteioReaisGastoTotal()+getCapitalReaiGastoTotal();
	}

	public float getCusteioReaisGastoTotal() throws Exception {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCusteioReaisGastoTotal();
		}
		return gastoExecutadoCusteioReais+aux;
	}

	public float getCapitalReaiGastoTotal() throws Exception {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCapitalReaiGastoTotal();
		}
		return gastoExecutadoCapitalReais+aux;
	}
}
