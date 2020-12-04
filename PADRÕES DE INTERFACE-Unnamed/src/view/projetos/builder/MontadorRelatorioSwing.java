package view.projetos.builder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import model.utilitarios.LoggerProjeto;

/**
 * Montador concreto para um relatorio de projeto usando da API Swing, sera
 * exibido em uma janela swing.
 * 
 * @author bruno
 */
public class MontadorRelatorioSwing extends JFrame implements InterfaceDeMontagemRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane painelDoRelatorio;

	private JTextArea textArea;

	private String texto = "Montando...";

	@Override
	public void iniciarMontagem() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(150, 0, 400, 250);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		setLocationRelativeTo(null);
		Font f = new Font("Arial", 1, 15);
		textArea.setFont(f);
		painelDoRelatorio = new JScrollPane(textArea);
		painelDoRelatorio.setBounds(new Rectangle());
		add(painelDoRelatorio);

	}

	@Override
	public void montarCorpoRelatorio(Projeto projeto) {
		reiniciar();
		texto += "Projeto: " + projeto.getNome() + "\n" + "Aporte Custeio Reais: " + projeto.getAporteCusteioReais()
				+ "\n" + "Aporte Capital Reais: " + projeto.getAporteCapitalReais() + "\n"
				+ "Gasto Executado Custeio Reais: " + projeto.getAporteCusteioReais() + "\n"
				+ "gasto Executado Capital Reais: " + projeto.getAporteCapitalReais() + "\n" + "Membros: ";

		if (projeto.getItens().size() == 1) {
			texto += "Não tem membro cadastrado!" + "\n";
		} else {
			for (ProjetoComponente projetoComponente : projeto.getItens()) {
				texto += projetoComponente.getNome() + "\n";
			}
		}
	}

	public void montarCorpoRelatorio(Edital edital) {
		reiniciar();
		texto += "Edital: " + edital.getNome() + "\n" + "Data Inicio: " + edital.getDataInicio().toString() + "\n"
				+ "Data Termino: " + edital.getDataTermino().toString() + "\n" + "Grupos: " + "\n";

		String grupos = "";
		String projetos = "";

		for (ProjetoComponente projetoComponente : edital.getItens()) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.GRUPO) {
				grupos += projetoComponente.getNome() + "\n";
			} else {
				projetos += projetoComponente.getNome() + "\n";
			}
		}

		if (grupos.length() == 1) {
			texto += grupos;
		} else {
			texto += "Não tem membro cadastrado!" + "\n";
		}
		if (projetos.length() == 1) {
			texto += "Projetos: " + "\n" + projetos;
		} else {
			texto += "Projetos: " + "\n" + "Não tem projeto cadastrado!";
		}
	}

	public void montarCorpoRelatorio(Grupo grupo) {

		texto += "Grupo: " + grupo.getNome() + "\n" + "Data de Criação: " + grupo.getDataCriacao().toString() + "\n"
				+ "linkCNPq: " + grupo.getLinkCNPq() + "\n" + "Membros:  " + "\n";
		String membros = "";
		String projetos = "";
		for (ProjetoComponente projetoComponente : grupo.getItens()) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.MEMBRO) {
				membros += projetoComponente.getNome() + "\n";
			} else {
				projetos += projetoComponente.getNome() + "\n";
			}
		}
		if (membros.length() == 1) {
			texto += membros;
		} else {
			texto += "Não tem membro cadastrado!" + "\n";
		}
		if (projetos.length() == 1) {
			texto += projetos + "\n";
		} else {
			texto += "Não tem projeto cadastrado!" + "\n";
		}
	}

	public void finalizarMontagem() {
		texto += "Fim";
		textArea.setText(texto);
		setVisible(true);

		try {
			LoggerProjeto.getInstance().getLogger().warning("Relatorio gerado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reiniciar() {
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
