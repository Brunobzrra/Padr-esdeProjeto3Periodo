package model.autenticacao;

import java.io.Serializable;
import java.util.ArrayList;

import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import ponto.model.projetos.PontoTrabalhado;
import model.projetos.Participacao;

public class Membro extends ProjetoComponente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long matricula;

	private String nome;

	private boolean ativo = super.getAtivo();

	private String email;

	private String senha;

	private ContaEmail conta;

	private boolean administrador=false;
	/**
	 * colecao de participações
	 */
	private ArrayList<ProjetoComponente> participacoes = new ArrayList<>();

	public ArrayList<ProjetoComponente> getParticipacoes() {
		return participacoes;
	}

	public Membro(long matricula, String nome, String email, String senha) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.setSenha(senha);
		setTipo(TipoProjetoComponente.MEMBRO);
		int indice = email.indexOf("@");
		String dominio = email.substring(indice, email.length());
		if (dominio.equals("@academico.ifpb.edu.br")) {
			conta = new ContaEmailIFPB();
		} else {
			conta = new ContaEmailLivre();
		}
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	@Override
	public void ativar() {
		super.setAtivo(true);

	}

	@Override
	public void desativar() {
		super.setAtivo(false);

	}

	/**
	 * adciona somente participação
	 */
	public void adicionar(ProjetoComponente item) throws Exception {
		if (item.getTipo() == TipoProjetoComponente.PARTICIPACAO) {
			for (ProjetoComponente projetoComponente : participacoes) {
				if (item.equals(projetoComponente)) {
					throw new Exception("Este item ja existe aqui!");
				}
			}
			Participacao participa = (Participacao) item;
			participa.setMembro(this);
			;
			participacoes.add(item);
		}
	}

	public void remover(ProjetoComponente integracao) throws Exception {
		if (integracao.getTipo() == TipoProjetoComponente.PARTICIPACAO) {
			Participacao participa = (Participacao) integracao;
			participacoes.remove(participa);
			participa.setMembro(null);
		}
	}

	@Override
	/**
	 * Este metodo não sao implemntados em membro
	 */
	public float getCustoTotal() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");
	}

	@Override
	/**
	 * Este metodo não sao implemntados em membro
	 */
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");
	}

	@Override
	/**
	 * Este metodo não sao implemntados em membro
	 */
	public void mover(ProjetoComponente integracao) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");

	}

	@Override
	/**
	 * Este metodo não sao implemntados em membro
	 */
	public float getCapitalReaisNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");
	}

	public ContaEmail getConta() {
		return conta;
	}

	public void setConta(ContaEmail conta) {
		this.conta = conta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean buscarComponente(ProjetoComponente comonente) throws Exception {
		for (ProjetoComponente projetoComponente : participacoes) {
			if (projetoComponente == comonente) {
				return true;
			}
		}
		return false;
	}

	public boolean equals(Membro m) {
		long matricula = m.getMatricula();
		if (this.matricula == matricula) {
			return true;
		}
		return false;
	}

	public float getGastoTotal() {
		float aux = 0;
		for (ProjetoComponente projetoComponente : participacoes) {
			aux += projetoComponente.getGastoTotal();
		}
		return aux + getCusteioReaisGastoTotal() + getCapitalReaisGastoTotal();
	}

	public float getCusteioReaisGastoTotal() {
		float aux = 0;
		for (ProjetoComponente projetoComponente : participacoes) {
			aux += projetoComponente.getCusteioReaisGastoTotal();
		}
		return aux;
	}

	public float getCapitalReaisGastoTotal() {
		float aux = 0;
		for (ProjetoComponente projetoComponente : participacoes) {
			aux += projetoComponente.getCapitalReaisGastoTotal();
		}
		return aux;
	}

	public ArrayList<PontoTrabalhado> getPontosValidos() {
		ArrayList<PontoTrabalhado> pontos = new ArrayList<PontoTrabalhado>();
		for (ProjetoComponente partcipa : getParticipacoes()) {
			Participacao participacao = (Participacao) partcipa;
			for (PontoTrabalhado ponto : participacao.getPontos()) {
				if (ponto.isJustificativaAceita()) {
					pontos.add(ponto);
				}
			}
		}
		return pontos;
	}

	public void setParticipacoes(ArrayList<ProjetoComponente> participacoes) {
		this.participacoes = participacoes;
	}

}
