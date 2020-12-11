package model.casosDeUsofachadas;

import java.util.logging.Level;

import model.utilitarios.LoggerProjeto;
import view.projetos.builder.DiretorDeMontagemDeRelatorio;
import view.projetos.builder.MontadorRelatorioProjetoHTML;
import view.projetos.builder.MontadorRelatorioSwing;

//caso de uso 8
public class CasoDeUsoOito {

	/**
	 * gera instring equivalente a um html
	 * 
	 * @throws Exception
	 */
	public void gerarRelatorio(String op, String valor) throws Exception {
		DiretorDeMontagemDeRelatorio diretor = new DiretorDeMontagemDeRelatorio(new MontadorRelatorioSwing());
		if (valor.equals("HTML")) {
			diretor.setMontadorDeRelatorio(new MontadorRelatorioProjetoHTML());
		}
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Gerando relatorio em texto");
		diretor.montarRelatorioCompleto(new CasoDeUsoExtra().recuperarProjetoComponente(op));;
	}


}