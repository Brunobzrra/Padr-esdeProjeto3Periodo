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


import model.projetos.Edital;

public class DAOXMLEdital {
	
	private HashMap<Long,Edital> persistidos;

	private final File arquivoColecao = new File("XMLEdital.xml");

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id;
	
	public boolean criar(Edital edital) {
		    this.carregarXML();
			id = persistidos.size()+1;
			this.persistidos.put(id, edital);
			this.salvarXML(persistidos);
			return true;
	}

	public void remover(long id) {
		this.carregarXML();
		persistidos.remove(id);
		this.salvarXML(persistidos);
		
	}

	public boolean atualizar(long id, Edital edital) {
		this.carregarXML();
		persistidos.replace(id, edital);
		this.salvarXML(persistidos);
		return true;
	}

	public Set<Edital> consultarAnd(String atributos, String respectivosValoresAtributos) {
		return null;
	}

	public Set<Edital> consultarOr(String[] atributos, String[] respectivosValoresAtributos) {
		return null;
	}

	private void salvarXML(HashMap<Long,Edital> persistidos) {
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

	private void carregarXML() {
		if (arquivoColecao.exists()) {
			try {
				FileInputStream r = new FileInputStream(arquivoColecao);
				this.persistidos =  (HashMap<Long,Edital>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		this.persistidos = new HashMap<Long,Edital>();
	}
	
}
