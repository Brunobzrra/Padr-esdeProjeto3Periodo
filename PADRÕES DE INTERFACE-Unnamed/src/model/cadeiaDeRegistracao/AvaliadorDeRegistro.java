package model.cadeiaDeRegistracao;

import java.util.Date;

import model.projetos.Participacao;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.PontoTrabalho;

public abstract class AvaliadorDeRegistro {
	private AvaliadorDeRegistro proximo;
	private Participacao participacao;

	public abstract boolean justificarPontoInvalido(PontoTrabalho ponto, String justificativa, String login);

	public AvaliadorDeRegistro getProximo() {
		return proximo;
	}

	public void setProximo(AvaliadorDeRegistro proximo) {
		this.proximo = proximo;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	protected Object[] pegarHoraEDia() {
		// TODO Auto-generated method stub
		String dataQueOPontoFoiBatido = new Date(System.currentTimeMillis()).toString();
		String dia = dataQueOPontoFoiBatido.substring(0, 3);
		DiaSemana diaEmPortugues;
		String[] horaExataEmtexto = dataQueOPontoFoiBatido.substring(11, 16).split(":");
		long horaExata = Long.parseLong((horaExataEmtexto[0] + horaExataEmtexto[1]));
		switch (dia) {
		case "Mon":
			diaEmPortugues = DiaSemana.SEG;
			break;
		case "Tue":
			diaEmPortugues = DiaSemana.TER;
			break;
		case "Wed":
			diaEmPortugues = DiaSemana.QUA;
			break;
		case "Thu":
			diaEmPortugues = DiaSemana.QUI;
			break;
		case "Fri":
			diaEmPortugues = DiaSemana.SEX;
			break;
		case "Sat":
			diaEmPortugues = DiaSemana.SAB;
			break;
		default:
			diaEmPortugues = DiaSemana.DOM;
			break;
		}
		Object[] resultado = { horaExata, diaEmPortugues };
		return resultado;
	}
}
