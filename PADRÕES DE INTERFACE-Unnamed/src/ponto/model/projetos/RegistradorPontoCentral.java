package ponto.model.projetos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.chainOfResponsibility.AvaliadorDeRegistro;
import model.chainOfResponsibility.AvaliadorPontoSemEntradaeOuSaida;
import model.chainOfResponsibility.AvaliadorPontosComIntervalosConflitantes;
import model.chainOfResponsibility.AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos;
import model.chainOfResponsibility.AvaliadorPontosInvalidosComJustificativaNaoAceita;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.ConversorDeHoraEDia;

public class RegistradorPontoCentral extends UnicastRemoteObject implements ServicoRegistradorPontoCentral {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185696354619610643L;

	protected RegistradorPontoCentral() throws RemoteException {
		super();
	}

	public PontoTrabalhado registrarPonto(Projeto projeto, Membro membro) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		Participacao participacao = null;
		for (ProjetoComponente participa : projeto.getItens()) {
			if (participa instanceof Participacao) {
				participacao = (Participacao) participa;
			}
			LocalDateTime pontoBatidoagora = LocalDateTime.now();
			if (participacao.getMembro().getEmail().equals(membro.getEmail())) {
				int tamanho = participacao.getPontos().size();
				PontoTrabalhado ponto = null;
				if (tamanho != 0) {
					ponto = participacao.getPontos().get(tamanho);
					if (ponto.getDataHoraSaida() != null) {
						ponto.setDataHoraSaida(pontoBatidoagora);
						return ponto;
					}
				} else {
					for (HorarioPrevisto horario : participacao.getHorarios()) {
						if (horario.getHoraInicio() == pontoBatidoagora.getHour()
								&& ConversorDeHoraEDia.pegarHoraEDia(pontoBatidoagora)[1] == horario.getDiaSemana()) {
							participacao.adicionarPonto(new PontoTrabalhado(pontoBatidoagora));
							System.out.println("Ok");
							return participacao.getPontos().get(0);
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

	public void justificarPontoInvalido(PontoTrabalhado ponto, String justificar, Membro membro) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		Set<PontoTrabalhado> pontosInvalidos = getPontosInvalidos(membro);
		Iterator<PontoTrabalhado> pontos = pontosInvalidos.iterator();
		PontoTrabalhado pontoJustificado = null;
		while (pontos.hasNext()) {
			if (pontos.next().equals(ponto)) {
				pontoJustificado = pontos.next();
			}
		}
		if (pontoJustificado == null) {
			throw new Exception("Este ponto não existe!");
		}
		pontoJustificado.setJustificativa(justificar);
	}

	public void justificarPontoNaoBatido(PontoTrabalhado ponto, String justificar, Membro membro) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		Set<PontoTrabalhado> pontosInvalidos = new AvaliadorPontoSemEntradaeOuSaida(null).getPontosInvalidos(membro);
		Iterator<PontoTrabalhado> pontos = pontosInvalidos.iterator();
		PontoTrabalhado pontoJustificado = null;
		while (pontos.hasNext()) {
			if (pontos.next().equals(ponto)) {
				pontoJustificado = pontos.next();
			}
		}
		if (pontoJustificado == null) {
			throw new Exception("Este ponto não existe!");
		}
		pontoJustificado.setJustificativa(justificar);
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

	public Set<PontoTrabalhado> getPontosInvalidos(Membro membro) throws RemoteException, Exception {
		if (!RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		AvaliadorDeRegistro cadeia = new AvaliadorPontosComIntervalosConflitantes(
				new AvaliadorPontosForaParticipacaoPrevisaoToleranciaEmMinutos(new AvaliadorPontoSemEntradaeOuSaida(
						new AvaliadorPontosInvalidosComJustificativaNaoAceita(null))));
		return cadeia.getPontosInvalidos(membro);
	}
}
