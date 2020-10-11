package persistenia.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;

public class DAOXMLProjetoParticipacao {

	private HashMap<Long, Projeto> persistidos;

	private final File arquivoColecao = new File("XMLProjetoParticipacao.xml");

	private long id = 0;

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));

	public boolean criar(Projeto projeto) throws Exception {
		this.persistidos = this.carregarXML();
		id += 1;
		this.persistidos.put(id, projeto);
		this.salvarXML(persistidos);
		return true;
	}

	public void remover(long id) {
		this.persistidos = this.carregarXML();
		persistidos.remove(id);
		this.salvarXML(persistidos);
	}

	public boolean atualizar(long id, Projeto projeto) {
		this.persistidos = this.carregarXML();
		persistidos.replace(id, projeto);
		this.salvarXML(persistidos);
		return true;
	}

	public Set<Projeto> consultarAnd(String atributos, String respectivosValoresAtributos) {
		return null;
	}

	public Set<Projeto> consultarOr(String atributos, String respectivosValoresAtributos) {
		return null;
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