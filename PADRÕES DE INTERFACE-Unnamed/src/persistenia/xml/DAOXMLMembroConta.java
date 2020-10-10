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

import model.autenticacao.ContaEmail;
import model.autenticacao.Membro;
import model.projetos.Grupo;

public class DAOXMLMembroConta {

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id = 0;
	private HashMap<Long, Membro> persistidos;

	private final File arquivoColecao = new File("XMLMembroConta.xml");

	public boolean criar(Membro membro) {
		this.persistidos = this.carregarXML();
		id += 1;
		this.persistidos.put(id, membro);
		this.salvarXML(persistidos);
		return true;
	}

	public void remover(long id) {
		this.persistidos = this.carregarXML();
		persistidos.remove(id);
		this.salvarXML(persistidos);
	}

	public boolean atualizar(long id, Membro membro) {
		this.persistidos = this.carregarXML();
		persistidos.replace(id, membro);
		this.salvarXML(persistidos);
		return true;
	}

	// vai retornar o Set de membro

	public Set<Membro> consultarAnd(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Membro> auxiliar = new HashSet<Membro>();
		Set<Long> chaves = persistidos.keySet();
		for (Long chave : chaves) {
			if (chave != null) {
				Membro membroAuxiliar = persistidos.get(chave);
				boolean[] confirmacoes = new boolean[7];
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = membroAuxiliar.getNome();
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
								} else if (j == 5) {
									if (valores[5].equals(nomeRecuperado)) {
										confirmacoes[5] = true;
									}
								} else if (valores[6].equals(nomeRecuperado)) {
									confirmacoes[6] = true;
								}
							} else if (atributos[j].equals("email")) {
								String emailRecuperado = membroAuxiliar.getEmail();
								if (j == 0) {
									if (emailRecuperado.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (emailRecuperado.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (emailRecuperado.equals(valores[2]))
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valores[3].equals(emailRecuperado)) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(emailRecuperado)) {
										confirmacoes[4] = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(emailRecuperado)) {
										confirmacoes[5] = true;
									}
								} else {
									if (valores[6].equals(emailRecuperado)) {
										confirmacoes[6] = true;
									}
								}
							} else if (atributos[j].equals("senha")) {
								String senhaRecuperada = membroAuxiliar.getSenha();
								if (j == 0) {
									if (senhaRecuperada.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (senhaRecuperada.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (senhaRecuperada.equals(valores[2]))
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valores[3].equals(senhaRecuperada)) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(senhaRecuperada)) {
										confirmacoes[4] = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(senhaRecuperada)) {
										confirmacoes[5] = true;
									}
								} else {
									if (valores[6].equals(senhaRecuperada)) {
										confirmacoes[6] = true;
									}
								}
							} else if (atributos[j].equals("conta")) {
								ContaEmail contaRecuperada = membroAuxiliar.getConta();
								if (j == 0) {
									if (contaRecuperada.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (contaRecuperada.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (contaRecuperada.equals(valores[2])) {
										confirmacoes[2] = true;
									}
								} else if (j == 3) {
									if (valores[3].equals(contaRecuperada)) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(contaRecuperada)) {
										confirmacoes[4] = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(contaRecuperada)) {
										confirmacoes[5] = true;
									}
								} else {
									if (valores[6].equals(contaRecuperada)) {
										confirmacoes[6] = true;
									}
								}
							} else if (atributos[j].equals("matricula")) {
								Long matriculaRecuperada = membroAuxiliar.getMatricula();
								if (j == 0) {
									if (matriculaRecuperada.equals(valores[0])) {
										confirmacoes[0] = true;
									}
								} else if (j == 1) {
									if (matriculaRecuperada.equals(valores[1])) {
										confirmacoes[1] = true;
									}
								} else if (j == 2) {
									if (matriculaRecuperada.equals(valores[2]))
										confirmacoes[2] = true;
								} else if (j == 3) {
									if (valores[3].equals(matriculaRecuperada)) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(matriculaRecuperada)) {
										confirmacoes[4] = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(matriculaRecuperada)) {
										confirmacoes[5] = true;
									}
								} else {
									if (valores[6].equals(matriculaRecuperada)) {
										confirmacoes[6] = true;
									}
								}
							} else if (atributos[j].equals("ativo")) {
								Boolean ativo = membroAuxiliar.getAtivo();
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
								} else if (j == 3) {
									if (ativo.equals(valores[3])) {
										confirmacoes[3] = true;
									}
								} else if (j == 4) {
									if (ativo.equals(valores[4])) {
										confirmacoes[4] = true;
									}
								} else if (j == 5) {
									if (ativo.equals(valores[5])) {
										confirmacoes[5] = true;
									}
								} else {
									if (ativo.equals(valores[6])) {
										confirmacoes[6] = true;
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
					auxiliar.add(membroAuxiliar);
				}
			}
		}
		return auxiliar;
	}

	public Set<Membro> consultarOr(String[] atributos, Object[] valores) {
		this.persistidos = this.carregarXML();
		Set<Membro> auxiliar = new HashSet<Membro>();
		Set<Long> chaves = persistidos.keySet();
		boolean adicionar = false;
		Membro membroAuxiliar = null;
		for (Long chave : chaves) {
			if (chave != null) {
				membroAuxiliar = persistidos.get(chave);
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
							if (atributos[j].equals("nome")) {
								String nomeRecuperado = membroAuxiliar.getNome();
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
								} else if (j == 5) {
									if (valores[5].equals(nomeRecuperado)) {
										adicionar = true;
									}
								} else if (valores[6].equals(nomeRecuperado)) {
									adicionar = true;
								}
							} else if (atributos[j].equals("email")) {
								String emailRecuperado = membroAuxiliar.getEmail();
								if (j == 0) {
									if (emailRecuperado.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (emailRecuperado.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (emailRecuperado.equals(valores[2]))
										adicionar = true;
								} else if (j == 3) {
									if (valores[3].equals(emailRecuperado)) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(emailRecuperado)) {
										adicionar = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(emailRecuperado)) {
										adicionar = true;
									}
								} else {
									if (valores[6].equals(emailRecuperado)) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("senha")) {
								String senhaRecuperada = membroAuxiliar.getSenha();
								if (j == 0) {
									if (senhaRecuperada.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (senhaRecuperada.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (senhaRecuperada.equals(valores[2]))
										adicionar = true;
								} else if (j == 3) {
									if (valores[3].equals(senhaRecuperada)) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(senhaRecuperada)) {
										adicionar = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(senhaRecuperada)) {
										adicionar = true;
									}
								} else {
									if (valores[6].equals(senhaRecuperada)) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("conta")) {
								ContaEmail contaRecuperada = membroAuxiliar.getConta();
								if (j == 0) {
									if (contaRecuperada.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (contaRecuperada.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (contaRecuperada.equals(valores[2])) {
										adicionar = true;
									}
								} else if (j == 3) {
									if (valores[3].equals(contaRecuperada)) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(contaRecuperada)) {
										adicionar = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(contaRecuperada)) {
										adicionar = true;
									}
								} else {
									if (valores[6].equals(contaRecuperada)) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("matricula")) {
								Long matriculaRecuperada = membroAuxiliar.getMatricula();
								if (j == 0) {
									if (matriculaRecuperada.equals(valores[0])) {
										adicionar = true;
									}
								} else if (j == 1) {
									if (matriculaRecuperada.equals(valores[1])) {
										adicionar = true;
									}
								} else if (j == 2) {
									if (matriculaRecuperada.equals(valores[2]))
										adicionar = true;
								} else if (j == 3) {
									if (valores[3].equals(matriculaRecuperada)) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (valores[4].equals(matriculaRecuperada)) {
										adicionar = true;
									}
								} else if (j == 5) {
									if (valores[5].equals(matriculaRecuperada)) {
										adicionar = true;
									}
								} else {
									if (valores[6].equals(matriculaRecuperada)) {
										adicionar = true;
									}
								}
							} else if (atributos[j].equals("ativo")) {
								Boolean ativo = membroAuxiliar.getAtivo();
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
								} else if (j == 3) {
									if (ativo.equals(valores[3])) {
										adicionar = true;
									}
								} else if (j == 4) {
									if (ativo.equals(valores[4])) {
										adicionar = true;
									}
								} else if (j == 5) {
									if (ativo.equals(valores[5])) {
										adicionar = true;
									}
								} else {
									if (ativo.equals(valores[6])) {
										adicionar = true;
									}
								}
							}
						}

					}
				}

			}
			if (adicionar) {
				auxiliar.add(membroAuxiliar);
			}
		}
		return auxiliar;
	}

	private void salvarXML(HashMap<Long, Membro> persistidos) {

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

	private HashMap<Long, Membro> carregarXML() {

		if (arquivoColecao.exists()) {
			try {
				FileInputStream r = new FileInputStream(arquivoColecao);
				return (HashMap<Long, Membro>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<Long, Membro>();
	}

}
