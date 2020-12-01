package model.casosDeUsofachadas;

import java.time.LocalDateTime;
import java.util.logging.Level;

import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;

//caso de uso 10
public class CasoDeUsoDez {
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
	private DAOXMLProjetoParticipacao daoProjeto = new DAOXMLProjetoParticipacao();

	public void adcionarHorarioTrabalhado(long matriculaCoordenador, long matriculaMembro, String nomeDoProjeto,
			DiaSemana dia, LocalDateTime horaInicio, LocalDateTime horaTermino, long toleranciaMinutos)
			throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Adicionando horario previsto para uma participacao");
		LoggerProjeto.getInstance().getLogger().info("Recuperando membro e projeto");
		Membro coordenador = daoMembro.recuperarPorIndentificador(matriculaCoordenador);
		Projeto projeto = daoProjeto.recuperarPorIndentificador(nomeDoProjeto);
		LoggerProjeto.getInstance().getLogger().info("Testando se eh coordenador");
		if (projeto.getCordenador().getMatricula() == coordenador.getMatricula()) {
			Membro membro = daoMembro.recuperarPorIndentificador(matriculaMembro);
			LoggerProjeto.getInstance().getLogger().info("eh coordenador, iterando sob participacoes");
			for (ProjetoComponente participa : projeto.getItens()) {
				Participacao participacao = (Participacao) participa;
				LoggerProjeto.getInstance().getLogger().info("testando se o membro esta ativo");
				if (participacao.getMembro().getMatricula() == membro.getMatricula() && participacao.getAtivo()) {
					if (participacao
							.adicionarHorario(new HorarioPrevisto(dia, horaInicio, horaTermino, toleranciaMinutos))) {
						LoggerProjeto.getInstance().getLogger().warning("Salvando o horario previso");
						daoProjeto.atualizar(projeto, projeto);
						return;
					}
					LoggerProjeto.getInstance().getLogger().severe("Horario existente");
					throw new Exception("Já existe este horario!");
				}
			}
			LoggerProjeto.getInstance().getLogger().severe("Membro nao peretence ao projeto");
			throw new Exception("Este membro não está cadastrado neste projeto!");
		} else {
			LoggerProjeto.getInstance().getLogger().severe("Membro nao coordenador");
			throw new Exception("Este coordenador não está cadastrado neste projeto!");
		}
	}
}
