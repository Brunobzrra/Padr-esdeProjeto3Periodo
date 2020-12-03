package model.projetos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe principal do composite =, interfaxce emcomum dos itens
 * 
 * @author Ant�nio Amorim
 *
 */
public abstract class ProjetoComponente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean ativo = true;

	private ProjetoComponente projetoPai;
	
	private TipoProjetoComponente tipo;
	
	public ProjetoComponente getProjetoPai() {
		return projetoPai;
	}

	public void setProjetoPai(ProjetoComponente projetoPai) {
		this.projetoPai = projetoPai;
	}

	public void remover(ProjetoComponente integracao) throws Exception {
		throw new Exception("Este objeto n�o se move!");
	}

	// metodos que precis�o ser abstratatos pois estar�o em todas as classes
	public abstract void ativar();

	public abstract void desativar();

	public abstract float getCustoTotal() throws Exception;

	public abstract float getCusteioReaisNaoGastoTotal() throws Exception;

	public abstract String getNome();

	public abstract void adicionar(ProjetoComponente item) throws Exception;

	public abstract void mover(ProjetoComponente integracao) throws Exception;

	public abstract float getCapitalReaisNaoGastoTotal() throws Exception;
	
	public abstract float getGastoTotal();
	
	public abstract float getCusteioReaisGastoTotal();

	public abstract float getCapitalReaisGastoTotal();
	// metodos que precis�o ser n�o abstratos pois s� estar�o em algumas as classes

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * ativa todos os objetos daquela cole��o ou desativa de acordo com o desejo do
	 * cliente
	 * 
	 * @param itens
	 * @param integracaoDeProjeto
	 */
	protected void modificarAtivo(ArrayList<ProjetoComponente> itens, ProjetoComponente integracaoDeProjeto,
			boolean ativar) {
		if (ativar) {
			integracaoDeProjeto.setAtivo(true);
			for (ProjetoComponente item : itens) {
				item.ativar();
			}
		} else {
			integracaoDeProjeto.setAtivo(false);
			for (ProjetoComponente item : itens) {
				item.desativar();
			}
		}
	}
	public boolean buscarComponente(ProjetoComponente comonente)throws Exception {
		throw new Exception("Aqui n�o existe coel��o");
	}
	public TipoProjetoComponente getTipo() {
		return tipo;
	}

	public void setTipo(TipoProjetoComponente tipo) {
		this.tipo = tipo;
	}
}
