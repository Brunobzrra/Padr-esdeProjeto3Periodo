package view.projetos;


import view.controller.ControllerCadastroGrupos;

public class TelaCadastroGrupos {
	
	private ControllerCadastroGrupos controller = new ControllerCadastroGrupos();
	
	public void adcionarGrupo(String nome, String linkCNPq, long matricula) throws Exception {
		controller.adcionarGrupo(nome, linkCNPq, matricula);

	}

	public void removerGrupo(long matricula, String linkCNPq) throws Exception {
		controller.removerGrupo(matricula, linkCNPq);

	}

	public void atualizarrGrupo(long matricula, String linkCNPq, String nomeNovo, String linkCNPqNovo) throws Exception {
		controller.atualizarrGrupo(matricula, linkCNPq, nomeNovo, linkCNPqNovo);

	}
	
	public void mostrarGruposDoUsuarioLogado() {
		//o que fazer aqui?
	}
}
