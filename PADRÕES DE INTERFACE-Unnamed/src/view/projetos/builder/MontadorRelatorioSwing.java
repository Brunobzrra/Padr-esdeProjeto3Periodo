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

/**
 * Montador concreto para um relatorio de projeto usando da API Swing, sera
 * exibido em uma janela swing.
 * 
 * @author bruno
 */

public class MontadorRelatorioSwing extends JPanel implements InterfaceDeMontagemRelatorio {
	private CasoDeUsoOito fachada = new CasoDeUsoOito();
	private CasoDeUsoExtra fachadaExtra = new CasoDeUsoExtra();

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
		JFrame tela = new JFrame();
		tela.setSize(560, 450);
		tela.add(this);

		tela.setDefaultCloseOperation(1);
		tela.setLocationRelativeTo(null);
		LayoutManager layout = new FlowLayout();
		this.setLayout(layout);

		JEditorPane jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);
		jEditorPane.setContentType("text/html");

		try {
			jEditorPane.setText(fachada.gerarRelatorio(fachadaExtra.recuperarProjetoComponente(componente)).toString());
		} catch (Exception e) {
			jEditorPane.setText("<html>Page not found.</html>");
		}

		JScrollPane jScrollPane = new JScrollPane(jEditorPane);
		jScrollPane.setPreferredSize(new Dimension(540, 400));

		this.add(jScrollPane);
		tela.getContentPane().add(this, BorderLayout.CENTER);
		tela.setVisible(true);
	}

}
