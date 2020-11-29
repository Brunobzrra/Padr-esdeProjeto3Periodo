package view.projetos;

public class FabricaDeTelasDeCadastroSwing implements FabricaDeTelasDeCadastro{


	public TelaCadastroProjetos fabricarTelaCadastroProjetos() {
		
		return new TelaCadastroProjetos();
	}

	
	public TelaCadastroGrupos fabricarTelaCadastroGrupos() {
		
		return new TelaCadastroGrupos();
	}

	
	public TelaCadastroEditais fabricarTelaCadastroEditais() {
		
		return new TelaCadastroEditais();
	}

}
