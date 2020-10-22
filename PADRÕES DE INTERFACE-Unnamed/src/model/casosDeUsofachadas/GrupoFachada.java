package model.casosDeUsofachadas;

import java.util.Date;

import model.projetos.Grupo;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import model.utilitarios.AutenticadorDePersistencia;
import persistenia.xml.DAOXMLGrupo;

//caso de uso 3
public class GrupoFachada extends ProjetoFachada {
	private DAOXMLGrupo daoGrupo = new DAOXMLGrupo();

	public GrupoFachada(long matricula, Date dataInicio, Date dataTermino, float aporteCusteioMensalReais,
			short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador) throws Exception {
		super(AutenticadorDePersistencia.verificarMembro(matricula),
				AutenticadorDePersistencia.criarParticipacao(matricula, dataInicio, dataTermino,
						aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos, coordenador));
		// TODO Auto-generated constructor stub
	}

	public void adcionarGrupo(String linkCNPq) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoGrupo.criar(AutenticadorDePersistencia.verificarGrupo(linkCNPq)))
				throw new Exception("Este grupo ja existe!");
		}
		System.out.println("Grupo adcionado com sucesso!");
	}

	public void atualizarGrupo(String linkCNPq, Grupo grupoSubistituto) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoGrupo.atualizar(AutenticadorDePersistencia.verificarGrupo(linkCNPq), grupoSubistituto)) {
				throw new Exception("Este grupo não existe!");
			}
		}
		System.out.println("Grupo atualizado com sucesso!");
	}

	public void removerGrupo(String linkCNPq) throws Exception {
		// TODO Auto-generated method stub
		// decidimos que seria melhor a fachada fazer o uso do instanceof
		Grupo grupo=AutenticadorDePersistencia.verificarGrupo(linkCNPq);
		if (super.getMembro().isAdministrador()) {
			for (ProjetoComponente projetoComponente : grupo.getItens()) {
				if (projetoComponente instanceof Projeto) {
					throw new Exception("Este grupo contem projetos atribuidos a ele!");
				}
			}
			daoGrupo.remover(grupo);
		}
		System.out.println("Grupo removido com sucesso!");
	}
}
