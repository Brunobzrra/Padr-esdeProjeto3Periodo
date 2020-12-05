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

	private String texto = "";

	@Override
	public void iniciarMontagem() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(150, 0, 400, 250);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		setLocationRelativeTo(null);
//		setResizable(false);
		Font f = new Font("Arial", 1, 15);
		textArea.setFont(f);
		painelDoRelatorio = new JScrollPane(textArea);
		painelDoRelatorio.setBounds(new Rectangle());
		add(painelDoRelatorio);
		reiniciar();
		texto+="Relatorio\n";
	}

	public void montarCorpoRelatorio(Projeto projeto) {
		texto += "Projeto: " + projeto.getNome() + "\n" + "Aporte Custeio Reais: " + projeto.getAporteCusteioReais()
				+ "\n" + "Aporte Capital Reais: " + projeto.getAporteCapitalReais() + "\n"
				+ "Gasto Executado Custeio Reais: " + projeto.getAporteCusteioReais() + "\n"
				+ "gasto Executado Capital Reais: " + projeto.getAporteCapitalReais() + "\n" + "Membros: "+"\n";

		if (projeto.getItens().size() == 1) {
			texto += "Não tem membro cadastrado!" + "\n";
		} else {
			for (ProjetoComponente projetoComponente : projeto.getItens()) {
				texto += projetoComponente.getNome() + "\n";
			}
		}
	}

	public void montarCorpoRelatorio(Edital edital) {
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
		texto += "Projetos\n";
		if (projetos.length() == 1) {
			texto += projetos + "\n";
		} else {
			texto += "Não tem projeto cadastrado!" + "\n";
		}
	}

	public void finalizarMontagem() {
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
