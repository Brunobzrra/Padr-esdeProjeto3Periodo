package view.projetos;

public class DiretorDeMontagemDeRelatorio {
	private InterfaceDeMontagemRelatorio montadorDeRelatorio;
	public void montarRelatorioCompleto(String componente) throws Exception {
		montadorDeRelatorio.montarRelatorio(componente);
	}

	public void montarArquivo(String texto) {
		montadorDeRelatorio.montarArquivo(texto);
	}

	public void abrirArquivo() {
		montadorDeRelatorio.abrirArquivo();
	}
}
