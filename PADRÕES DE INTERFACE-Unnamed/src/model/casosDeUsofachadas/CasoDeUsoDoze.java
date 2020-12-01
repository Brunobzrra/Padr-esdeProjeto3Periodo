package model.casosDeUsofachadas;

import java.time.LocalDateTime;
import java.util.logging.Level;

import model.autenticacao.Membro;
import model.utilitarios.LoggerProjeto;
import persistencia.xml.DAOXMLMembroConta;
import ponto.model.projetos.PontoTrabalhado;
import ponto.model.projetos.RegistradorPontoCentral;

//Caso de uso 12
public class CasoDeUsoDoze {
	DAOXMLMembroConta daoMembroConta = new DAOXMLMembroConta();

	public void justificarPonto(LocalDateTime dataHoraEntrada, String justificar, long matricula) throws Exception {
		LoggerProjeto.getInstance().getLogger().log(Level.FINE, "Justificando um ponto");
		RegistradorPontoCentral registrador = new RegistradorPontoCentral();
		LoggerProjeto.getInstance().getLogger().info("Tentando justificar ponto nao batido");
		Membro membro = daoMembroConta.recuperarPorIndentificador(matricula);
		registrador.justificarPontoNaoBatido(new PontoTrabalhado(dataHoraEntrada), justificar, membro);
		daoMembroConta.atualizar(membro, membro);
		LoggerProjeto.getInstance().getLogger().warning("ponto justificado com sucesso");
	}
}
