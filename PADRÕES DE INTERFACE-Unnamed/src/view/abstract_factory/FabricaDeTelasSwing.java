package view.abstract_factory;

import view.TelaPonto;
import view.autenticacao.TelaAutenticacaoSwing;
import view.autenticacao.TelaConfiguracaoAdminSwing;
import view.autenticacao.TelaCriarContaSwing;
import view.projetos.TelaCadastroEditaisSwing;
import view.projetos.TelaCadastroGruposSwing;
import view.projetos.TelaCadastroProjetosSwing;
import view.projetos.TelaJustificativaPontoSwing;

/**
 * Fabrica de telas concreta para implementacao em swing usada
 * @author bruno
 * */

public class FabricaDeTelasSwing implements InterfaceFabricaDeTelas{
	private static FabricaDeTelasSwing fabricaTelasSwing = new FabricaDeTelasSwing();
	
	private FabricaDeTelasSwing() {
		
	}
	
	public synchronized static InterfaceFabricaDeTelas getFabrica() {
		return fabricaTelasSwing;
	}
	public InterfaceTelaAutenticacao fabricarTelaAutenticacao() {
	
		return new TelaAutenticacaoSwing();
	}

	public InterfaceTelaCriarConta fabricarTelaCriarConta() {
		
		return new TelaCriarContaSwing();
	}

	public InterfaceTelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin() {
		
		return new TelaConfiguracaoAdminSwing();
	}

	public InterfaceTelaCadastroProjetos fabricarTelaCadastroProjetos() {
		
		return new TelaCadastroProjetosSwing();
	}

	public InterfaceTelaCadastroGrupos fabricarTelaCadastroGrupos() {
		
		return new TelaCadastroGruposSwing();
	}

	public InterfaceTelaCadastroEditais fabricarTelaCadastroEditais() {
		
		return new TelaCadastroEditaisSwing();
	}
	public InterfaceTelaPonto fabricarTelaPonto() {
		
		return new TelaPonto();
	}

	
	public InterfaceTelaJustificarPonto fabricarTelaJustificarPonto() {

		return new TelaJustificativaPontoSwing();
	}

}
