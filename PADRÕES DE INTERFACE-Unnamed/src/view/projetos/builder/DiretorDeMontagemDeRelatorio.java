package view.projetos.builder;


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

    public void montarRelatorioCompleto(String componente) throws Exception {
        montadorDeRelatorio.montarRelatorio(componente);
    }


    public void montarArquivo(String texto) {
        montadorDeRelatorio.montarArquivo(texto);
    }

    public void abrirArquivo(String componente) {
        montadorDeRelatorio.abrirArquivo(componente);
    }
}