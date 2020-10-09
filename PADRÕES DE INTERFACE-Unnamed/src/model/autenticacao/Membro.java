package model.autenticacao;

import java.util.ArrayList;

import model.projetos.IntegracaoDeProjeto;
import model.projetos.Participacao;

public class Membro extends IntegracaoDeProjeto {

	private long matricula;

	private String nome;

	private boolean ativo;
	
	private String email;
	
	private ContaEmail conta;

	private boolean administrador;

	private ArrayList<IntegracaoDeProjeto> participacoes = new ArrayList<>();

	// creio que seja +- assim, não sei se a composição dessa classe é pela conta email.
	public Membro(String email, TipoProvedorAutenticacao tipo, String senhaEmail) {
		
		int pos = email.indexOf("@");
		String dominioEmail = email.substring(pos, email.length());
		if (dominioEmail.equals("@academico.ifpb.edu.br")) {
			conta = new ContaEmailIFPB();
		}else
			conta = new ContaEmailLivre();
		if (tipo.name().equals("INTERNAMENTE")) {
			conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorInterno());
		}else
			conta.setImplementacaoContaBridge(new ContaAutenticacaoProvedorEmailSMTP());
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
			integracao.setProjetoPai(this);
			participacoes.add(integracao);
	}

	public void remover(IntegracaoDeProjeto integracao) throws Exception {
		participacoes.remove(integracao);
		integracao.setProjetoPai(null);

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

	public ContaEmail getConta() {
		return conta;
	}

	public void setConta(ContaEmail conta) {
		this.conta = conta;
	}

}
