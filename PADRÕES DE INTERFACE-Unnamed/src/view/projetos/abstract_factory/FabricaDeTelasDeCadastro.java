package view.projetos.abstract_factory;

import view.projetos.TelaCadastroEditais;
import view.projetos.TelaCadastroGrupos;
import view.projetos.TelaCadastroProjetos;

public interface FabricaDeTelasDeCadastro {

	public abstract TelaCadastroProjetos fabricarTelaCadastroProjetos();

	public abstract TelaCadastroGrupos fabricarTelaCadastroGrupos();

	public abstract TelaCadastroEditais fabricarTelaCadastroEditais();

}
