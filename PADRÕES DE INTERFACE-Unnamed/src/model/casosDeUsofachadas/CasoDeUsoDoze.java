package model.casosDeUsofachadas;

import java.time.LocalDateTime;

import model.autenticacao.Membro;
import persistencia.xml.DAOXMLMembroConta;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.RegistradorPontoCentral;

public class CasoDeUsoDoze {
	DAOXMLMembroConta daoMembroConta= new DAOXMLMembroConta();
	public void justifiarPonto(LocalDateTime dataHoraEntrada, String justificar, long matricula) throws Exception {
		RegistradorPontoCentral registrador;
		Membro membro=daoMembroConta.recuperarPorIndentificador(matricula);
		registrador.justificarPontoNaoBatido(new PontoTrabalhado(dataHoraEntrada), justificar, membro);
		daoMembroConta.atualizar(membro, membro);
	}
}
