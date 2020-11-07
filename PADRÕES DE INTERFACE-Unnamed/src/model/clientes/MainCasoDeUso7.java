package model.clientes;

import java.util.Date;
import java.util.Set;

import model.autenticacao.Membro;
import model.casosDeUsofachadas.GerenciadorDeAdministradorFachada;
import model.projetos.Participacao;
import persistencia.xml.DAOXMLMembroConta;

public class MainCasoDeUso7 {
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
			GerenciadorDeAdministradorFachada fachada= new GerenciadorDeAdministradorFachada(membro.getMatricula());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
