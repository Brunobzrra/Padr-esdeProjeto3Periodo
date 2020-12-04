package view.abstract_factory;

import view.projetos.TelaCadastroEditaisSwing;
import view.projetos.TelaCadastroGruposSwing;
import view.projetos.TelaCadastroProjetosSwing;

/**
 * Classe concreta para a fabricacao de uma tela de cadastro ultilizando-se da
 * API swing
 * 
 * @author bruno
 */

public class FabricaDeTelasDeCadastroSwing implements FabricaDeTelasDeCadastro {

	public InterfaceTelaCadastroProjetos fabricarTelaCadastroProjetos() {

		return new TelaCadastroProjetosSwing();
	}

	public InterfaceTelaCadastroGrupos fabricarTelaCadastroGrupos() {

		return new TelaCadastroGruposSwing();
	}

	public InterfaceTelaCadastroEditais fabricarTelaCadastroEditais() {

		return new TelaCadastroEditaisSwing();
	}

}
