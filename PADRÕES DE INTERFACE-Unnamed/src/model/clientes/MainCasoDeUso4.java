package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.EditalFachada;
import model.projetos.Edital;
import model.projetos.Participacao;
import persistencia.xml.DAOXMLMembroConta;

public class MainCasoDeUso4 {
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
		

		try {
			Edital edital1 = new Edital();
			Edital edital2 = new Edital();
			EditalFachada facade = new EditalFachada((short)1 ,agora,termino, 1f, (short)1,(short) 1, false);
			edital1.setNome("Edital 1");
			edital2.setNome("Edital 2");
			edital1.setDataInicio(agora);
			edital2.setDataInicio(agora);
			facade.adcionarEdital(edital2.getNome());
			facade.adcionarEdital(edital1.getNome());
			Edital antigoGrupo= edital2;
			edital2.setNome("Edital novo 2");
			facade.atualizarEdital(antigoGrupo.getNome(),edital2);
			facade.removerEdital(edital2.getNome());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
