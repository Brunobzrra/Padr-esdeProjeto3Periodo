package model.casosDeUsofachadas;

import java.util.Set;

import model.autenticacao.Membro;
import persistencia.xml.DAOXMLMembroConta;
//caso de uso 7
public class CasoDeUsoSeisSete {
	private Membro administrador;
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public CasoDeUsoSeisSete(long matriculaDoAdministrador) throws Exception {
		this.administrador = AutenticadorDePersistencia.verificarMembro(matriculaDoAdministrador);
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
