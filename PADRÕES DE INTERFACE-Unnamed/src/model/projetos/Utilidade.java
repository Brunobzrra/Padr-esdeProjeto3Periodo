package model.projetos;

import java.util.ArrayList;

public class Utilidade {
	public static void ativar(ArrayList<ProjetoComponente> itens, ProjetoComponente integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(true);
		for (ProjetoComponente item : itens) {
			item.ativar();
		}
	}
	public static void desativar(ArrayList<ProjetoComponente> itens, ProjetoComponente integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(false);
		for (ProjetoComponente item : itens) {
			item.desativar();
		}
	}
}
