package model.projetos;

import model.autenticacao.Membro;

public abstract class ProjetoComponente {

	private boolean ativo = true;

	private ProjetoComponente projetoPai;

	public ProjetoComponente getProjetoPai() {
		return projetoPai;
	}

	public void setProjetoPai(ProjetoComponente projetoPai) {
		this.projetoPai = projetoPai;
	}

	public void remover(ProjetoComponente integracao) throws Exception{
		throw new Exception("Este objeto não se move!");
	}
	
	// metodos que precisão ser abstratatos pois estarão em todas as classes

	public abstract void ativar();

	public abstract void desativar();

	public abstract float getCustoTotal() throws Exception;

	public abstract float getCusteioReaisNaoGastoTotal() throws Exception;

	public void adicionar(Membro integracao) throws Exception {
		throw new Exception("Membro não pode ser adcionado aqui!");
	}

	public void adicionar(Projeto integracao) throws Exception {
		throw new Exception("Projeto não pode ser adcionado aqui!");
	}

	public void adicionar(Participacao integracao) throws Exception {
		throw new Exception("Participação não pode ser adcionado aqui!");
	}

	public void adicionar(Edital integracao) throws Exception {
		throw new Exception("Edital não pode ser adcionado aqui!");
	}

	public void adicionar(Grupo integracao) throws Exception {
		throw new Exception("Grupo não pode ser adcionado aqui!");
	}


	public abstract void mover(ProjetoComponente integracao) throws Exception;

	public abstract float getCapitalReaiNaoGastoTotal() throws Exception;

	// metodos que precisão ser não abstratos pois só estarão em algumas as classes

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
