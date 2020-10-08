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

	// metodos que precis�o ser abstratatos pois estar�o em todas as classes

	public abstract void ativar();

	public abstract void desativar();

	public  float getCustoTotal() {
		return 0;
		
	}

	public  float getCusteioReaisNaoGastoTotal() {
		return 0;
		
	}

	// metodos que precis�o ser n�o abstratos pois s� estar�o em algumas as classes

	public void adicionar(IntegracaoDeProjeto integracao) {

	}

	public void remover(IntegracaoDeProjeto integracao) {

	}

	public void mover(IntegracaoDeProjeto integracao) {

	}
	
	public float getCapitalReaiNaoGastoTotal() {
		return 0;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
