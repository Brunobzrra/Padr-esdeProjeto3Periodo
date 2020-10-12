package persistenia.xml;

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

public class DAOXMLEdital {

	private HashMap<Long, Edital> persistidos;

	private final File arquivoColecao = new File("XMLEdital.xml");

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id = 0;

	public boolean criar(Edital edital) {
		String[] atributos = { "nome" };
		Object[] valores = { edital.getNome() };
		if (consultarAnd(atributos, valores) == null) {
			this.persistidos = this.carregarXML();
			id += 1;
			this.persistidos.put(id, edital);
			this.salvarXML(persistidos);
			return true;
		}
		return false;
	}

	public void remover(Edital editalRemover) {
		this.persistidos = this.carregarXML();
		persistidos.remove(editalRemover);
		this.salvarXML(persistidos);
	}


	public boolean atualizar(Edital editalSubstituivel, Edital editalSubistituto) {
		this.persistidos = this.carregarXML();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (persistidos.get(chave).equals(editalSubstituivel)) {
				persistidos.replace(chave, editalSubistituto);
			}
		}
		this.persistidos = this.carregarXML();
		this.salvarXML(persistidos);
		return true;
	}

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

	public void limpar() {
		this.persistidos = this.carregarXML();
		persistidos.clear();
		this.salvarXML(persistidos);
	}

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
