package view.projetos.builder;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;

/**
 * Montador concreto para um relatorio de projeto usando da API Swing, sera
 * exibido em uma janela swing.
 * 
 * @author bruno
 */
public class MontadorRelatorioSwing extends JPanel implements InterfaceDeMontagemRelatorio {

	private JFrame tela;

	private JScrollPane painelDoRelatorio;

	private JTextArea textArea;

	private String texto;

	@Override
	public void iniciarMontagem() {

		tela = new JFrame();
		tela.setBackground(Color.BLACK);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setBounds(150, 0, 850, 700);
		tela.setVisible(true);
		

		painelDoRelatorio = new JScrollPane();
		painelDoRelatorio.setBounds(new Rectangle());
		tela.add(painelDoRelatorio);

	}

	@Override
	public void montarCorpoRelatorio(Projeto projeto) {
		reiniciar();
		if (projeto.getItens().size() == 0)
			texto += "nenhum edital cadastrado";
		else {
			for (ProjetoComponente projetoComponente : projeto.getItens()) {
				if(projetoComponente.getTipo() == TipoProjetoComponente.PROJETO) {
				texto += "Nome: " + projetoComponente.getNome() + "\nAporteCusteiReais: "
						+ ((Projeto) projetoComponente).getAporteCusteioReais() + "\nAporteCapitalReais: "
						+ ((Projeto) projetoComponente).getAporteCapitalReais()+"\nAporteCusteioReais: "
						+ ((Projeto) projetoComponente).getAporteCusteioReais()+"\nAporteCapitalReais: "
						+ ((Projeto) projetoComponente).getAporteCapitalReais();
				}
			}
		}
		System.out.println(texto + "\nCUUUUUUUU");
		textArea = new JTextArea(texto);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		painelDoRelatorio.add(textArea);
//		textArea.setText(texto);
		textArea.repaint();
		painelDoRelatorio.repaint();
		tela.repaint();

	}

	@Override
	public void montarCorpoRelatorio(Edital edital) {
		// TODO Auto-generated method stub

	}

	@Override
	public void montarCorpoRelatorio(Grupo grupo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizarMontagem() {
		// TODO Auto-generated method stub

	}

	public void reiniciar() {
		texto = "";
	}

}
//	private CasoDeUsoOito fachada = new CasoDeUsoOito();
//	private CasoDeUsoExtra fachadaExtra = new CasoDeUsoExtra();
//
//	public void montarRelatorio(String componente) throws Exception {
////		montarArquivo(fachada.gerarRelatorio(fachadaExtra.recuperarProjetoComponente(componente)).toString());
//		abrirArquivo(componente);
//	}
//
//	public void montarArquivo(String texto) {
//		FileWriter fw;
//		try {
//			fw = new FileWriter(new File("Relatorio.html"));
//			fw.write(texto);
//			fw.flush();
//			fw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void abrirArquivo(String componente) {
//		JFrame tela = new JFrame();
//		tela.setSize(560, 450);
//		tela.add(this);
//
//		tela.setDefaultCloseOperation(1);
//		tela.setLocationRelativeTo(null);
//		LayoutManager layout = new FlowLayout();
//		this.setLayout(layout);
//
//		JEditorPane jEditorPane = new JEditorPane();
//		jEditorPane.setEditable(false);
//		jEditorPane.setContentType("text/html");
//
//		try {
////			jEditorPane.setText(fachada.gerarRelatorio(fachadaExtra.recuperarProjetoComponente(componente)).toString());
//		} catch (Exception e) {
//			jEditorPane.setText("<html>Page not found.</html>");
//		}
//
//		JScrollPane jScrollPane = new JScrollPane(jEditorPane);
//		jScrollPane.setPreferredSize(new Dimension(540, 400));
//
//		this.add(jScrollPane);
//		tela.getContentPane().add(this, BorderLayout.CENTER);
//		tela.setVisible(true);
//	}
//
//
//	@Override
//	public void finalizarMontagem() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void montarRelatorio(ProjetoComponente componente) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void iniciarMontagem(ProjetoComponente componente) {
//		// TODO Auto-generated method stub
//		
//	}
//
