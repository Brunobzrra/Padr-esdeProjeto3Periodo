package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.ProjetoFachada;
import model.projetos.Participacao;
import model.projetos.Projeto;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso5 {
	public static void main(String[] args) {
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		Object[] valores = { "brunin", "fan@gmail.com" };
		String[] atributos = { "nome", "email" };
		Set<Membro> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro membro = (Membro) recuperados[0];
		Date agora = new Date(System.currentTimeMillis());
		Date termino = new Date("02/02/2021");
		Participacao participacao = new Participacao(agora, termino, (short) 200, (short) 10, (short) 30, true);
		try {
			ProjetoFachada fachada= new ProjetoFachada(membro, participacao);
			Projeto projeto=fachada.criarProjeto("Projet", 200, 100, 30, 20);
			fachada.atualizarDado(projeto, "nome", "Projeto", "Projet");
			fachada.remover(projeto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
