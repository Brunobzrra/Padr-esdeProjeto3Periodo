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
import model.projetos.Grupo;

public class DAOXMLGrupo {

	private HashMap<Long, Grupo> persistidos;

	private final File arquivoColecao = new File("XMLGrupo.xml");

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id = 0;

	public boolean criar(Grupo grupo) {
		this.persistidos = this.carregarXML();
		id += 1;
		this.persistidos.put(id, grupo);
		this.salvarXML(persistidos);
		return true;
	}

	public void remover(long id) {
		this.persistidos = this.carregarXML();
		persistidos.remove(id);
		this.salvarXML(persistidos);
	}

	public boolean atualizar(long id, Grupo grupo) {
		this.persistidos = this.carregarXML();
		persistidos.replace(id, grupo);
		this.salvarXML(persistidos);
		return true;
	}

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
							} else if (atributos[j].equals("dataDeCriacao")&& adicionar==false) {
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
							} else if (atributos[j].equals("linkCNPq")&& adicionar==false) {
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
							} else if (atributos[j].equals("ativo")&& adicionar==false) {
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
