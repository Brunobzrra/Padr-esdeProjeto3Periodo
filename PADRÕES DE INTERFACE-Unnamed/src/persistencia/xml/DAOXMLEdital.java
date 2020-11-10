package persistencia.xml;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;

public class DAOXMLEdital {

	private HashMap<Long, Edital> persistidos;

	private final File arquivoColecao = new File("XMLEdital.xml");

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id = 0;
	
	/*
	 * adiciona um novo edital na colecao de persistidos, que é salvo posteriormente, adicionando assim
	 * um edital ao XML, que é nosso BD.
	 * @params edital*/
	
	public boolean criar(String nome, Date dataInicio, Date dataTermino)throws Exception {
		if(nome.length()<5 || dataInicio==null || dataTermino==null) {
			throw new Exception("Digite todos os parametros corretamente!");
		}
		String[] atributos = { "nome" };
		Object[] valores = { nome };
		Edital edital= new Edital(nome, dataInicio, dataTermino);
		edital.setNome(nome);
		edital.ativar();
		if (consultarAnd(atributos, valores).size()==0) {
			this.persistidos = this.carregarXML();
			id += 1;
			this.persistidos.put(id, edital);
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
	public Edital recuperarPorIndentificador(String nome) {
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			if(persistidos.get(long1).getNome().equals(nome)){
				return persistidos.get(long1);
			}
		}
		return null;
	}
	/*
	 * Metodo que ira procurar uma chave de um edital especifico no HASHSET de persistidos, returna o indice
	 * que o membro se encontra no HASHSET
	 * @params procurado*/

	private Long procurarChave(Edital procurado) {
		Long indice = null;
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			Edital recuperado = persistidos.get(long1);
			if (recuperado.equals(procurado)) {
				indice = long1;
				break;
			}
		}
		return indice;
	}
	/*
	 * metodo usado para remover um edital do HASHSET persistidos, que eh recuperado, modificado, e posterior
	 * mente salvo no BD via salvarXML()
	 * @params editalRemover */

	public void remover(Edital editalRemover) {
		this.persistidos = this.carregarXML();
		Long indice = procurarChave(editalRemover);
		if (indice != null) {
			persistidos.remove(indice);
			this.salvarXML(persistidos);
		}
	}

