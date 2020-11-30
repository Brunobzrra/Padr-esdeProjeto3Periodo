package model.casosDeUsofachadas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;

import model.autenticacao.Membro;
import model.autenticacao.RegistradorSessaoLogin;

import model.projetos.Participacao;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import model.utilitarios.ConversorDeHoraEDia;
import model.utilitarios.LoggerProjeto;
import model.utilitarios.PegadorDeEmailDoDaoMembro;
import persistencia.xml.DAOXMLMembroConta;
import persistencia.xml.DAOXMLProjetoParticipacao;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.RegistradorPontoCentral;
import ponto.model.projetos.ServicoRegistradorPontoCentral;

//caso de uso 11 e 13
public class CasoDeUsoOnzeETreze extends UnicastRemoteObject implements ServicoRegistradorPontoCentral {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DAOXMLMembroConta daMembro = new DAOXMLMembroConta();
	private static DAOXMLProjetoParticipacao daoProjetoParticipacao = new DAOXMLProjetoParticipacao();
	private static RegistradorPontoCentral registrador = new RegistradorPontoCentral();
	private static CasoDeUsoOnzeETreze fachadaSingleton;

	private CasoDeUsoOnzeETreze() throws Exception {
		super();
	}

	// metodos usados pela view
	public static CasoDeUsoOnzeETreze getInstance() throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Obtendo instancia unica");
		if (fachadaSingleton == null) {
			fachadaSingleton = new CasoDeUsoOnzeETreze();
		}
		return fachadaSingleton;
	}

	public ArrayList<String> recuperarProjetos(String email) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Recuperando projeto");
		ArrayList<ProjetoComponente> participacao = daMembro.recuperarPorEmail(email).getParticipacoes();
		ArrayList<String> nomes = new ArrayList<String>();
		for (ProjetoComponente participa : participacao) {
			if (participa.getProjetoPai().getAtivo()) {
				LoggerProjeto.getInstance().getLogger()
						.warning("Nome projeto encontrado: " + participa.getProjetoPai().getNome());
				nomes.add(participa.getProjetoPai().getNome());
			}
		}
		LoggerProjeto.getInstance().getLogger().warning("Nomes recuperados com sucesso");
		return nomes;
	}

	public Set<PontoTrabalhado> getPontosInvalidos(String login, String nomeDoProjeto) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Obtendo pontos invalidos");
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		LoggerProjeto.getInstance().getLogger().info("Selecionando o membro");
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					LoggerProjeto.getInstance().getLogger().warning("Retornando os pontos invalidos");
					return registrador.getPontosInvalidos((Membro) membroDoFor);
				}

			}
		}
		LoggerProjeto.getInstance().getLogger().severe("Nao foi possivel retornar os pontos invalidos");
		return null;
	}

	public String getDetalhes(String login, String nomeDoProjeto) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Fornecendo sobre um projeto/participacao");
		String retorno = "Defict de Horas: " + defcitHoras(login, nomeDoProjeto) + "\n Horas trabalhadas validas: "
				+ horasTrabalhadasValidas(login, nomeDoProjeto) + "\nPontos invalidos: ";

		Set<PontoTrabalhado> pontos = getPontosInvalidos(login, nomeDoProjeto);
		PontoTrabalhado[] recuperados = (PontoTrabalhado[]) pontos.toArray();
		LoggerProjeto.getInstance().getLogger().info("Preenchendo o retorno dos detalhes");
		for (int i = 0; i < recuperados.length; i++) {
			retorno += "\n" + " Hora e dia de entrada: "
					+ ConversorDeHoraEDia.pegarHoraEDia(recuperados[i].getDataHoraEntrada()) + " Hora e dia de saida: "
					+ ConversorDeHoraEDia.pegarHoraEDia(recuperados[i].getDataHoraSaida()) + " justificativa: "
					+ recuperados[i].getJustificativa() + "\n";
		}
		LoggerProjeto.getInstance().getLogger().warning("Retorno preenchido com sucesso");
		return retorno;
	}

	// Metodo caso de uso 11
	public void registrarPonto(String nomeDoProjeto, String login, String senha) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Registrando um ponto");
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		LoggerProjeto.getInstance().getLogger().info("Recuperando projeto");
		Projeto projetoAux = projeto;
		LoggerProjeto.getInstance().getLogger().info("Verificando se foi recuperado");
		if (projeto != null) {
			LoggerProjeto.getInstance().getLogger().info("Autenticando a senha");
			if (senha.length() == 0) {
				LoggerProjeto.getInstance().getLogger().severe("Senha nao encontrada");
				throw new Exception("Digite uma senha!");
			}
			LoggerProjeto.getInstance().getLogger().info("Recuperando membro");
			Membro m = daMembro.recuperarPorEmail(login);
			RegistradorSessaoLogin.getInstance().registrarOline(m);
			Membro aux = m;
			LoggerProjeto.getInstance().getLogger().info("Validando senha");
			if (m.getSenha().equals(senha)) {
				LoggerProjeto.getInstance().getLogger().info("Registrando ponto trabalhado");
				PontoTrabalhado ponto = registrador.registrarPonto((Projeto) projeto, m);
				if (ponto != null) {
					LoggerProjeto.getInstance().getLogger().warning("Inserindo ponto");
					daMembro.atualizar(aux, m);
					daoProjetoParticipacao.atualizar(projetoAux, projeto);
					System.out.println("ok");
				} else {
					LoggerProjeto.getInstance().getLogger().severe("O horario previsto nao foi responsavel");
					throw new Exception("Não existe horario previsto para isso!");
				}
			} else {
				throw new Exception("Senha incorreta!");
			}
		} else
			LoggerProjeto.getInstance().getLogger().severe("Projeto nao encontrado");
		throw new Exception("Projeto não existente!");
	}

	// Metodos do caso de uso 13
	public StringBuffer defcitHoras(String login, String nomeDoProjeto) throws RemoteException, Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "calculando o defict");
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Deficit de Horas\n");
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					LoggerProjeto.getInstance().getLogger().info("Calculando o defict");
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						texto.append("O membro " + participacaoDoFor.getMembro().getNome() + " teve um deficit de\n");
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							texto.append(registrador.defcitHoras(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor) + "\n");
						}
					}
				}

			}
		}
		LoggerProjeto.getInstance().getLogger().warning("Defict calculado");
		;
		return texto;
	}

	public StringBuffer getPontosValidos(String login, String nomeDoProjeto) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Capturando os pontos validos");
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Ponto inalidas\n");

		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			LoggerProjeto.getInstance().getLogger().info("Armazenando os pedidos em StringBuffer");
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				Membro membro = (Membro) membroDoFor;
				if ((membro).getEmail().equalsIgnoreCase(login)) {
					texto.append("Membro " + membroDoFor.getNome() + " seus pontos validos /n"
							+ membro.getPontosValidos().toString());
					LoggerProjeto.getInstance().getLogger().info("Pedidos obtidos");

				}

			}
		}
		return texto;
	}

	public StringBuffer horasTrabalhadasValidas(String login, String nomeDoProjeto) throws RemoteException, Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Verificando as horas trabalhadas");		
		Projeto projeto = daoProjetoParticipacao.recuperarPorIndentificador(nomeDoProjeto);
		StringBuffer texto = new StringBuffer("Horas Trabalhadas Validas\n");
		LoggerProjeto.getInstance().getLogger().info("Buscando libros...");
		for (ProjetoComponente membroDoFor : projeto.getItens()) {
			if (membroDoFor.getTipo() == TipoProjetoComponente.MEMBRO) {
				if (((Membro) membroDoFor).getEmail().equalsIgnoreCase(login)) {
					for (Participacao participacaoDoFor : PegadorDeEmailDoDaoMembro
							.recuperarParticipacao((Membro) membroDoFor)) {
						texto.append("O membro " + participacaoDoFor.getMembro().getNome() + " trabalhou\n");
						for (PontoTrabalhado pontoDoFor : participacaoDoFor.getPontos()) {
							texto.append(registrador.horasTrabalhadasValidas(pontoDoFor.getDataHoraEntrada(),
									pontoDoFor.getDataHoraSaida(), (Membro) membroDoFor) + "\n");
						}
					}
				}

			}
		}
		return texto;

	}

}
