package model.casosDeUsofachadas;

import java.time.LocalDateTime;

import model.autenticacao.Membro;
import persistencia.xml.DAOXMLMembroConta;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.RegistradorPontoCentral;
//Caso de uso 12
public class CasoDeUsoDoze {
	DAOXMLMembroConta daoMembroConta = new DAOXMLMembroConta();

	public void justificarPonto(LocalDateTime dataHoraEntrada, String justificar, long matricula) throws Exception {
		RegistradorPontoCentral registrador = new RegistradorPontoCentral();
		Membro membro = daoMembroConta.recuperarPorIndentificador(matricula);
		registrador.justificarPontoNaoBatido(new PontoTrabalhado(dataHoraEntrada), justificar, membro);
		daoMembroConta.atualizar(membro, membro);
	}
}
