package view.projetos.builder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

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

	/**
	 * Aqui é criado a base e o cabesario do relatorio
	 */
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
		reiniciar();
		texto += "	Relatorio\n\n";
	}

	/**
	 * metodos que com o uso da sobrecarga constroe o corpo do relatorio
	 */
	public void montarCorpoRelatorio(Projeto projeto) {
		texto += "Projeto: ".toUpperCase() + projeto.getNome() + "\n" + "Aporte Custeio Reais: ".toUpperCase() + projeto.getAporteCusteioReais()
				+ "\n" + "Aporte Capital Reais: ".toUpperCase() + projeto.getAporteCapitalReais() + "\n"
				+ "Gasto Executado Custeio Reais: ".toUpperCase() + projeto.getAporteCusteioReais() + "\n"
				+ "gasto Executado Capital Reais: ".toUpperCase() + projeto.getAporteCapitalReais() + "\n";
	}

	public void montarCorpoRelatorio(Edital edital) {
		texto += "Edital: ".toUpperCase() + edital.getNome() + "\n" + "Data Inicio: ".toUpperCase() + edital.getDataInicio().toString() + "\n"
				+ "Data Termino: ".toUpperCase() + edital.getDataTermino().toString() + "\n";

	}

	public void montarCorpoRelatorio(Grupo grupo) {

		texto += "Grupo: ".toUpperCase() + grupo.getNome() + "\n" + "Data de Criação: ".toUpperCase() + grupo.getDataCriacao().toString() + "\n"
				+ "linkCNPq: ".toUpperCase() + grupo.getLinkCNPq() + "\n";
	
	}
	public void montarProjetosFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += "Projetos: ".toUpperCase() + "\n   ";
		boolean contemDado=false;
		for (ProjetoComponente projetoComponente : componentes) {
			if(projetoComponente.getTipo()==TipoProjetoComponente.PROJETO) {
			texto += projetoComponente.getNome() + "\n";
			contemDado=true;
			}
		}
		if (!contemDado) {
			texto += "Não tem projeto cadastrado!" + "\n";
		}

	}

	public void montarEditaisFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += "Editais: ".toUpperCase() + "\n   ";
		boolean contemDado=false;
		for (ProjetoComponente projetoComponente : componentes) {
			if(projetoComponente.getTipo()==TipoProjetoComponente.EDITAL) {
			texto += projetoComponente.getNome() + "\n";
			contemDado=true;
			}
		}
		if (!contemDado) {
			texto += "Não tem edital cadastrado!" + "\n";
		}
	}

	public void montarMembrosFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += "Membros: ".toUpperCase() + "\n   ";
		boolean contemDado=false;
		for (ProjetoComponente projetoComponente : componentes) {
			if(projetoComponente.getTipo()==TipoProjetoComponente.MEMBRO || projetoComponente.getTipo()==TipoProjetoComponente.PARTICIPACAO) {
			texto += projetoComponente.getNome() + "\n";
			contemDado=true;
			}
		}
		if (!contemDado) {
			texto += "Não tem membro cadastrado!" + "\n";
		}
	}

	public void montarGruposFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += "Grupos: ".toUpperCase() + "\n   ";
		boolean contemDado=false;
		for (ProjetoComponente projetoComponente : componentes) {
			if(projetoComponente.getTipo()==TipoProjetoComponente.EDITAL) {
			texto += projetoComponente.getNome() + "\n";
			contemDado=true;
			}
		}
		if (!contemDado) {
			texto += "Não tem grupo cadastrado!" + "\n";
		}
	}

	/**
	 * este metodo fecha e finaliza o relatorio
	 */
	public void finalizarMontagem() {
		textArea.setText(texto);
		setVisible(true);
		try {
			LoggerProjeto.getInstance().getLogger().warning("Relatorio gerado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JFrame getRelatorio() {
		return this;
	}

	private void reiniciar() {
		texto = "";
	}


}
