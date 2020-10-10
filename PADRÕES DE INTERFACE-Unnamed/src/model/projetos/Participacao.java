package model.projetos;

import java.util.Date;

import org.joda.time.DateTime;

import org.joda.time.Period;

public class Participacao extends ProjetoComponente {

	private Date dataInicio;

	private Date dataTermino;

	// quantidade de dinheiro recebido por mes
	private float aporteCusteioMensalReais;

	// meses de recebimento futuro
	private short qtdMesesCusteados;

	// meses que foram pagos
	private short qtdMesesPagos;

	private boolean coordenador;

	// metodos obrigatorios
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
		return this.quantidadeDeMesesProjeto() * aporteCusteioMensalReais;

	}

	public float getCusteioReaisNaoGastoTotal() {
		float mesesDife = this.quantidadeDeMesesProjeto() - qtdMesesPagos;
		return aporteCusteioMensalReais * mesesDife;
	}

	private float quantidadeDeMesesProjeto() {
		DateTime i = new DateTime(dataInicio);
		DateTime f = new DateTime(dataTermino);
		Period dura = new Period(i, f);
		return dura.getMonths();
	}

	@Override
	public float getCapitalReaiNaoGastoTotal() throws Exception {
		float aux = 0;
		aux = this.qtdMesesCusteados - this.qtdMesesPagos;
		return aux * this.aporteCusteioMensalReais;
	}

	@Override
	public void remover(ProjetoComponente integracao) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Paricipação não remove!");
	}

	@Override
	public void mover(ProjetoComponente integracao) throws Exception {
		// TODO Auto-generated method stub
		// Deixamos assim, pois, achamos que não faz sentido parciáção mover-se
		throw new Exception("Paricipação não se move!");
	}

	// getters e setters
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
