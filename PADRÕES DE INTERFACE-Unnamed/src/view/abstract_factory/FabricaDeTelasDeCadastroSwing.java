package view.projetos.abstract_factory;

import view.projetos.TelaCadastroEditais;
import view.projetos.TelaCadastroGrupos;
import view.projetos.TelaCadastroProjetos;

/**
 * Classe concreta para a fabricacao de uma tela de cadastro ultilizando-se da API swing
 * 
 * @author bruno
 * */

public class FabricaDeTelasDeCadastroSwing implements FabricaDeTelasDeCadastro{

	public InterfaceTelaCadastroProjetos fabricarTelaCadastroProjetos() {
		
		return new TelaCadastroProjetos();
	}

	
	public InterfaceTelaCadastroGrupos fabricarTelaCadastroGrupos() {
		
		return new TelaCadastroGrupos();
	}

	
	public InterfaceTelaCadastroEditais fabricarTelaCadastroEditais() {
		
		return new TelaCadastroEditais();
	}

}
