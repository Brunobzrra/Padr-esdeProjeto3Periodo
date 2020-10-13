package persistenia.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

	public boolean criar(Projeto projeto) throws Exception {
		String[] atributos = { "nome" };
		Object[] valores = { projeto.getNome() };
		if (consultarAnd(atributos, valores).size()==0) {
			this.persistidos = this.carregarXML();
			id += 1;
			this.persistidos.put(id, projeto);
			this.salvarXML(persistidos);
			return true;
		}
		return false;
	}

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

	public void remover(Projeto projetoRemover) {
		this.persistidos = this.carregarXML();
		Long indice = procurarChave(projetoRemover);
		if (indice != null) {
			persistidos.remove(indice, projetoRemover);
			this.salvarXML(persistidos);
		}
	}
	public boolean atualizar(Projeto projetoSubstituivel, Projeto projetoSubstituto) {
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
								Float valorRecuperado = projetoAuxiliar.getGastoExecutadoCusteioReais();
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
							} else if (atributos[j].equals("gastoExecutadoCapitalReais")) {
								Float valorRecuperado = projetoAuxiliar.getGastoExecutadoCapitalReais();
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
								Float valorRecuperado = projetoAuxiliar.getGastoExecutadoCusteioReais();
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
							} else if (atributos[j].equals("gastoExecutadoCapitalReais") && adicionar == false) {
								Float valorRecuperado = projetoAuxiliar.getGastoExecutadoCapitalReais();
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