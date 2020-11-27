package view.projetos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoOito;

public class MontadorRelatorioSwing extends JPanel implements InterfaceDeMontagemRelatorio {
	private CasoDeUsoOito fachada;
	private CasoDeUsoExtra fachadaExtra;
	public void montarRelatorio(String componente) throws Exception {
		montarArquivo(fachada.gerarRelatorio(fachadaExtra.recuperarProjetoComponente(componente)).toString());
		abrirArquivo();
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

	public void abrirArquivo() {
		setLayout(null);
		JEditorPane jep = new JEditorPane();
		JScrollPane jsp = new JScrollPane(jep);
		add(jsp);

		jep.setContentType("text/html");
		try{
		  jep.setPage( System.getProperty("user.dir") + "/Relatorio.html");
		}
		catch (Exception e){
		  e.printStackTrace();
		}

		setBounds(50, 50, 600, 800);
		setVisible(true);		
		JFrame tela= new JFrame();
		tela.setSize(600, 800);
		tela.setLayout(null);
		tela.add(this);
		tela.setVisible(true);
	}



}
