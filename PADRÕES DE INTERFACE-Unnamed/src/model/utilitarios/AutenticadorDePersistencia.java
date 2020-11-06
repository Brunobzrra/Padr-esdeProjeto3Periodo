//package model.utilitarios;
/**
 * esta classe serve para que o clinte (main) não precise instaciar nada, apenas acesse tudo atraves dos tipo primitivos
 * @author Antônio Amorim
 *
 */

//import java.util.Date;
//import java.util.Set;
//
//import model.autenticacao.Membro;
//import model.projetos.Edital;
//import model.projetos.Grupo;
//import model.projetos.Participacao;
//import model.projetos.Projeto;
//import persistenia.xml.DAOXMLEdital;
//import persistenia.xml.DAOXMLGrupo;
//import persistenia.xml.DAOXMLMembroConta;
//import persistenia.xml.DAOXMLProjetoParticipacao;
//
//public class AutenticadorDePersistencia {
//	private static DAOXMLEdital daoedital = new DAOXMLEdital();
//	private static DAOXMLGrupo daoGrupo = new DAOXMLGrupo();
//	private static DAOXMLProjetoParticipacao daoProjeto = new DAOXMLProjetoParticipacao();
//	private static DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();
//
//	public static Participacao criarParticipacao(long matricula, Date dataInicio, Date dataTermino,
//			float aporteCusteioMensalReais, short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador) throws Exception {
//		Membro membro = verificarMembro(matricula);
//		Participacao participacao= new Participacao(dataInicio, dataTermino, aporteCusteioMensalReais, qtdMesesCusteados,
//				qtdMesesPagos, coordenador);
//		membro.adicionar(participacao);
//		return participacao;
//	}
//
//	public static Membro verificarMembro(long matricula) throws Exception {
//		String[] atributos = { "matricula" };
//		Object[] valores = { matricula };
//		Membro membro = null;
//		Set<Membro> membros = daoMembro.consultarAnd(atributos, valores);
//		for (Membro membroDaVez : membros) {
//			membro = membroDaVez;
//			if (membroDaVez == null) {
//				throw new Exception("Membro não existe!");
//			}
//		}
//		return membro;
//	}
//
//	public static Edital verificarEdital(String nome) throws Exception {
//		String[] atributos = { "nome" };
//		Object[] valores = { nome };
//		Edital edital = null;
//		Set<Edital> editais = daoedital.consultarAnd(atributos, valores);
//		for (Edital editalDaVez : editais) {
//			edital = editalDaVez;
//			if (editalDaVez == null) {
//				throw new Exception("Edital não existe!");
//			}
//		}
//		return edital;
//	}
//
//	public static Projeto verificarProjeto(String nome) throws Exception {
//		String[] atributos = { "nome" };
//		Object[] valores = { nome };
//		Projeto projeto = null;
//		Set<Projeto> projetos = daoProjeto.consultarAnd(atributos, valores);
//		for (Projeto projetoDaVez : projetos) {
//			projeto = projetoDaVez;
//			if (projetoDaVez == null) {
//				throw new Exception("Projeto não existe!");
//			}
//		}
//		return projeto;
//	}
//
//	public static Grupo verificarGrupo(String linkCNPq) throws Exception {
//		String[] atributos = { "linkCNPq" };
//		Object[] valores = { linkCNPq };
//		Grupo grupo = null;
//		Set<Grupo> grupos = daoGrupo.consultarAnd(atributos, valores);
//		for (Grupo gruposDaVez : grupos) {
//			grupo = gruposDaVez;
//			if (gruposDaVez == null) {
//				throw new Exception("grupo não existe!");
//			}
//		}
//		return grupo;
//	}
//
//}
