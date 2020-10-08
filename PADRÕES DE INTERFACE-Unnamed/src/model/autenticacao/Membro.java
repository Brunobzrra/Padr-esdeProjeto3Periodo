package model.autenticacao;

import java.util.ArrayList;

import model.projetos.IntegracaoDeProjeto;
import model.projetos.Participacao;

public class Membro extends IntegracaoDeProjeto {

	private long matricula;

	private String nome;

	private boolean ativo;

	private String email;

	private boolean administrador;

	private ArrayList<IntegracaoDeProjeto> participacoes = new ArrayList<>();

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

	public void adicionar(IntegracaoDeProjeto integracao) throws Exception {
		if (integracao instanceof Participacao) {
			integracao.setProjetoPai(this);
			participacoes.add(integracao);
		}
		throw new Exception("Membro não pode ter esse tipo de objeto!");
	}

	public void remover(IntegracaoDeProjeto integracao) throws Exception {
		integracao.setProjetoPai(null);
		participacoes.remove(integracao);

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
	public void mover(IntegracaoDeProjeto integracao) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");

	}

	@Override
	public float getCapitalReaiNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Membro não este recurso!");
	}

}
