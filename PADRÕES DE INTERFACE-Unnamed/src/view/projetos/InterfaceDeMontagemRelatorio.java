package view.projetos;

public interface InterfaceDeMontagemRelatorio {
	
	public void montarRelatorio(String componente) throws Exception ;
	public void montarArquivo(String texto);
	public void abrirArquivo();
}
