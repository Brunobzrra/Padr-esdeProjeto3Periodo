package model.projetos;

import java.util.ArrayList;
//essa classe serve para tirar o codigo que não varia mas que se replica, ai coloca tudo ai, da uma olhada em Projeto
//faz isso nas outras classes do composite
public class EscolheUmNomeMelhor {
	public static void ativar(ArrayList<IntegracaoDeProjeto> itens, IntegracaoDeProjeto integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(true);
		for (IntegracaoDeProjeto item : itens) {
			item.ativar();
		}
	}
	public static void destivar(ArrayList<IntegracaoDeProjeto> itens, IntegracaoDeProjeto integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(false);
		for (IntegracaoDeProjeto item : itens) {
			item.desativar();
		}
	}
	//implementa ai pfv, que tb n entendi n kkkk

}
