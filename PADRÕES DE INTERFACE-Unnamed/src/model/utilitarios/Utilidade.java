package model.utilitarios;

import java.util.ArrayList;

import model.projetos.ProjetoComponente;

/**
 * Aqui reside operações em funcões que se replicavam em classes composites 
 * @author Antônio Amorim
 *
 */
public class Utilidade {
	/**
	 * ativa todos os objetos daquela coleção
	 * @param itens
	 * @param integracaoDeProjeto
	 */
	public static void ativar(ArrayList<ProjetoComponente> itens, ProjetoComponente integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(true);
		for (ProjetoComponente item : itens) {
			item.ativar();
		}
	}
	/**
	 * desativa todos os objetos daquela coleção
	 * @param itens
	 * @param integracaoDeProjeto
	 */
	public static void desativar(ArrayList<ProjetoComponente> itens, ProjetoComponente integracaoDeProjeto) {
		integracaoDeProjeto.setAtivo(false);
		for (ProjetoComponente item : itens) {
			item.desativar();
		}
	}
}
