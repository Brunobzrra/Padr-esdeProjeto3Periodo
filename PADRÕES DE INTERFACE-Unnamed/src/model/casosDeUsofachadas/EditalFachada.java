package model.casosDeUsofachadas;

import model.autenticacao.Membro;
import model.projetos.Grupo;
import model.projetos.Projeto;
import persistenia.xml.DAOXMLGrupo;

//caso de uso 4
public class EditalFachada extends ProjetoFachada {

	private Membro menbro;
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();
	private Grupo grupo;

	public EditalFachada(Projeto projeto) {
		super(projeto);
		// TODO Auto-generated constructor stub
	}

}
