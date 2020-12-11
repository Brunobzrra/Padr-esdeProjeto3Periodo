package model.utilitarios;

import java.time.LocalDateTime;
import java.util.Date;

import ponto.model.projetos.DiaSemana;

public class ConversorDeHoraEDia {
	/**
	 * Este metodo tem duas maneiras de agir, a que o cliente passar um
	 * localDateTime e a outra que recebe null no caso de null ele considera que a
	 * localDateTime deve ser criado aqui no outro caso o ele so cham o metodo de
	 * conversão
	 * 
	 * @param data
	 * @return
	 */
	public static Object[] pegarHoraEDia(LocalDateTime data) {
		if (data == null) {
			String dataQueOPontoFoiBatido = new Date(System.currentTimeMillis()).toString();
			String dia = dataQueOPontoFoiBatido.substring(0, 3);
			return pegarHoraEDia(LocalDateTime.now(), dia);
		} else {
			return pegarHoraEDia(data, data.getDayOfWeek().toString().substring(0, 3));
		}
	}

	/**
	 * Este metodo encapsula o algoritimo responsavel pela conversão de hora e dia
	 * do cliente
	 * 
	 * @param horaExata
	 * @param dia
	 * @return
	 */
	private static Object[] pegarHoraEDia(LocalDateTime data, String dia) {
		DiaSemana diaEmPortugues;
		switch (dia.toString()) {
		case "MON":
			diaEmPortugues = DiaSemana.SEG;
			break;
		case "TUE":
			diaEmPortugues = DiaSemana.TER;
			break;
		case "WED":
			diaEmPortugues = DiaSemana.QUA;
			break;
		case "THU":
			diaEmPortugues = DiaSemana.QUI;
			break;
		case "FRI":
			diaEmPortugues = DiaSemana.SEX;
			break;
		case "SAT":
			diaEmPortugues = DiaSemana.SAB;
			break;
		default:
			diaEmPortugues = DiaSemana.DOM;
			break;
		}
		Object[] resultado = { data, diaEmPortugues };
		return resultado;
	}

	public static DiaSemana diaString(String dia) {
		DiaSemana diaEmPortugues;
		switch (dia.toString()) {
		case "SEG":
			diaEmPortugues = DiaSemana.SEG;
			break;
		case "TER":
			diaEmPortugues = DiaSemana.TER;
			break;
		case "QUA":
			diaEmPortugues = DiaSemana.QUA;
			break;
		case "QUI":
			diaEmPortugues = DiaSemana.QUI;
			break;
		case "SEX":
			diaEmPortugues = DiaSemana.SEX;
			break;
		case "SAB":
			diaEmPortugues = DiaSemana.SAB;
			break;
		default:
			diaEmPortugues = DiaSemana.DOM;
			break;
		}
		return diaEmPortugues;
	}
}