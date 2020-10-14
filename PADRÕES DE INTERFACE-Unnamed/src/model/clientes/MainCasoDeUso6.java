package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.GerenciadorMembroFachada;
import model.projetos.Participacao;
import model.projetos.Projeto;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso6 {
	public static void main(String[] args) {
		DAOXMLMembroConta dao = new DAOXMLMembroConta();
		Object[] valores = { "bruno", "fananitadz@gmail.com" };
		String[] atributos = { "nome", "email" };
		Set<Membro> recuperado = dao.consultarAnd(atributos, valores);
		Object[] recuperados = recuperado.toArray();
		Membro membro=null;
		try {
			membro = (Membro) recuperados[0];			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não foi possivel encontrar membro!");
		}
		Membro auxiliar = membro;
		Date agora = new Date(System.currentTimeMillis());
		Date termino = new Date("02/02/2021");
		Participacao participacao = new Participacao(agora, termino, (short) 200, (short) 10, (short) 30, true);
		Projeto p = new Projeto("projeto", Float.parseFloat("300"), Float.parseFloat("100"), Float.parseFloat("10"),
				Float.parseFloat("100"));
			
		try {
			membro.adicionar(participacao);
			dao.atualizar(membro, auxiliar);
			
			GerenciadorMembroFachada gerenci = new GerenciadorMembroFachada(membro, participacao, p);
			gerenci.adicionar(membro, agora, termino, Float.parseFloat("10"), Short.parseShort("5"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
