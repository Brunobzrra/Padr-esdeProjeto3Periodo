package model.autenticacao;

import model.projetos.IntegracaoDeProjeto;

public class Membro extends IntegracaoDeProjeto{

	private long matricula;

	private String nome;

	private boolean ativo;

	private String email;

	private boolean administrador;

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

	@Override
	public float getCustoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
