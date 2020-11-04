package ponto.model.projetos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;
import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.ConversorDeHoraEDia;
import ponto.model.projetos.flyweight.HorarioPrevistoExatoFlyweight;

public class RegistradorPontoCentral extends UnicastRemoteObject implements ServicoRemotoPontoTrabalhado {
	private static final long serialVersionUID = 1L;

	protected RegistradorPontoCentral() throws RemoteException {
		super();
	}

	public boolean registrarPonto(Projeto projeto, String login) throws Exception {
		if (RegistradorSessaoLogin.getInstance().isOline(login)) {
			throw new Exception("Este membro não estar online!");
		}
		Participacao participacao = null;
		for (ProjetoComponente participa : projeto.getItens()) {
			if(participa instanceof Participacao) {
				 participacao = (Participacao) participa;
			}
			LocalDateTime pontoBatidoagora = LocalDateTime.now();
			if (participacao.getMembro().getEmail().equals(login)) {
				int tamanho = participacao.getPontos().size();
				PontoTrabalhado ponto = participacao.getPontos().get(tamanho);
				if (tamanho != 0 && ponto.getDataHoraSaida() != null) {
					ponto.setDataHoraSaida(pontoBatidoagora);
					return true;
				} else {
					for (HorarioPrevistoExatoFlyweight horario : participacao.getHorarios()) {
						if (horario.getHoraInicio() == pontoBatidoagora.getHour() && ConversorDeHoraEDia.pegarHoraEDia(pontoBatidoagora)[1] == horario.getDiaSemana()) {
							participacao.adicionarPonto(new PontoTrabalhado(pontoBatidoagora));
							return true;
						}
					}
				}
			}
		}
		return false;
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
		// TODO Auto-generated method stub

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

	@Override
	public float defcitHoras(LocalDateTime datInicio, LocalDateTime dataTermino, Membro membro) throws RemoteException {
		return 0;
	}

	@Override
	public Set<PontoTrabalhado> getPontosInvalidos(Membro membro) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
