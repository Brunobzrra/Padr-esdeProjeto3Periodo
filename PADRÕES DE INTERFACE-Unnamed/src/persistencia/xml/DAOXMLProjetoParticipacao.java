package persistencia.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.autenticacao.Membro;
import model.projetos.Projeto;

public class DAOXMLProjetoParticipacao {

	private HashMap<Long, Projeto> persistidos;

	private final File arquivoColecao = new File("XMLProjetoParticipacao.xml");

	private long id = 0;

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));

	/*
	 * adiciona um novo projeto na colecao de persistidos, que é salvo
	 * posteriormente, adicionando assim um projeto ao XML, que é nosso BD.
	 * 
	 * @params edital
	 */

	public boolean criar(Projeto projeto) throws Exception {
		if (projeto.getNome().length() < 5 || projeto.getAporteCapitalReais() == 0
				|| projeto.getAporteCusteioReais() == 0 || projeto.getGastoExecutadoCapitalReais() == 0
				|| projeto.getGastoExecutadoCusteioReais() == 0) {
			throw new Exception("Digite todos os parametros corretamente!");
		}
		String[] atributos = { "nome" };
		Object[] valores = { projeto.getNome() };
		if (consultarAnd(atributos, valores).size() == 0) {
			this.persistidos = this.carregarXML();
			id =persistidos.size() + 1;
			this.persistidos.put(id, projeto);
			this.salvarXML(persistidos);
			return true;
		}
		return false;
	}

	/**
	 * este metodo retorna o objeto por indentificador
	 * 
	 * @param nome
	 * @return
	 */
	public Projeto recuperarPorIndentificador(String nome) {
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			if (persistidos.get(long1).getNome().equals(nome)) {
				return persistidos.get(long1);
			}
		}
		return null;
	}

	/*
	 * Metodo que ira procurar uma chave de um projeto especifico no HASHSET de
	 * persistidos, returna o indice que o projeto se encontra no HASHSET
	 * 
	 * @params procurado
	 */
	private Long procurarChave(Projeto procurado) {
		Long indice = null;
		Set<Long> chaves = persistidos.keySet();
		for (Long long1 : chaves) {
			Projeto recuperado = persistidos.get(long1);
			if (recuperado.equals(procurado)) {
				indice = long1;
				break;
			}
		}
		return indice;
	}

	/*
	 * metodo usado para remover um projeto do HASHSET persistidos, que eh
	 * recuperado, modificado, e posterior mente salvo no BD via salvarXML()
	 * 
	 * @params grupoRemover
	 */
	public void remover(Projeto projetoRemover) {
		this.persistidos = this.carregarXML();
		Long indice = procurarChave(projetoRemover);
		if (indice != null) {
			persistidos.remove(indice);
			this.salvarXML(persistidos);
		}
	}

	/*
	 * Metodo que substitui um projeto no HASHSET de persistidos, colocando outro
	 * grupo de interesse no lugar com isso e salvando o hashset posteriormente, com
	 * isso, atualizando o valor do projeto no BD
	 */
	public boolean atualizar(Projeto projetoSubstituivel, Projeto projetoSubstituto) throws Exception {
		if (projetoSubstituto.getNome().length() < 5 || projetoSubstituto.getAporteCapitalReais() == 0
				|| projetoSubstituto.getAporteCusteioReais() == 0 || projetoSubstituto.getGastoExecutadoCapitalReais() == 0
				|| projetoSubstituto.getGastoExecutadoCusteioReais() == 0) {
			throw new Exception("Digite todos os parametros corretamente!");
		}
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (persistidos.get(chave).equals(projetoSubstituivel)) {
				persistidos.replace(chave, projetoSubstituto);
			}
		}
		this.salvarXML(persistidos);
		return true;
	}
	public ArrayList<Projeto> recuperarProjetosComMembro(Membro membro) throws Exception{
		ArrayList<Projeto> projetos= new ArrayList<Projeto>();
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (persistidos.get(chave).buscarComponente(membro)) {
				projetos.add(persistidos.get(chave));
			}
		}
		return projetos;
	}
	/*
	 * metodo usado para consultar um projeto no hashset de persistidos, por meio de
	 * seus atributos. caso exista um projeto com o mesmo ou os mesmos atributos
	 * escolhidos,eh retornado um set com todos os projetos correspondentes. sao
	 * ultilizados como paramentro de entrada um array de string onde o nome dos
	 * atributos que se deseja consulta, exatamente como estao declarados na classe
	 * do projeto sao inseridos e os seus respectivos valores sao adicionados nas
	 * repectivas posicoes em um array de object, que no cao sao os valores desses
	 * atributos.
	 * 
	 * @params atributos, valores
	 */

	public Set<Projeto> consultarAnd(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Projeto> auxiliar = new HashSet<Projeto>();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (chave != null) {
				Projeto projetoAuxiliar = persistidos.get(chave);
				boolean[] confirmacoes = new boolean[5];
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = projetoAuxiliar.getNome();
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
								} else if (j == 3) {
									if (valores[3].equals(nomeRecuperado)) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(nomeRecuperado)) {
										confirmacoes[4] = true;
									}
								}
							} else if (atributos[j].equals("aporteCusteioReais")) {
								Float valorRecuperado = projetoAuxiliar.getAporteCusteioReais();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[4] = true;
									}
								}
							} else if (atributos[j].equals("aporteCapitalReais")) {
								Float valorRecuperado = projetoAuxiliar.getAporteCapitalReais();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[4] = true;
									}
								}
							} else if (atributos[j].equals("gastoExecutadoCusteioReais")) {
								Float valorRecuperado = projetoAuxiliar.getCusteioReaisGastoTotal();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[4] = true;
									}
								}
							} else if (atributos[j].equals("getCapitalReaisGastoTotal")) {
								Float valorRecuperado = projetoAuxiliar.getCapitalReaisGastoTotal();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										confirmacoes[4] = true;
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
					auxiliar.add(projetoAuxiliar);
				}
			}
		}
		return auxiliar;
	}

	/*
	 * metodo usado para cosultar se existe um determinado projeto no hashset de
	 * persistidos, que eh como o BD eh construido, isso eh feito por meio da
	 * disponibilizacao dos atribuos que vao ser consultados via array de string, e
	 * os seus respectivos valoress via um array de object, esse metodo retorna um
	 * set com todos os projetos correspondentes a pelo menos um dos atributos
	 * consultados,
	 * 
	 * @params atributos, valores
	 */
	public Set<Projeto> consultarOr(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Projeto> auxiliar = new HashSet<Projeto>();
		Set<Long> chaves = persistidos.keySet();
		boolean adicionar = false;
		adicionar = true;
		for (Long chave : chaves) {
			if (chave != null) {
				Projeto projetoAuxiliar = persistidos.get(chave);
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = projetoAuxiliar.getNome();
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
								} else if (j == 3) {
									if (valores[3].equals(nomeRecuperado)) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(nomeRecuperado)) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("aporteCusteioReais") && adicionar == false) {
								Float valorRecuperado = projetoAuxiliar.getAporteCusteioReais();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										adicionar = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("aporteCapitalReais") && adicionar == false) {
								Float valorRecuperado = projetoAuxiliar.getAporteCapitalReais();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										adicionar = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("gastoExecutadoCusteioReais") && adicionar == false) {
								Float valorRecuperado = projetoAuxiliar.getCusteioReaisGastoTotal();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										adicionar = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("getCapitalReaisGastoTotal") && adicionar == false) {
								Float valorRecuperado = projetoAuxiliar.getCapitalReaisGastoTotal();
								if (j == 0) {
									if (valorRecuperado == valores[0]) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (valorRecuperado == valores[1]) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (valorRecuperado == valores[2])
										adicionar = true;
								} else if (j == 3) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valorRecuperado == valores[3]) {
										adicionar = true;
									}
								}
							}
						}

					}
				}
				if (adicionar) {
					auxiliar.add(projetoAuxiliar);
				}
			}
		}
		return auxiliar;
	}

	/*
	 * salva o hashmap de persistidos em XML
	 * 
	 * @params persistidos
	 */
	private void salvarXML(HashMap<Long, Projeto> persistidos) {

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
	 * Caso o XML já exista, apenas atualiza o mesmo, caso nao, um novo XML eh
	 * criado
	 */

	private HashMap<Long, Projeto> carregarXML() {

		if (arquivoColecao.exists()) {
			try {
				FileInputStream r = new FileInputStream(arquivoColecao);
				return (HashMap<Long, Projeto>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<Long, Projeto>();
	}

}