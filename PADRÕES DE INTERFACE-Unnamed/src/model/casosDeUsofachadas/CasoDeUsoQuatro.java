package model.casosDeUsofachadas;

import java.util.Date;

import model.projetos.Edital;
import model.projetos.Projeto;
import model.projetos.ProjetoComponente;
import persistencia.xml.DAOXMLEdital;

//caso de uso 4
public class CasoDeUsoQuatro {

	private DAOXMLEdital daoEdital = new DAOXMLEdital();

	public CasoDeUsoQuatro(long matricula, Date dataInicio, Date dataTermino, float aporteCusteioMensalReais,
			short qtdMesesCusteados, short qtdMesesPagos, boolean coordenador) throws Exception {
		super(AutenticadorDePersistencia.verificarMembro(matricula),
				AutenticadorDePersistencia.criarParticipacao(matricula, dataInicio, dataTermino,
						aporteCusteioMensalReais, qtdMesesCusteados, qtdMesesPagos, coordenador));
	}

	public void adcionarEdital(String nome) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoEdital.criar(nome))
				throw new Exception("Este grupo ja existe!");
		}
		System.out.println("Edital adcionado com sucesso!");
	}

	public void atualizarEdital(String nome, Edital editalSubistituto) throws Exception {
		// TODO Auto-generated method stub
		if (super.getMembro().isAdministrador()) {
			if (!daoEdital.atualizar(AutenticadorDePersistencia.verificarEdital(nome), editalSubistituto)) {
				throw new Exception("Este edital não existe!");
			}
		}
		System.out.println("Edital atualizado com sucesso!");
	}

	public void removerEdital(String nome) throws Exception {
		// TODO Auto-generated method stub
		// decidimos que seria melhor a fachada fazer o uso do instanceof
		Edital edital = AutenticadorDePersistencia.verificarEdital(nome);
		if (super.getMembro().isAdministrador()) {
			for (ProjetoComponente projetoComponente : edital.getItens()) {
				if (projetoComponente instanceof Projeto) {
					throw new Exception("Este grupo contem projetos atribuidos a ele!");
				}
			}
			daoEdital.remover(edital);
			System.out.println("Edital removido com sucesso!");
		}
	}
}
