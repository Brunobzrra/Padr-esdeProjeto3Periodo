package model.projetos;

import java.io.Serializable;
import java.util.ArrayList;

import model.autenticacao.Membro;

public class Projeto extends ProjetoComponente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * coleção de participacao
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
		setTipo(TipoProjetoComponente.PROJETO);
	}

	public void adicionar(ProjetoComponente item) throws Exception {
		if (item.getTipo() == TipoProjetoComponente.PARTICIPACAO) {
			for (ProjetoComponente projetoComponente : itens) {
				if (item.equals(projetoComponente)) {
					throw new Exception("Este item ja existe aqui!");
				}
			}
			item.setProjetoPai(this);
			itens.add(item);
		}
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
		modificarAtivo(itens, this, true);
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		modificarAtivo(itens, this, false);
	}

	@Override
	public float getCustoTotal() throws Exception {
		float valorFinal = 0;
		for (ProjetoComponente projetoComponente : itens) {
			valorFinal += projetoComponente.getCustoTotal();
		}
		return valorFinal;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		float valorFinal = 0;
		for (ProjetoComponente projetoComponente : itens) {
			valorFinal += projetoComponente.getCapitalReaisNaoGastoTotal();
		}
		return valorFinal + (aporteCusteioReais - gastoExecutadoCusteioReais - valorFinal);
	}

	public float getCapitalReaisNaoGastoTotal() throws Exception {
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

	public float getGastoExecutadoCusteioReais() {
		return gastoExecutadoCusteioReais;
	}

	public float getGastoExecutadoCapitalReais() {
		return gastoExecutadoCapitalReais;
	}

	public boolean equals(Projeto projeto) {
		if (projeto.getNome().equals(nome)) {
			return true;
		}
		return false;
	}

	public Membro getCordenador() {
		for (ProjetoComponente projetoComponente : itens) {
			Participacao participacao = (Participacao) projetoComponente;
			if (participacao.isCoordenador()) {
				return participacao.getMembro();
			}
		}
		return null;
	}

	public Participacao getMembro(long matricula) {
		for (ProjetoComponente projetoComponente : itens) {
			Participacao participacao = (Participacao) projetoComponente;
			if (participacao.getMembro().getMatricula() == matricula) {
				return participacao;
			}
		}
		return null;
	}

	public float getGastoTotal() {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getGastoTotal();
		}
		return aux + getCusteioReaisGastoTotal() + getCapitalReaisGastoTotal();
	}

	public float getCusteioReaisGastoTotal() {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCusteioReaisGastoTotal();
		}
		return gastoExecutadoCusteioReais + aux;
	}

	public float getCapitalReaisGastoTotal() {
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCapitalReaisGastoTotal();
		}
		return gastoExecutadoCapitalReais + aux;
	}

	public boolean buscarComponente(ProjetoComponente comonente) throws Exception {
		for (ProjetoComponente projetoComponente : itens) {
			Participacao participacao=(Participacao) projetoComponente;
			if(participacao.getMembro()==comonente) {
				return true;
			}
		}
		return false;
	}
	public String toStringHTML() {
		String texto = String.format(
				"<span>Projeto %s </span><br>\n<span>Aporte Custeio Reais %s </span><br>\n"
				+ "<span>Aporte Capital Reais %s </span><br>\n<span>Gasto Executado Custeio Reais %s </span><br>"
				+ "\n<span>gasto Executado Capital Reais %s </span><br>\n<span>Membros </span><br>\n",
				nome, aporteCusteioReais, aporteCapitalReais, gastoExecutadoCusteioReais, gastoExecutadoCapitalReais);
		for (ProjetoComponente projetoComponente : itens) {
			texto += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
		}
		return texto;
	}
}
