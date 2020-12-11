package view.abstract_factory;

import java.time.LocalDateTime;

import ponto.model.projetos.DiaSemana;

public interface InterfaceTelaCadastroHorarioPrevisto {
	public void adcionarHorarioTrabalhado(long matriculaCoordenador, long matriculaMembro, String nomeDoProjeto,
			DiaSemana dia, LocalDateTime horaInicio, LocalDateTime horaTermino, long toleranciaMinutos)
			throws Exception;
}
