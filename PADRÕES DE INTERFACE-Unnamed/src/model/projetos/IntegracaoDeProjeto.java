package model.projetos;

public abstract class IntegracaoDeProjeto {

	private boolean ativo;

	private IntegracaoDeProjeto projetoPai;

	public IntegracaoDeProjeto getProjetoPai() {
		return projetoPai;
	}

	public void setProjetoPai(IntegracaoDeProjeto projetoPai) {
		this.projetoPai = projetoPai;
	}

	// metodos que precisão ser abstratatos pois estarão em todas as classes

	public abstract void ativar();

	public abstract void desativar();

	public abstract float getCustoTotal() throws Exception;

	public abstract float getCusteioReaisNaoGastoTotal() throws Exception;
	
	public abstract void adicionar(IntegracaoDeProjeto integracao) throws Exception;
	
	public abstract void remover(IntegracaoDeProjeto integracao) throws Exception;
	
	public abstract void mover(IntegracaoDeProjeto integracao) throws Exception;
	
	public abstract float getCapitalReaiNaoGastoTotal() throws Exception;

	// metodos que precisão ser não abstratos pois só estarão em algumas as classes

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
