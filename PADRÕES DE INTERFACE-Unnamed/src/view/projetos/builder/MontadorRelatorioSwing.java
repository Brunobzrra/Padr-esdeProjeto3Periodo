package view.projetos.builder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.casosDeUsofachadas.CasoDeUsoExtra;
import model.casosDeUsofachadas.CasoDeUsoOito;

public class MontadorRelatorioSwing extends JPanel implements InterfaceDeMontagemRelatorio {
	private CasoDeUsoOito fachada = new CasoDeUsoOito();
	private CasoDeUsoExtra fachadaExtra = new CasoDeUsoExtra();

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
//		setLayout(null);
//		JEditorPane jep = new JEditorPane();
//		JScrollPane jsp = new JScrollPane(jep);
//		add(jsp);
//
//		jep.setContentType("Relatorio/html");
//		try {
//			jep.setPage(System.getProperty("user.dir") + "\\Relatorio.html");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		setBounds(50, 50, 600, 800);
//		setVisible(true);
		JFrame tela = new JFrame();
		tela.setSize(600, 800);
		tela.add(this);

		tela.setDefaultCloseOperation(1);
		tela.setLocationRelativeTo(null);
		LayoutManager layout = new FlowLayout();
		this.setLayout(layout);

		JEditorPane jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);
		URL url = MontadorRelatorioSwing.class.getResource(System.getProperty("user.dir") + "\\Relatorio.html");
		System.out.println(System.getProperty("user.dir"));
		try {
			jEditorPane.setPage(url);
		} catch (IOException e) {
			jEditorPane.setContentType("Relatorio/html");
			jEditorPane.setText("<html>Page not found.</html>");
		}

		JScrollPane jScrollPane = new JScrollPane(jEditorPane);
		jScrollPane.setPreferredSize(new Dimension(540, 400));

		this.add(jScrollPane);
		tela.getContentPane().add(this, BorderLayout.CENTER);
		tela.setVisible(true);
	}

}
