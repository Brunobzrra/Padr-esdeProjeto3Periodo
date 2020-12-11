package view.projetos.builder;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.projetos.Edital;
import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import model.utilitarios.LoggerProjeto;

/**
 * Montador concreto de um relatorio de projeto para um arquivo em HTML
 * 
 * @author bruno
 */

public class MontadorRelatorioProjetoHTML implements InterfaceDeMontagemRelatorio {
	private FileWriter fw;
	private String texto = "";
	private String file = "";

	/**
	 * Aqui é criado a base e o cabesario do relatorio
	 */
	public void iniciarMontagem() {
		try {
			fw = new FileWriter(new File("Relatorio.html"));
			texto += String.format("<html>\n<head>\n<title>Relatorio Do Item</title>\n"
					+ " <style>\n  .negrito{\n font-weight: bold;\n font-size: 20;\n }"
					+ "\n </style>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodos que com o uso da sobrecarga constroe o corpo do relatorio
	 */
	public void montarCorpoRelatorio(Projeto projeto) {
		texto += String.format(
				"<span class='negrito'>Projeto: </span><span>%s </span><br>\n<span class='negrito'>Aporte Custeio Reais: </span><span>%s </span><br>\n"
						+ "<span class='negrito'>Aporte Capital Reais: </span><span>%s </span><br>\n<span class='negrito'>Gasto Executado Custeio Reais: </span><span>%s </span><br>"
						+ "\n<span class='negrito'>Gasto Executado Capital Reais: </span><span>%s </span><br>\n",
				projeto.getNome(), projeto.getAporteCusteioReais(), projeto.getAporteCapitalReais(),
				projeto.getAporteCusteioReais(), projeto.getAporteCapitalReais());
	}

	public void montarCorpoRelatorio(Edital edital) {
		texto += String.format(
				"<span class='negrito'>Edital: </span><span>%s </span><br>\n<span span class='negrito'>Data de Inicio: </span><span>%s </span><br>\n<span span class='negrito'>Data Termino: </span><span>Data de Criação %s </span><br>\n",
				edital.getNome(), edital.getDataInicio().toString(), edital.getDataTermino().toString());
	}

	public void montarCorpoRelatorio(Grupo grupo) {
		texto += String.format(
				"<span class='negrito'>Grupo:</span><span> %s </span><br>\n<span class='negrito'>Data de Criação: </span><span>%s </span><br>\n<span class='negrito'>linkCNPq:</span><span> %s </span><br>\n",
				grupo.getNome(), grupo.getDataCriacao().toString(), grupo.getLinkCNPq());
	}

	public void montarMembrosFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += String.format("<span class='negrito'>Membros:</span><br>\n");
		boolean contemDado = false;
		for (ProjetoComponente projetoComponente : componentes) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.PARTICIPACAO
					|| projetoComponente.getTipo() == TipoProjetoComponente.MEMBRO) {
				texto += String.format("<blockquote>\n<span>-%s </span><br>\n",
						projetoComponente.getNome() + "\n</blockquote>\n");
				contemDado = true;
			}
		}
		if (!contemDado) {
			texto += String.format("<blockquote>\n<span>-Não tem membro cadastrado!</span><br>\n</blockquote>\n");
		}
	}

	public void montarEditaisFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += String.format("<span class='negrito'>Editais:</span><br>\n");
		boolean contemDado = false;
		for (ProjetoComponente projetoComponente : componentes) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.EDITAL) {
				texto += String.format("<blockquote>\n<span>-%s </span><br>\n",
						projetoComponente.getNome() + "\n</blockquote>\n");
				contemDado = true;
			}
		}
		if (!contemDado) {
			texto += String.format("<blockquote>\n<span>-Não tem edital cadastrado!</span><br>\n</blockquote>\n");
		}
	}

	public void montarProjetosFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += String.format("<span class='negrito'>Projetos:</span><br>\n");
		boolean contemDado = false;
		for (ProjetoComponente projetoComponente : componentes) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.PROJETO) {
				texto += String.format("<blockquote>\n<span>-%s </span><br>\n",
						projetoComponente.getNome() + "\n</blockquote>\n");
				contemDado = true;
			}
		}
		if (!contemDado) {
			texto += String.format("<blockquote>\n<span>-Não tem projeto cadastrado!</span><br>\n</blockquote>\n");
		}
	}

	public void montarGruposFilhos(ArrayList<ProjetoComponente> componentes) {
		texto += String.format("<span class='negrito'>Grupos:</span><br>\n");
		boolean contemDado = false;
		for (ProjetoComponente projetoComponente : componentes) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.GRUPO) {
				texto += String.format("<blockquote>\n<span>-%s </span><br>\n",
						projetoComponente.getNome() + "\n</blockquote>\n");
				contemDado = true;
			}
		}
		if (!contemDado) {
			texto += String.format("<blockquote>\n<span>-Não tem grupo cadastrado!</span><br>\n</blockquote>\n");
		}
	}

	/**
	 * este metodo fecha e finaliza o relatorio
	 */
	public Object finalizarMontagem() {
		try {
			texto += "</body>\n</html>";
			System.out.println(texto);
			LoggerProjeto.getInstance().getLogger().warning("Relatorio gerado");
			file = System.getProperty("user.dir") + "/Relatorio.html";
			fw.write(texto.toString());
			fw.flush();
			fw.close();
			return (Object) fw;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void mostrarRelatorioConstruido() {
		try {
			Desktop.getDesktop().open(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public FileWriter getFw() {
		return fw;
	}
}
