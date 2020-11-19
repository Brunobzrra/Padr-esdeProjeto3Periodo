package model.casosDeUsofachadas;

import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 7
public class CasoDeUsoSete {
	private Membro administrador;
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public CasoDeUsoSete(long matriculaDoAdministrador) throws Exception {
		administrador = daoMembro.isAdmimPelaMatricula(matriculaDoAdministrador);
	}

	public void habilitarAdministrador(long matricula) throws Exception {
		if (administrador != null) {
			if (RegistradorSessaoLogin.getInstance().isOline(administrador.getEmail())) {
				Membro membro = daoMembro.recuperarPorIndentificador(matricula);
				try {
					if (!administrador.equals(membro)) {
						Membro membroAntigo = membro;
						membro.setAdministrador(true);
						daoMembro.atualizar(membroAntigo, membro);
					} else {
						throw new Exception("Você não pode se auto-desabilitar do cargo, contate outro administrador!");
					}
				} catch (Exception e) {
					throw new Exception("Não existe membro com essa matricula!");
				}
			}
		} else {
			throw new Exception("Você não é administrador");
		}
	}
}