	/*
	 * Metodo que substitui um edital no HASHSET de persistidos, colocando outro edital de interesse no lugar
	 * com isso e salvando o hashset posteriormente, com isso, atualizando o valor do edital no BD*/
	public boolean atualizar(Edital editalSubstituivel, Edital editalSubistituto) throws Exception {
		if(editalSubistituto.getNome().length()<5 || editalSubistituto.getDataInicio()==null || editalSubistituto.getDataTermino()==null) {
			throw new Exception("Digite todos os parametros corretamente!");
		}
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (persistidos.get(chave).equals(editalSubstituivel)) {
				persistidos.replace(chave, editalSubistituto);
			}
		}
		this.salvarXML(persistidos);
		return true;
	}

	/*
	 * metodo usado para consultar um edital no hashset de persistidos, por meio de seus atributos. caso
	 * exista um edital com o mesmo ou os mesmos atributos escolhidos,eh retornado um set com todos os 
	 * edital correspondentes. sao ultilizados como paramentro de entrada um array de string onde o nome
	 * dos atributos que se deseja consulta, exatamente como estao declarados na classe do edital sao inseridos
	 * e os seus respectivos valores sao adicionados nas repectivas posicoes em um array de object, que no cao
	 * sao os valores desses atributos.
	 * @params atributos, valores */

	public Set<Edital> consultarAnd(String[] atributos, Object[] valores) {

		this.persistidos = this.carregarXML();
		Set<Edital> auxiliar = new HashSet<Edital>();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (chave != null) {
				Edital editalAuxiliar = persistidos.get(chave);
				boolean[] confirmacoes = new boolean[4];
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("*")) {
								auxiliar.addAll(persistidos.values());
								return auxiliar;
							}
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = editalAuxiliar.getNome();
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
									if (valores[3].equals(nomeRecuperado))
										confirmacoes[3] = true;
								}
							} else if (atributos[j].equals("dataDeInicio")) {
								Date dataDeInicioRecuperada = editalAuxiliar.getDataInicio();
								if (j == 0) {
									if (dataDeInicioRecuperada.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (dataDeInicioRecuperada.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (dataDeInicioRecuperada.equals(valores[2]))
										confirmacoes[2] = true;
								} else {
									if (dataDeInicioRecuperada.equals(valores[3])) {
										confirmacoes[3] = true;
									}
								}
							} else if (atributos[j].equals("dataTermino")) {
								Date dataTerminoRecuperada = editalAuxiliar.getDataTermino();
								if (j == 0) {
									if (dataTerminoRecuperada.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (dataTerminoRecuperada.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (dataTerminoRecuperada.equals(valores[2]))
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (dataTerminoRecuperada.equals(valores[3])) {
										confirmacoes[3] = true;
									}
								}
							} else if (atributos[j].equals("ativo")) {
								Boolean ativo = editalAuxiliar.getAtivo();
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

				}
				int aux = 0;
				for (int j = 0; j < confirmacoes.length; j++) {
					if (confirmacoes[j] == true) {
						aux++;
					}
				}
				if (aux == atributos.length) {
					auxiliar.add(editalAuxiliar);
				}
			}
		}
		return auxiliar;
	}
	/*
	 * metodo usado para cosultar se existe um determinado edital no hashset de persistidos, que eh como o 
	 * BD eh construido, isso eh feito por meio da disponibilizacao dos atribuos que vao ser consultados via 
	 * array de string, e os seus respectivos valoress via um array de object, esse metodo retorna um set com
	 * todos os editais correspondentes a pelo menos um dos atributos consultados,
	 * @params atributos, valores*/
	public Set<Edital> consultarOr(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Edital> auxiliar = new HashSet<Edital>();
		Set<Long> chaves = persistidos.keySet();
		Edital editalAuxiliar = null;
		boolean adicionar = false;
		for (Long chave : chaves) {
			if (chave != null) {
				editalAuxiliar = persistidos.get(chave);
				if (atributos != null) {
					for (int i = 0; i < atributos.length; i++) {
						adicionar = false;
						if (atributos[i].equals("nome")) {
							String nomeRecuperado = editalAuxiliar.getNome();
							if (i == 0) {
								if (valores[0].equals(nomeRecuperado)) {
									adicionar = true;
								}
							} else if (i == 1) {
								if (valores[1].equals(nomeRecuperado)) {
									adicionar = true;
								}
							} else if (i == 2) {
								if (valores[2].equals(nomeRecuperado)) {
									adicionar = true;
								}
							} else {
								if (valores[3].equals(nomeRecuperado)) {
									adicionar = true;
								}
							}
						} else if (atributos[i].equals("dataDeInicio") && adicionar == false) {
							Date dataDeInicioRecuperada = editalAuxiliar.getDataInicio();
							if (i == 0) {
								if (dataDeInicioRecuperada.equals(valores[0])) {
									adicionar = true;
								}
							} else if (i == 1) {
								if (dataDeInicioRecuperada.equals(valores[1]))
									adicionar = true;
							} else if (i == 2) {
								if (valores[2].equals(dataDeInicioRecuperada)) {
									adicionar = true;
								}
							} else {
								if (valores[3].equals(dataDeInicioRecuperada)) {
									adicionar = true;
								}
							}
						} else if (atributos[i].equals("dataTermino") && adicionar == false) {
							Date dataTerminoRecuperada = editalAuxiliar.getDataTermino();
							if (i == 0) {
								if (dataTerminoRecuperada.equals(valores[0])) {
									adicionar = true;
								}
							} else if (i == 1) {
								if (dataTerminoRecuperada.equals(valores[1])) {
									adicionar = true;
								}
							} else if (i == 2) {
								if (valores[2].equals(dataTerminoRecuperada)) {
									adicionar = true;
								}
							} else {
								if (valores[3].equals(dataTerminoRecuperada)) {
									adicionar = true;
								}
							}
						} else if (atributos[i].equals("ativo") && adicionar == false) {
							Boolean ativo = editalAuxiliar.getAtivo();
							if (i == 0) {
								if (ativo.equals(valores[0])) {
									adicionar = true;
								}
							} else if (i == 1) {
								if (ativo.equals(valores[1])) {
									adicionar = true;
								}
							} else if (i == 2) {
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
			if (adicionar) {
				auxiliar.add(editalAuxiliar);
			}
		}
		return auxiliar;
	}
	/*
	 * salva o hashmap de persistidos em XML
	 * @params persistidos*/
	private void salvarXML(HashMap<Long, Edital> persistidos) {
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
	 * remove todos os valores do hashmap de persistidos e salva no BD, que no caso, ira ficar vazio*/

	public void limpar() {
		this.persistidos = this.carregarXML();
		persistidos.clear();
		this.salvarXML(persistidos);
	}
	/*
	 * Caso o XML já exista, apenas atualiza o mesmo, caso nao, um novo XML eh criado*/
	private HashMap<Long, Edital> carregarXML() {
		if (arquivoColecao.exists()) {
			try {
				FileInputStream r = new FileInputStream(arquivoColecao);
				return (HashMap<Long, Edital>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<Long, Edital>();
	}

}
