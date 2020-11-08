package model.projetos;

import java.util.ArrayList;
import java.util.Date;

public class Edital extends ProjetoComponente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	private Date dataInicio;

	private Date dataTermino;
	/**
	 * coleção de grupos e projestos
	 */
	private ArrayList<ProjetoComponente> itens = new ArrayList<ProjetoComponente>();

	public Edital(String nome, Date dataInicio, Date dataTermino) {
		super();
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		setTipo(TipoProjetoComponente.EDITAL);
	}

	public void adicionar(ProjetoComponente item) throws Exception {
		if (item.getTipo() == TipoProjetoComponente.GRUPO || item.getTipo() == TipoProjetoComponente.PROJETO) {
			for (ProjetoComponente projetoComponente : itens) {
				if (item.equals(projetoComponente)) {
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
		modificarAtivo(itens, this, true);
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		modificarAtivo(itens, this, false);

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

	public float getGastoTotal(){
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getGastoTotal();
		}
		return aux+getCusteioReaisGastoTotal()+getCapitalReaiGastoTotal();
	}

	public float getCusteioReaisGastoTotal(){
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCusteioReaisGastoTotal();
		}
		return aux;
	}

	public float getCapitalReaiGastoTotal(){
		float aux = 0;
		for (ProjetoComponente projetoComponente : itens) {
			aux += projetoComponente.getCapitalReaiGastoTotal();
		}
		return aux;
	}

}