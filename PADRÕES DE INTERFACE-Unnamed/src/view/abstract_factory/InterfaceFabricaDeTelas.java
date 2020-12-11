package view.abstract_factory;

public interface InterfaceFabricaDeTelas {

	/**
	 * metodo para fabricar tela de autenticacao
	 */
	public InterfaceTelaAutenticacao fabricarTelaAutenticacao();

	/**
	 * metodo para fabricar tela para criacao de contas
	 */
	public InterfaceTelaCriarConta fabricarTelaCriarConta();

	/**
	 * metodo para fabricar tela para configurar admins
	 */
	public InterfaceTelaConfiguracaoAdmin fabricarTelaConfiguracaoAdmin();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de projeto
	 */
	public InterfaceTelaCadastroProjetos fabricarTelaCadastroProjetos();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de grupos
	 */
	public InterfaceTelaCadastroGrupos fabricarTelaCadastroGrupos();

	/**
	 * Metodo para a fabricacao de uma tela de cadastro de editais
	 */
	public InterfaceTelaCadastroEditais fabricarTelaCadastroEditais();

	/**
	 * Metodo para a fabricacao de uma tela ponto
	 */
	public InterfaceTelaPonto fabricarTelaPonto();

	/**
	 * Metodo para a fabricacao de uma tela de justificar ponto
	 */
	public InterfaceTelaJustificarPonto fabricarTelaJustificarPonto();

	/**
	 * Metodo para a fabricacao de uma tela principal
	 */
	public InterfaceTelaPrincipal fabricarTelaPrincipal();

	/*
	 * Fabricacao de uma tela de cadastro de horario previsto
	 */

	public InterfaceTelaCadastroHorarioPrevisto fabricarTelaCadastroHorarioPrevisto();

}
