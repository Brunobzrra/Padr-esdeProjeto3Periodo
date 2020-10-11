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

	public boolean criar(Projeto projeto, Membro m, Participacao p) throws Exception {
		this.persistidos = this.carregarXML();
		Set<Long> chave = persistidos.keySet();
		Long idRecuperado = null;
		for (Long long1 : chave) {
			if (persistidos.get(long1).getNome().equals(projeto.getNome())) {
				idRecuperado = long1;
				break;
			}
		}
		if (m != null && projeto != null && idRecuperado != null && p != null) {
			m.adicionar(p);
			projeto.adicionar(m);
			this.persistidos.put(idRecuperado, projeto);
			this.salvarXML(persistidos);
			return true;
		}
		return false;
	}

	public void remover(Projeto p, Membro m, Participacao part) throws Exception {
		this.persistidos = this.carregarXML();
		Set<Long> chave = persistidos.keySet();
		Long idRecuperado = null;
		for (Long long1 : chave) {
			if (persistidos.get(long1).getNome().equals(p.getNome())) {
				idRecuperado = long1;
				break;
			}
		}
		if (m != null && p != null && idRecuperado != null) {
			ArrayList<ProjetoComponente> itensRecuperados = p.getItens();
			for (ProjetoComponente projetoComponente : itensRecuperados) {
				if (projetoComponente instanceof Membro) {
					if (m.getMatricula() == m.getMatricula()) {
						m.remover(part);
					}
				}
			}
		}
		this.salvarXML(persistidos);
	}

	public boolean atualizar(Projeto proj, Participacao partici) {
		this.persistidos = this.carregarXML();
		Set<Long> chave = persistidos.keySet();
		int posParticipProjeto = 0;
		Long idRecuperado = null;
		for (Long long1 : chave) {
			posParticipProjeto = 0;
			ArrayList<ProjetoComponente> auxiliar = persistidos.get(long1).getItens();
			for (int i = 0; i < auxiliar.size(); i++) {
				posParticipProjeto++;
				if (auxiliar.get(i).equals(partici)) {
					idRecuperado = long1;
					break;
				}
			}
		}
		if (partici != null && idRecuperado != null) {
			persistidos.get(idRecuperado).getItens().set(posParticipProjeto, partici);
			this.salvarXML(persistidos);
			return true;
		}
		return false;
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