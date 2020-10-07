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

import model.autenticacao.Membro;



public class DAOXMLMembroConta {

	private final XStream xstream = new XStream(new DomDriver("UTF-8"));
	private long id;
	private HashMap<Long,Membro> persistidos;

	private final File arquivoColecao = new File("XMLMembroConta.xml");

	public boolean criar(Membro membro) {
		this.carregarXML();
		id = persistidos.size()+1;
		this.persistidos.put(id, membro);
		this.salvarXML(persistidos);
		return true;
	}
	
	public void remover(long id) {
		this.carregarXML();
		persistidos.remove(id);
		this.salvarXML(persistidos);
	}

	public boolean atualizar(long id, Membro membro) {
		this.carregarXML();
		persistidos.replace(id, membro);
		this.salvarXML(persistidos);
		return true;
	}

	public Set<Membro> consultarAnd(String atributos, String respectivosValoresAtributos) {
		return null;
	}

	public Set<Membro> consultarOr(String atributos, String respectivosValoresAtributos) {
		return null;
	}

	private void salvarXML(HashMap<Long,Membro> persistidos) {
		
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
				this.persistidos =  (HashMap<Long,Membro>) xstream.fromXML(r);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		this.persistidos = new HashMap<Long,Membro>();
	}

}
