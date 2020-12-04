package view.projetos.builder;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

public class MontadorRelatorioProjetoHTML extends File implements InterfaceDeMontagemRelatorio {
	private FileWriter fw;
	private String texto = "";

	public MontadorRelatorioProjetoHTML(String pathname) {
		super(pathname);
	}

	public void iniciarMontagem() {
		try {
			fw = new FileWriter(new File("Relatorio.html"));
			texto += String.format(
					"<html>\n<head>\n<title>Relatorio Do Item</title>\n</head>\n<body>\n<h1 style='text-align:center'>Relatorio</h1>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void montarCorpoRelatorio(Projeto projeto) {
		texto += String.format("<span>Projeto %s </span><br>\n<span>Aporte Custeio Reais %s </span><br>\n"
				+ "<span>Aporte Capital Reais %s </span><br>\n<span>Gasto Executado Custeio Reais %s </span><br>"
				+ "\n<span>gasto Executado Capital Reais %s </span><br>\n<span>Membros </span><br>\n",
				projeto.getNome(), projeto.getAporteCusteioReais(), projeto.getAporteCapitalReais(),
				projeto.getAporteCusteioReais(), projeto.getAporteCapitalReais());
		if (projeto.getItens().size() == 1) {
			texto += "<span>Não tem membro cadastrado!</span><br>\n";
		} else {
			for (ProjetoComponente projetoComponente : projeto.getItens()) {
				texto += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			}
		}
	}

	public void montarCorpoRelatorio(Edital edital) {
		texto += String.format(
				"<span>Edital %s </span><br>\n<span>Data de Inicio %s </span><br>\n<span>Data Termino %s </span><br>\n<span>Grupos </span><br>\n",
				edital.getNome(), edital.getDataInicio().toString(), edital.getDataTermino().toString());
		String grupos = "";
		String projetos = "";
		for (ProjetoComponente projetoComponente : edital.getItens()) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.GRUPO) {
				grupos += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			} else {
				projetos += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			}
		}

		if (grupos.length() == 1) {
			texto += grupos;
		}else {
			texto += "<span>Não tem membro cadastrado!</span><br>\n";
		}
		if (projetos.length() == 1) {
			texto += String.format("<span>Projetos </span><br>\n" + projetos);
		}else {
			texto += "<span>Projetos </span><br>\n<span>Não tem projeto cadastrado!</span><br>\n";
		}
	}

	public void montarCorpoRelatorio(Grupo grupo) {
		texto += String.format(
				"<span>Grupo %s </span><br>\n<span>Data de Criação %s </span><br>\n<span>linkCNPq %s </span><br>\n<span>Membros </span><br>\n",
				grupo.getNome(), grupo.getDataCriacao().toString(), grupo.getLinkCNPq());
		String membros = "";
		String projetos = "";
		for (ProjetoComponente projetoComponente : grupo.getItens()) {
			if (projetoComponente.getTipo() == TipoProjetoComponente.MEMBRO) {
				membros += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			} else {
				projetos += String.format("<span>%s </span><br>\n", projetoComponente.getNome());
			}
		}
		if (membros.length() == 1) {
			texto += membros;
		}else {
			texto += "<span>Não tem membro cadastrado!</span><br>\n";
		}
		if (projetos.length() == 1) {
			texto += String.format("<span>Projetos </span><br>\n" + projetos);
		}else {
			texto += "<span>Projetos </span><br>\n<span>Não tem projeto cadastrado!</span><br>\n";
		}
	}

	public void finalizarMontagem() {
		try {
			texto += "</body>\n</html>";
			System.out.println(texto);
			LoggerProjeto.getInstance().getLogger().warning("Relatorio gerado");
			String file = System.getProperty("user.dir") + "/Relatorio.html";
			fw.write(texto.toString());
			fw.flush();
			fw.close();
			Desktop.getDesktop().open(new File(file));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
