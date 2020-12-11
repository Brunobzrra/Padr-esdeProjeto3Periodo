package view.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.casosDeUsofachadas.CasoDeUsoDez;
import model.casosDeUsofachadas.CasoDeUsoExtra;
import ponto.model.projetos.DiaSemana;

public class ControllerCadastroHorarioPrevisto {

	private CasoDeUsoDez casoDeUso = new CasoDeUsoDez();
	private CasoDeUsoExtra casoExtra = new CasoDeUsoExtra();

	public void adcionarHorarioTrabalhado(long matriculaCoordenador, long matriculaMembro, String nomeDoProjeto,
			DiaSemana dia, LocalDateTime dataInicio, LocalDateTime dataTermino, long toleranciaMinutos)
			throws Exception {

		casoDeUso.adcionarHorarioTrabalhado(matriculaCoordenador, matriculaMembro, nomeDoProjeto, dia, dataInicio,
				dataTermino, toleranciaMinutos);

	}

	public ArrayList<String> mostrarProjetosDoUsuarioLogado() throws Exception {
		return casoExtra.recuperarProjetos();
	}

}
