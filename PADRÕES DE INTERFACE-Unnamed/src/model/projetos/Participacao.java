package model.projetos;

import java.util.Date;

public class Participacao extends IntegracaoDeProjeto{

	private Date dataInicio;

	private Date dataTermino;

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


	public float getCustoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public float getCusteioReaisNaoGastoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getCapitalReaiNaoGastoTotal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void remover(IntegracaoDeProjeto integracao) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Paricipação não remove!");
	}
	
	@Override
	public void mover(IntegracaoDeProjeto integracao) throws Exception {
		// TODO Auto-generated method stub
		//Deixamos assim, pois, achamos que não faz sentido parciáção mover-se
		throw new Exception("Paricipação não se move!");
	}
	
	//getters e setters
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
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
