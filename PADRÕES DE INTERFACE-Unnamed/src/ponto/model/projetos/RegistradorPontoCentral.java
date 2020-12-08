package ponto.model.projetos;

import java.io.Serializable;
import java.rmi.RemoteException;
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
import model.projetos.TipoProjetoComponente;
import model.utilitarios.ConversorDeHoraEDia;

/**
 * Classe que registra um ponto para um determinado membro, seguindo a logica de
 * negocio.
 * 
 * @author bruno
 * 
 */
public class RegistradorPontoCentral implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este metodo e responsavel por testar cadastrar um ponto na sua hora inicial
	 * ou se o ponto de entrada ja foi batido ele bate o ponto de saida
	 * 
	 * @param projeto, membro
	 */
	public PontoTrabalhado registrarPonto(Projeto projeto, Membro membro) throws Exception {
		if (!RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não esta online!");
		}
		Participacao participacao = null;
		for (ProjetoComponente participa : projeto.getItens()) {
			if (participa.getTipo() == TipoProjetoComponente.PARTICIPACAO) {
				participacao = (Participacao) participa;
			}
			LocalDateTime pontoBatidoagora = LocalDateTime.now();
			if (participacao.getMembro().getEmail().equals(membro.getEmail())) {
				if (!participacao.getPontos().isEmpty()) {
					for (PontoTrabalhado p : participacao.getPontos()) {
						if (p.getDataHoraSaida() == null) {
							p.setDataHoraSaida(pontoBatidoagora);
							return p;
						} else {
							throw new Exception("Ponto ja batido");
						}
					}
				} else {
					participacao.adicionarPonto(new PontoTrabalhado(pontoBatidoagora));
					return participacao.getPontos().get(0);
				}
			}
		}
		throw new Exception("Dia/Horario nao respeitado");
	}

	/**
	 * este metodo testa e retorna objetos ativos
	 * 
	 * @param membro
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Este metodo justifica um ponta invalido
	 * 
	 * @param ponto
	 * @param justificar
	 * @param membro
	 * @throws Exception
	 */
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

	/**
	 * Este metodo justifica quando um ponto não foi batido nio horario previsto
	 * 
	 * @param ponto
	 * @param justificar
	 * @param membro
	 * @throws Exception
	 */
	public void justificarPontoNaoBatido(PontoTrabalhado ponto, String justificar, Membro membro) throws Exception {
		if (!RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
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

	/**
	 * Metodo ressponsavel por pegar todos a horas trabalhadas que são validas do
	 * membro que é passado
	 * 
	 * @param datInicio
	 * @param dataTermino
	 * @param membro
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String horasTrabalhadasValidas(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro)
			throws RemoteException, Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(membro.getEmail())) {
			throw new Exception("Este membro não estar online!");
		}
		for (ProjetoComponente participacao : membro.getParticipacoes()) {
			Participacao participa = (Participacao) participacao;
			for (PontoTrabalhado ponto : participa.getPontos()) {
				if (ponto.getDataHoraEntrada().equals(datInicio) && ponto.getDataHoraSaida().equals(dataTermino)) {
					return ponto.getHorasTrabalhadas();
				}
			}
		}
		throw new Exception("Sem horas registradas");
	}

	/**
	 * Este metodo pega o deficit de horas do membro passado no parametro
	 * 
	 * @param datInicio
	 * @param dataTermino
	 * @param membro
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * este metodo pega todos os pontos invalidos de acordo com a cadeia do padrão
	 * chain of responsability escolhida aqui
	 * 
	 * @param membro
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
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
