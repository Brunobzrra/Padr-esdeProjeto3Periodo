package ponto.model.projetos;

import java.rmi.Remote;
import java.util.Set;

public interface ServicoRemotoPontoTrabalhado extends Remote{
	public abstract float horasTrabalhadasValidas(long datInicio, long dataTermino, String login);
	public abstract float defcitHoras(long datInicio, long dataTermino, String login);
	public abstract Set<PontoTrabalhado> getPontosInvalidos(String login);
}
