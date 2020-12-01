package view.controller;

import view.projetos.builder.DiretorDeMontagemDeRelatorio;
import view.projetos.builder.MontadorRelatorioProjetoHTML;
import view.projetos.builder.MontadorRelatorioSwing;

public class ControllerGerarRelatorio {
	public void gerarRelatorio(String op,String valor) throws Exception {
		
		DiretorDeMontagemDeRelatorio diretor = new DiretorDeMontagemDeRelatorio(new MontadorRelatorioSwing());
		if (valor.equals("HTML")) {
			diretor.setMontadorDeRelatorio(new MontadorRelatorioProjetoHTML(""));
		}
		diretor.montarRelatorioCompleto(op);
	}
}
