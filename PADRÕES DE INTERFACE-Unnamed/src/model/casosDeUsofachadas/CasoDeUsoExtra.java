package model.casosDeUsofachadas;

import java.util.ArrayList;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistencia.xml.DAOXMLEdital;
import persistencia.xml.DAOXMLGrupo;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;
//Facahda que serve para recupera objetos persistidos,
//porem que não foi pedido em cso ede uso anteriores 
//mas se faz necessario nesta terceora etapa, para não ter
//que modificar fachadas ja criadas em etapas anteriores
public class CasoDeUsoExtra {
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();
	private DAOXMLEdital daoEdital = new DAOXMLEdital();
	private DAOXMLProjetoParticipacao daoProjeto= new DAOXMLProjetoParticipacao();

	//Servem para recuperar um unico objeto persistido
	public Object[] recuperarMembro(long matricula) {
		Membro membro = daoMembro.recuperarPorIndentificador(matricula);
		Object[] dados = { membro.getNome(), membro.getEmail(), membro.getSenha() };
		return dados;
	}

	public Object[] recuperarGrupo(String linkCNPq) {
		Grupo grupo = daoGrupo.recuperarPorIndentificador(linkCNPq);
		Object[] dados = { grupo.getNome(), grupo.getLinkCNPq()};
		return dados;
	}
	
	public Object[] recuperarEdital(String nome) {
		Edital edital = daoEdital.recuperarPorIndentificador(nome);
		Object[] dados = { edital.getNome()};
		return dados;
	}
	public Object[] recuperarPtojeto(String nome) {
		Projeto projeto = daoProjeto.recuperarPorIndentificador(nome); 
		Object[] dados = { projeto.getAporteCusteioReais(),projeto.getAporteCapitalReais()};
		return dados;
	}
	//Servem para recuperar objetos persistindos que pertecem a um membro logado
	public ProjetoComponente recuperarProjetoComponente(String nome)throws Exception {
		ProjetoComponente componente= daoProjeto.recuperarPorIndentificador(nome);
		if(componente!=null) {
			return componente;
		}
		componente=daoGrupo.recuperarPorNome(nome);
		if(componente!=null) {
			return componente;
		}
		componente=daoEdital.recuperarPorIndentificador(nome);
		if(componente==null) {
			throw new Exception("Componente não encontrado");
		}
		return componente;
	}
	public ArrayList<String> recuperarProjetos() throws Exception {
		Membro membro = RegistradorSessaoLogin.getInstance().getLogado();
		ArrayList<String> projetos= new ArrayList<String>();
		for (Projeto projeto : daoProjeto.recuperarProjetosComMembro(membro)) {
			projetos.add(projeto.getNome());
		}
		return projetos;
	}

	public ArrayList<String> recuperarGrupos()  throws Exception {
		Membro membro = RegistradorSessaoLogin.getInstance().getLogado();
		ArrayList<String> grupos= new ArrayList<String>();
		for (Grupo grupo : daoGrupo.recuperarGruposComMembro(membro)) {
			grupos.add(grupo.getNome());
		}
		return grupos;
	}
	
	public ArrayList<String> recuperarEditais()  throws Exception {
		Membro membro = RegistradorSessaoLogin.getInstance().getLogado();
		ArrayList<String> editais= new ArrayList<String>();
		for (Edital edital : daoEdital.recuperarEditaisComMembro(membro)) {
			editais.add(edital.getNome());
		}
		return editais;
	}
}
