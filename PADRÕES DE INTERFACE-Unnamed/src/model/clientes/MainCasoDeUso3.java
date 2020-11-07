package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.GrupoFachada;
import model.projetos.Grupo;
import model.projetos.Participacao;
import persistencia.xml.DAOXMLMembroConta;

public class MainCasoDeUso3 {

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
		Date agora = new Date(System.currentTimeMillis());
		Date termino = new Date("02/02/2021");
		Participacao participacao = new Participacao(agora, termino, (short) 200, (short) 10, (short) 30, true);

		Grupo grupo1 = new Grupo();
		Grupo grupo2 = new Grupo();
		GrupoFachada facade;
		try {
			facade = new GrupoFachada((short)1 ,agora,termino, 1f, (short)1,(short) 1, false);
			grupo1.setLinkCNPq("cnpq.com.br");
			grupo1.setNome("grupo dos mofi");
			grupo2.setLinkCNPq("br");
			grupo2.setNome("grupo ");
			grupo1.setDataCriacao(agora);
			grupo2.setDataCriacao(agora);
			facade.adcionarGrupo(grupo2.getLinkCNPq());
			facade.adcionarGrupo(grupo1.getLinkCNPq());
			Grupo antigoGrupo = grupo2;
			grupo2.setNome("Galera");
			facade.atualizarGrupo(antigoGrupo.getLinkCNPq(), grupo2);
			facade.removerGrupo(grupo2.getLinkCNPq());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

	}

}
