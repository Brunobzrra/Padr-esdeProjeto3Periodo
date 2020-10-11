package model.autenticacao;

import java.util.ArrayList;

import model.projetos.ProjetoComponente;
import model.projetos.Participacao;

public class Membro extends ProjetoComponente {
	private long matricula;

	private String nome;

	private boolean ativo;
	
	private String email;
	
	private String senha;
	
	private ContaEmail conta;

	private boolean administrador;

	private ArrayList<ProjetoComponente> participacoes = new ArrayList<>();

	public Membro() {}
	public Membro(long matricula, String nome, String email, String senha, String senhaEmail) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.setSenha(senha);
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

	public void adicionar(Participacao integracao) throws Exception {
		if (integracao instanceof Participacao) {
			integracao.setProjetoPai(this);
			participacoes.add(integracao);			
		}
	}

	public void remover(ProjetoComponente integracao) throws Exception {
		if (integracao instanceof Participacao) {
			participacoes.remove(integracao);
			integracao.setProjetoPai(null);
		}
	}

	@Override
	public float getCustoTotal() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");
	}

	@Override
	public void mover(ProjetoComponente integracao) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");

	}

	@Override
	public float getCapitalReaiNaoGastoTotal() throws Exception {
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
	
	public boolean equals(Membro m) {
		long matricula = m.getMatricula();
		if (this.matricula == matricula) {
			return true;
		}
		return false;
	}

}
