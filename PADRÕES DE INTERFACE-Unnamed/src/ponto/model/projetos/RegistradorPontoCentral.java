package ponto.model.projetos;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.ConversorDeHoraEDia;
import ponto.model.projetos.flyweight.HorarioPrevistoExatoFlyweight;

public class RegistradorPontoCentral extends UnicastRemoteObject implements ServicoRegistradorPontoCentral, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RegistradorPontoCentral() throws RemoteException {
		super();
	}

	public PontoTrabalhado registrarPonto(Projeto projeto, String login) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(login)) {
			throw new Exception("Este membro não estar online!");
		}
		Participacao participacao = null;
		for (ProjetoComponente participa : projeto.getItens()) {
			if (participa instanceof Participacao) {
				participacao = (Participacao) participa;
			}
			LocalDateTime pontoBatidoagora = LocalDateTime.now();
			if (participacao.getMembro().getEmail().equals(login)) {
				int tamanho = participacao.getPontos().size();
				PontoTrabalhado ponto = participacao.getPontos().get(tamanho);
				if (tamanho != 0 && ponto.getDataHoraSaida() != null) {
					ponto.setDataHoraSaida(pontoBatidoagora);
					return ponto;
				} else {
					for (HorarioPrevistoExatoFlyweight horario : participacao.getHorarios()) {
						if (horario.getHoraInicio() == pontoBatidoagora.getHour()
								&& ConversorDeHoraEDia.pegarHoraEDia(pontoBatidoagora)[1] == horario.getDiaSemana()) {
							participacao.adicionarPonto(new PontoTrabalhado(pontoBatidoagora));
							return ponto;
						}
					}
				}
			}
		}
		return null;
	}

	public Set<Projeto> getProjetosAtivos(Membro membro) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		Set<Projeto> projetosAtivos = new HashSet<Projeto>();
		for (ProjetoComponente participa : membro.getParticipacoes()) {
			Participacao participacao = (Participacao) participa;
			Projeto projeto = (Projeto) participacao.getProjetoPai();
			if (projeto.getAtivo()) {
				projetosAtivos.add(projeto);
			}
		}
		return projetosAtivos;
	}

	public void justificarPontoInvalido(PontoTrabalhado ponto, String justificar, String login) {

	}

	public void justificarPontoNaoBatido(PontoTrabalhado ponto, String justificar, String login) {

	}

	public float horasTrabalhadasValidas(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro)
			throws RemoteException, Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		for (ProjetoComponente participacao : membro.getParticipacoes()) {
			Participacao participa = (Participacao) participacao;
			for (PontoTrabalhado ponto : participa.getPontos()) {
				if (ponto.getDataHoraEntrada() == datInicio && ponto.getDataHoraSaida() == dataTermino) {
					return ponto.getHorasTrabalhadas();
				}
			}
		}
		return 0;
	}

	
	public float defcitHoras(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		for (ProjetoComponente participacao : membro.getParticipacoes()) {
			Participacao participa = (Participacao) participacao;
			for (PontoTrabalhado ponto : participa.getPontos()) {
				if (!(ponto.getDataHoraEntrada() == datInicio && ponto.getDataHoraSaida() == dataTermino)) {
					long duracaoPonto = Duration.between(ponto.getDataHoraEntrada(), ponto.getDataHoraSaida())
							.toMillis();
					long duracaoBatida = Duration.between(datInicio, dataTermino).toMillis();
					long defictLong = duracaoBatida - duracaoPonto;
					LocalDateTime defict = LocalDateTime.ofInstant(Instant.ofEpochMilli(defictLong),
							ZoneId.systemDefault());
					return defict.getHour();
				}
			}
		}
		return 0;
	}

	public Set<PontoTrabalhado> getPontosInvalidos(Membro membro) throws RemoteException {
		return null;
	}
}
