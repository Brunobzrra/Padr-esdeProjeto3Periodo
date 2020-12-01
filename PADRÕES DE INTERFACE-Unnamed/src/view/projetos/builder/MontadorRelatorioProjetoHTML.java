package view.projetos.builder;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoOito;

/**
 * Montador concreto de um relatorio de projeto para um arquivo em HTML
 * 
 * @author bruno
 */

public class MontadorRelatorioProjetoHTML extends File implements InterfaceDeMontagemRelatorio {
	private CasoDeUsoOito fachada = new CasoDeUsoOito();
	private CasoDeUsoExtra fachadaExtra = new CasoDeUsoExtra();

	public MontadorRelatorioProjetoHTML(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	public void montarRelatorio(String componente) throws Exception {
		montarArquivo(fachada.gerarRelatorio(fachadaExtra.recuperarProjetoComponente(componente)).toString());
		abrirArquivo(componente);
	}

	public void montarArquivo(String texto) {
		FileWriter fw;
		try {
			fw = new FileWriter(new File("Relatorio.html"));
			fw.write(texto);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void abrirArquivo(String componente) {

		String file = System.getProperty("user.dir") + "/Relatorio.html";
		try {
			Desktop.getDesktop().open(new File(file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
