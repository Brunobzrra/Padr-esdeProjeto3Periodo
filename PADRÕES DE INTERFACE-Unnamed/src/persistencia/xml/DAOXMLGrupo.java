package persistencia.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.autenticacao.Membro;
import model.projetos.Grupo;
public class DAOXMLGrupo {

	private HashMap<Long, Grupo> persistidos;

	private final File arquivoColecao = new File("XMLGrupo.xml");

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id = 0;
	
	
	/*
	 * adiciona um novo grupo na colecao de persistidos, que é salvo posteriormente, adicionando assim
	 * um membro ao XML, que é nosso BD.
	 * @params grupo*/
	
	public boolean criar(Grupo grupo) throws Exception{
		if(grupo.getNome().length()<5 || grupo.getLinkCNPq().length()<5) {
			throw new Exception("Digite todos os parametros corretamente!");
		}
		String[] atributos = { "linkCNPq" };
		Object[] valores = { grupo.getLinkCNPq() };
		if (consultarAnd(atributos, valores).size()==0) {
			this.persistidos = this.carregarXML();
			id += 1;
			this.persistidos.put(id, grupo);
			this.salvarXML(persistidos);
			return true;
		}
		return false;
	}
	/**
	 * este metodo retorna o objeto por indentificador
	 * @param nome
	 * @return
	 */
	public Grupo recuperarPorIndentificador(String linkCNPq) {
		this.persistidos=carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			if(persistidos.get(long1).getLinkCNPq().equals(linkCNPq)){
				return persistidos.get(long1);
			}
		}
		return null;
	}
	public Grupo recuperarPorNome(String nome) {
		this.persistidos=carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			if(persistidos.get(long1).getNome().equals(nome)){
				return persistidos.get(long1);
			}
		}
		return null;
	}
	/*
	 * Metodo que ira procurar uma chave de um grupo especifico no HASHSET de persistidos, returna o indice
	 * que o membro se encontra no HASHSET
	 * @params procurado*/


	private Long procurarChave(Grupo procurado) {
		Long indice = null;
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			Grupo recuperado = persistidos.get(long1);
			if (recuperado.equals(procurado)) {
				indice = long1;
				break;
			}
		}
		return indice;
	}
	/*
	 * metodo usado para remover um grupo do HASHSET persistidos, que eh recuperado, modificado, e posterior
	 * mente salvo no BD via salvarXML()
	 * @params grupoRemover */
	public void remover(Grupo grupoRemover) {
		this.persistidos = this.carregarXML();
		Long indice = procurarChave(grupoRemover);
		if (indice != null) {
			persistidos.remove(indice);
			this.salvarXML(persistidos);
		}
	}
	/*
	 * Metodo que substitui um grupo no HASHSET de persistidos, colocando outro grupo de interesse no lugar
	 * com isso e salvando o hashset posteriormente, com isso, atualizando o valor do edital no BD*/
	public boolean atualizar(Grupo grupoSubstituivel, Grupo grupoSubistituto) throws Exception{
		if(grupoSubistituto.getNome().length()<5 || grupoSubistituto.getLinkCNPq().length()<5) {
			throw new Exception("Digite todos os parametros corretamente!");
		}
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (persistidos.get(chave).getLinkCNPq().equals(grupoSubstituivel.getLinkCNPq())) {
				persistidos.replace(chave, grupoSubistituto);
			}
		}
		this.salvarXML(persistidos);
		return true;
	}
	public ArrayList<Grupo> recuperarGruposComMembro(Membro membro) throws Exception{
		ArrayList<Grupo> grupos= new ArrayList<Grupo>();
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (persistidos.get(chave).buscarComponente(membro)) {
				grupos.add(persistidos.get(chave));
			}
		}
		return grupos;
	}
	/*
	 * metodo usado para consultar um grupo no hashset de persistidos, por meio de seus atributos. caso
	 * exista um grupo com o mesmo ou os mesmos atributos escolhidos,eh retornado um set com todos os 
	 * grupos correspondentes. sao ultilizados como paramentro de entrada um array de string onde o nome
	 * dos atributos que se deseja consulta, exatamente como estao declarados na classe do grupo sao inseridos
	 * e os seus respectivos valores sao adicionados nas repectivas posicoes em um array de object, que no cao
	 * sao os valores desses atributos.
	 * @params atributos, valores */

	public Set<Grupo> consultarAnd(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Grupo> auxiliar = new HashSet<Grupo>();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (chave != null) {
				Grupo grupoAuxiliar = persistidos.get(chave);
				boolean[] confirmacoes = new boolean[4];
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = grupoAuxiliar.getNome();
								if (j == 0) {
									if (valores[0].equals(nomeRecuperado)) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (valores[1].equals(nomeRecuperado)) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (valores[2].equals(nomeRecuperado))
										confirmacoes[2] = true;
								} else {
									if (valores[3].equals(nomeRecuperado)) {
										confirmacoes[3] = true;
									}
								}
							} else if (atributos[j].equals("dataDeCriacao")) {
								Date dataDeCriacao = grupoAuxiliar.getDataCriacao();
								if (j == 0) {
									if (dataDeCriacao.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (dataDeCriacao.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (dataDeCriacao.equals(valores[2]))
										confirmacoes[2] = true;
								} else {
									if (dataDeCriacao.equals(valores[3])) {
										confirmacoes[3] = true;
									}
								}
							} else if (atributos[j].equals("linkCNPq")) {
								String linkCNPqRecuperado = grupoAuxiliar.getLinkCNPq();
								if (j == 0) {
									if (linkCNPqRecuperado.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (linkCNPqRecuperado.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (linkCNPqRecuperado.equals(valores[2]))
										confirmacoes[2] = true;
								} else {
									if (linkCNPqRecuperado.equals(valores[3])) {
										confirmacoes[3] = true;
									}
								}
							} else if (atributos[j].equals("ativo")) {
								Boolean ativo = grupoAuxiliar.getAtivo();
								if (j == 0) {
									if (ativo.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (ativo.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (ativo.equals(valores[2])) {
										confirmacoes[2] = true;
									}
								} else {
									if (ativo.equals(valores[3])) {
										confirmacoes[3] = true;
									}
								}
							}
						}
					}
					int aux = 0;
					for (int j = 0; j < confirmacoes.length; j++) {
						if (confirmacoes[j] == true) {
							aux++;
						}
					}
					if (aux == atributos.length) {
						auxiliar.add(grupoAuxiliar);
					}
				}
			}
		}
		return auxiliar;
	}
	/*
	 * metodo usado para cosultar se existe um determinado grupo no hashset de persistidos, que eh como o 
	 * BD eh construido, isso eh feito por meio da disponibilizacao dos atribuos que vao ser consultados via 
	 * array de string, e os seus respectivos valoress via um array de object, esse metodo retorna um set com
	 * todos os grupo correspondentes a pelo menos um dos atributos consultados,
	 * @params atributos, valores*/

	public Set<Grupo> consultarOr(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Grupo> auxiliar = new HashSet<Grupo>();
		Set<Long> chaves = persistidos.keySet();
		boolean adicionar = false;
		Grupo grupoAuxiliar = null;
		for (Long chave : chaves) {
			if (chave != null) {
				grupoAuxiliar = persistidos.get(chave);
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = grupoAuxiliar.getNome();
								if (j == 0) {
									if (valores[0].equals(nomeRecuperado)) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (valores[1].equals(nomeRecuperado)) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (valores[2].equals(nomeRecuperado))
										adicionar = true;
								} else {
									if (valores[3].equals(nomeRecuperado)) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("dataDeCriacao") && adicionar == false) {
								Date dataDeCriacao = grupoAuxiliar.getDataCriacao();
								if (j == 0) {
									if (dataDeCriacao.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (dataDeCriacao.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (dataDeCriacao.equals(valores[2]))
										adicionar = true;
								} else {
									if (dataDeCriacao.equals(valores[3])) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("linkCNPq") && adicionar == false) {
								String linkCNPqRecuperado = grupoAuxiliar.getLinkCNPq();
								if (j == 0) {
									if (linkCNPqRecuperado.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (linkCNPqRecuperado.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (linkCNPqRecuperado.equals(valores[2]))
										adicionar = true;
								} else {
									if (linkCNPqRecuperado.equals(valores[3])) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("ativo") && adicionar == false) {
								Boolean ativo = grupoAuxiliar.getAtivo();
								if (j == 0) {
									if (ativo.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (ativo.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (ativo.equals(valores[2])) {
										adicionar = true;
									}
								} else {
									if (ativo.equals(valores[3])) {
										adicionar = true;
									}
								}
							}
						}
					}

				}

			}
			if (adicionar) {
				auxiliar.add(grupoAuxiliar);
			}
		}
		return auxiliar;
	}
	/*
	 * salva o hashmap de persistidos em XML
	 * @params persistidos*/

	private void salvarXML(HashMap<Long, Grupo> persistidos) {

		String xml = xstream.toXML(persistidos);

		try {
			arquivoColecao.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivoColecao);
			gravar.print(xml);
			gravar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Caso o XML já exista, apenas atualiza o mesmo, caso nao, um novo XML eh criado*/
	private HashMap<Long, Grupo> carregarXML() {

		if (arquivoColecao.exists()) {
			try {
				FileInputStream r = new FileInputStream(arquivoColecao);
				return (HashMap<Long, Grupo>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<Long, Grupo>();
	}

}
