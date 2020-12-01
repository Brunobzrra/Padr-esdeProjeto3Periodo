package model.casosDeUsofachadas;

import java.util.logging.Level;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 7
public class CasoDeUsoSete {
	private Membro administrador;
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void habilitarAdministrador(long matriculaAdministrador,long novoAdmin) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Habilitando novo administrador");
		administrador=daoMembro.recuperarPorIndentificador(matriculaAdministrador);
		if (administrador != null) {
			LoggerProjeto.getInstance().getLogger().info("O solicitante eh adm");
			;
			if (RegistradorSessaoLogin.getInstance().isOline(administrador.getEmail())) {
				LoggerProjeto.getInstance().getLogger().info("O solicitante esta on");
				;
				Membro membro = daoMembro.recuperarPorIndentificador(novoAdmin);
				try {
					LoggerProjeto.getInstance().getLogger().info("Verificando se ele nao eh adm");
					;
					if (!administrador.equals(membro)) {
						Membro membroAntigo = membro;
						if(membroAntigo.isAdministrador()) {
							throw new Exception("Você não pode habilitar novamente este administrador!");
						}
						membro.setAdministrador(true);
						daoMembro.atualizar(membroAntigo, membro);
						LoggerProjeto.getInstance().getLogger().warning("Adicionado aos adms com sucesso");
						;
					} else {
						LoggerProjeto.getInstance().getLogger().severe("Adm nao pode se auto desabilitar");
						;
						throw new Exception("Você não pode se auto-desabilitar do cargo, contate outro administrador!");
					}
				} catch (Exception e) {
					LoggerProjeto.getInstance().getLogger().severe("Futuro Adm nao encontrado no bd");
					;
					throw new Exception("Não existe membro com essa matricula!");
				}
			}
		} else {
			LoggerProjeto.getInstance().getLogger().severe("Apenas adms podem dar cargos a outros adms");
			;
			throw new Exception("Você não é administrador");
		}
	}
}
