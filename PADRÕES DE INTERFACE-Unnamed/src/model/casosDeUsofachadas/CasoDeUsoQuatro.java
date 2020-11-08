package model.casosDeUsofachadas;

import java.util.Date;

import model.autenticacao.Membro;
import model.projetos.Edital;
import model.projetos.ProjetoComponente;
import model.projetos.TipoProjetoComponente;
import persistencia.xml.DAOXMLEdital;
import persistencia.xml.DAOXMLMembroConta;

//caso de uso 4
public class CasoDeUsoQuatro {

	private DAOXMLEdital daoEdital = new DAOXMLEdital();
	private DAOXMLMembroConta daoMembro = new DAOXMLMembroConta();

	public void adcionarEdital(String nomeEdital, Date dataInicio, Date dataTermino, long matricula) throws Exception {
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		if (membro != null) {
			if (!daoEdital.criar(nomeEdital, dataTermino, dataTermino))
				throw new Exception("Este grupo ja existe!");
		}
		System.out.println("Edital adcionado com sucesso!");
	}

	public void atualizarEdital(String nomeEdital,String novoNome,Date dataInicio,Date dataTermino, Object[] valores, long matricula)
			throws Exception {
		String[] atributo = { "nome" };
		Object[] valor = { nomeEdital };
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		Edital editalAntigo = daoEdital.consultarAnd(atributo, valor).iterator().next();
		Edital editalAtualizado = editalAntigo;
		editalAtualizado.setNome(novoNome);
		editalAtualizado.setDataInicio(dataInicio);
		editalAtualizado.setDataTermino(dataTermino);
		if (membro != null) {
			if (!daoEdital.atualizar(editalAntigo, editalAtualizado)) {
				throw new Exception("Este edital não existe!");
			}
		}
		System.out.println("Edital atualizado com sucesso!");
	}

	public void removerEdital(String nomeEdital, long matricula) throws Exception {
		// TODO Auto-generated method stub
		Membro membro = daoMembro.isAdmimPelaMatricula(matricula);
		String[] atributo = { "nome" };
		Object[] valor = { nomeEdital };
		Edital edital = daoEdital.consultarAnd(atributo, valor).iterator().next();
		if (membro != null) {
			for (ProjetoComponente projetoComponente : edital.getItens()) {
				if (projetoComponente.getTipo() == TipoProjetoComponente.PROJETO) {
					throw new Exception("Este grupo contem projetos atribuidos a ele!");
				}
			}
			daoEdital.remover(edital);
			System.out.println("Edital removido com sucesso!");
		}
	}
}
