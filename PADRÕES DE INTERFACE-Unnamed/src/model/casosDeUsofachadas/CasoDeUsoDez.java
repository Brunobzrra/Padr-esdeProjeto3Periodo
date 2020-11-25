package model.casosDeUsofachadas;

import java.time.LocalDateTime;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;

//caso de uso 10
public class CasoDeUsoDez {
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
	private DAOXMLProjetoParticipacao daoProjeto = new DAOXMLProjetoParticipacao();

	public void adcionarHorarioTrabalhado(long matriculaCoordenador, long matriculaMembro, String nomeDoProjeto,
			DiaSemana dia, LocalDateTime horaInicio, LocalDateTime horaTermino, long toleranciaMinutos) throws Exception {
		Membro coordenador = daoMembro.recuperarPorIndentificador(matriculaCoordenador);
		Projeto projeto = daoProjeto.recuperarPorIndentificador(nomeDoProjeto);
		if (projeto.getCordenador().getMatricula() == coordenador.getMatricula()) {
			Membro membro = daoMembro.recuperarPorIndentificador(matriculaMembro);
			for (ProjetoComponente participa : projeto.getItens()) {
				Participacao participacao = (Participacao) participa;
				if (participacao.getMembro().getMatricula() == membro.getMatricula() && participacao.getAtivo()) {
					if (participacao
							.adicionarHorario(new HorarioPrevisto(dia, horaInicio, horaTermino, toleranciaMinutos))) {
						daoProjeto.atualizar(projeto, projeto);
						return;
					}
					throw new Exception("Já existe este horario!");
				}
			}
			throw new Exception("Este membro não está cadastrado neste projeto!");
		} else {
			throw new Exception("Este coordenador não está cadastrado neste projeto!");
		}
	}
}
