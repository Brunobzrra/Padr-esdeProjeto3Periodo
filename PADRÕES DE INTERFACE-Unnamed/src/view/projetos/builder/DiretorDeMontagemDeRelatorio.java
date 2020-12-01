package view.projetos.builder;

/**
 * Diretor de montagem dos relatorios que fazem uso da interface de Montagem
 * 
 * @author bruno
 */
public class DiretorDeMontagemDeRelatorio {
	private InterfaceDeMontagemRelatorio montadorDeRelatorio;

	public DiretorDeMontagemDeRelatorio(InterfaceDeMontagemRelatorio montadorDeRelatorio) {
		super();
		this.montadorDeRelatorio = montadorDeRelatorio;
	}

	public InterfaceDeMontagemRelatorio getMontadorDeRelatorio() {
		return montadorDeRelatorio;
	}

	public void setMontadorDeRelatorio(InterfaceDeMontagemRelatorio montadorDeRelatorio) {
		this.montadorDeRelatorio = montadorDeRelatorio;
	}

	/**
	 * Metodo ultilizado para montar um relatorio com o componente.
	 * 
	 * @param componente
	 */
	public void montarRelatorioCompleto(String componente) throws Exception {
		montadorDeRelatorio.montarRelatorio(componente);
	}

	/**
	 * Metodo ultilizado para montar um arquivo com texto.
	 * 
	 * @param texto
	 */

	public void montarArquivo(String texto) {
		montadorDeRelatorio.montarArquivo(texto);
	}

	public void abrirArquivo(String componente) {
		montadorDeRelatorio.abrirArquivo(componente);
	}
}