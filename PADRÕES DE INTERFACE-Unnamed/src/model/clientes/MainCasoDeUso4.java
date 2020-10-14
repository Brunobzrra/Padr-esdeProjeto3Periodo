package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.projetos.Edital;
import model.projetos.Participacao;
import model.utilitarios.EditalFachada;
import persistenia.xml.DAOXMLMembroConta;

public class MainCasoDeUso4 {
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
			Edital edital1 = new Edital();
			Edital edital2 = new Edital();
			EditalFachada facade = new EditalFachada(membro, participacao);
			edital1.setNome("Edital 1");
			edital2.setNome("Edital 2");
			edital1.setDataInicio(agora);
			edital2.setDataInicio(agora);
			facade.adcionarEdital(edital2);
			facade.adcionarEdital(edital1);
			Edital antigoGrupo= edital2;
			edital2.setNome("Edital novo 2");
			facade.atualizarEdital(antigoGrupo,edital2);
			facade.removerEdital(edital2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
