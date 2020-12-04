package view.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import model.autenticacao.ContaAutenticacaoProvedorInterno;
import model.autenticacao.ContaEmail;
import model.autenticacao.ContaEmailLivre;
import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.ServicoRegistradorPontoCentral;

/**
 * esta classe atraves do proxy faz uso de todos os metodos do caso de uso onze
 * e treze
 * 
 * @author Antônio Amorim
 *
 */
public class ControllerRegistradorEView {

	private ServicoRegistradorPontoCentral proxy;

	public ControllerRegistradorEView() throws MalformedURLException, RemoteException, NotBoundException {
		proxy = (ServicoRegistradorPontoCentral) Naming.lookup("//localhost/ServicoRemotoPontoTrabalhado");
	}

	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {
		proxy.registrarPonto(nomeDoProjeto, login, senha);
	}

	public StringBuffer defcitHoras(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return proxy.defcitHoras(login, nomeDoProjeto);
	}

	public StringBuffer getPontosValidos(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return proxy.getPontosValidos(login, nomeDoProjeto);
	}

	public Set<PontoTrabalhado> getPontosInvalidos(String login, String nomeDoProjeto) throws Exception {
		return proxy.getPontosInvalidos(login, nomeDoProjeto);
	}

	public String getDetalhes(String login, String nomeDoProjeto) throws Exception {
		return proxy.getDetalhes(login, nomeDoProjeto);
	}

	public StringBuffer horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {
		return proxy.horasTrabalhadasValidas(login, nomeDoProjeto);
	}

	public ArrayList<String> recuperarProjetos(String email) throws RemoteException, Exception {
		return proxy.recuperarProjetos(email);
	}

}