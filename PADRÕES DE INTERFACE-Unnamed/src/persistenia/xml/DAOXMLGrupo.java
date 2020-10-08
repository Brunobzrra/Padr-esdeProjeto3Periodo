package persistenia.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import model.projetos.Grupo;

public class DAOXMLGrupo {

	private HashMap<Long,Grupo> persistidos;

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

	public Set<Grupo> consultarAnd(String atributos, String respectivosValoresAtributos) {
		return null;
	}

	public Set<Grupo> consultarOr(String atributos, String respectivosValoresAtributos) {
		return null;
	}

	private void salvarXML(HashMap<Long,Grupo> persistidos) {
		
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

	private HashMap<Long,Grupo> carregarXML() {
		
		if (arquivoColecao.exists()) {
			try {
				FileInputStream r = new FileInputStream(arquivoColecao);
				return (HashMap<Long,Grupo>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<Long,Grupo>();
	}

}
