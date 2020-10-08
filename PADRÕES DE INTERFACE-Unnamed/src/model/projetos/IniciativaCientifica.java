package model.projetos;

import java.util.ArrayList;

public class IniciativaCientifica {
	public static void ativar(ArrayList<IntegracaoDeProjeto> itens, IntegracaoDeProjeto integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(true);
		for (IntegracaoDeProjeto item : itens) {
			item.ativar();
		}
	}
	public static void desativar(ArrayList<IntegracaoDeProjeto> itens, IntegracaoDeProjeto integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(false);
		for (IntegracaoDeProjeto item : itens) {
			item.desativar();
		}
	}
}
