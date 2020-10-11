package model.fachadas;

public class RelatorioFachada {
	public void gerarRelatorio() {
		long tStart, tEnd, tResult;
	       StringBuffer strBuffer = new StringBuffer();
	       tStart = System.currentTimeMillis();
	       for(int i = 0; i < 100000; i ++){
	              strBuffer.append("a");
	       }
	       tEnd = System.currentTimeMillis();
	       tResult = tEnd - tStart;
	       System.out.println("Tempo de Execução com StringBuffer ="+tResult+" ms");

	}
	public static void main(String[] args) {
		new RelatorioFachada().gerarRelatorio();
	}
}
