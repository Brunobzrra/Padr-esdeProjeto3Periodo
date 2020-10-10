package persistenia.xml;

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

import model.projetos.Edital;

public class DAOXMLEdital {

	private HashMap<Long, Edital> persistidos;

	private final File arquivoColecao = new File("XMLEdital.xml");

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id = 0;

	public boolean criar(Edital edital) {
		this.persistidos = this.carregarXML();
		id += 1;
		this.persistidos.put(id, edital);
		this.salvarXML(persistidos);
		return true;
	}

	public void remover(long id) {
		this.persistidos = this.carregarXML();
		persistidos.remove(id);
		this.salvarXML(persistidos);

	}

	public boolean atualizar(long id, Edital edital) {
		this.persistidos = this.carregarXML();
		persistidos.replace(id, edital);
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
				boolean[] confirmacoes = new boolean[3];
				if (atributos != null) {
					for (int j = 0; j < atributos.length; j++) {
						if (valores != null) {
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
								} else {
									if (valores[2].equals(nomeRecuperado))
										confirmacoes[2] = true;
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
								} else {
									if (dataDeInicioRecuperada.equals(valores[2]))
										confirmacoes[2] = true;
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
								} else {
									if (dataTerminoRecuperada.equals(valores[2]))
										confirmacoes[2] = true;
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

	public Set<Edital> consultarOr(String nome, Date dataInicio, Date dataTermino, boolean ativo) {
		this.persistidos = this.carregarXML();
		Set<Edital> auxiliar = new HashSet<Edital>();

		for (int i = 0; i < persistidos.size(); i++) {
			Edital editalAuxiliar = persistidos.get(i);
			if (editalAuxiliar.getNome().equalsIgnoreCase(nome) || editalAuxiliar.getDataInicio().equals(dataInicio)
					|| editalAuxiliar.getDataTermino().equals(dataTermino) || editalAuxiliar.getAtivo() == ativo) {
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
