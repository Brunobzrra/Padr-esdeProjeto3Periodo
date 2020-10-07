package model.projetos;

public class Participacao extends IntegracaoDeProjeto{

	private long dataInicio;

	private long dataTermino;

	private float aporteCusteioMensalReais;

	private short qtdMesesCusteados;

	private short qtdMesesPagos;

	private boolean coordenador;
	
	//metodos obrigatorios
	@Override
	public void ativar() {
		// TODO Auto-generated method stub
		setAtivo(true);
	}

	@Override
	public void desativar() {
		// TODO Auto-generated method stub
		setAtivo(false);
	}

	@Override
	public float getCustoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCusteioReaisNaoGastoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//getters e setters
	public long getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(long dataInicio) {
		this.dataInicio = dataInicio;
	}

	public long getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(long dataTermino) {
		this.dataTermino = dataTermino;
	}
	public float getAporteCusteioMensalReais() {
		return aporteCusteioMensalReais;
	}

	public void setAporteCusteioMensalReais(float aporteCusteioMensalReais) {
		this.aporteCusteioMensalReais = aporteCusteioMensalReais;
	}

	public short getQtdMesesCusteados() {
		return qtdMesesCusteados;
	}

	public void setQtdMesesCusteados(short qtdMesesCusteados) {
		this.qtdMesesCusteados = qtdMesesCusteados;
	}

	public short getQtdMesesPagos() {
		return qtdMesesPagos;
	}

	public void setQtdMesesPagos(short qtdMesesPagos) {
		this.qtdMesesPagos = qtdMesesPagos;
	}

	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}



	
	

}
