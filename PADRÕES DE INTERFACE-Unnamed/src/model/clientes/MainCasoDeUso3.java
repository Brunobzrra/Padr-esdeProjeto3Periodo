package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.GrupoFachada;
import model.projetos.Grupo;
import model.projetos.Participacao;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso3 {

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

		Grupo grupo1 = new Grupo();
		Grupo grupo2 = new Grupo();
		GrupoFachada facade;
		try {
			facade = new GrupoFachada(membro, participacao);
			grupo1.setLinkCNPq("cnpq.com.br");
			grupo1.setNome("grupo dos mofi");
			grupo2.setLinkCNPq("br");
			grupo2.setNome("grupo ");
			grupo1.setDataCriacao(agora);
			grupo2.setDataCriacao(agora);
			facade.adcionarGrupo(grupo2);
			facade.adcionarGrupo(grupo1);
			Grupo antigoGrupo = grupo2;
			grupo2.setNome("Galera");
			facade.atualizarGrupo(antigoGrupo, grupo2);
			facade.removerGrupo(grupo2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

	}

}
