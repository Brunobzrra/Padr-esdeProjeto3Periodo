package model.casosDeUsofachadas;

import java.util.Set;

import model.autenticacao.Membro;
import persistenia.xml.DAOXMLMembroConta;
//caso de uso 7
public class GerenciadorDeMembrosFachada {
	private Membro administrador;
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public GerenciadorDeMembrosFachada(Membro administrador) {
		this.administrador = administrador;
	}

	public Membro habilitarAdministrador(long matricula) {
		if (administrador.isAdministrador()) {
			String[] atributos = { "matricula" };
			Object[] valores = { matricula };
			Set<Membro> membro = daoMembro.consultarAnd(atributos, valores);
			try {
				for (Membro membroModificado : membro) {
					if (!administrador.equals(membroModificado)) {
						Membro membroAntigo = membroModificado;
						membroModificado.setAdministrador(true);
						daoMembro.atualizar(membroAntigo, membroModificado);
						return membroModificado;
					} else {
						System.out.println("Voce não pode se modificar!");
					}
				}
			} catch (Exception e) {
				System.out.println("Não existe membro com essa matricula!");
			}
		}
		return null;
	}
}
