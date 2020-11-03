package model.utilitarios;

import java.time.LocalDateTime;
import java.util.Date;

import ponto.model.projetos.DiaSemana;

public class ConversorDeHoraEDia {
	/**
	 * Este metodo tem duas maneiras de agir, a que o cliente passar um localDateTime e a outra que recebe null
	 * no caso de null ele considera que a localDateTime deve ser criado aqui
	 * no outro caso o ele so cham o metodo de conversão
	 * @param data
	 * @return
	 */
	public static Object[] pegarHoraEDia(LocalDateTime data) {
		if(data==null) {
			String dataQueOPontoFoiBatido = new Date(System.currentTimeMillis()).toString();
			String dia = dataQueOPontoFoiBatido.substring(0, 3);
			String[] horaExataEmtexto = dataQueOPontoFoiBatido.substring(11, 16).split(":");
			long horaExata = Long.parseLong((horaExataEmtexto[0] + horaExataEmtexto[1]));
			return pegarHoraEDia(horaExata, dia);
		}else {
			return pegarHoraEDia(data.getHour(), data.getDayOfWeek().toString().substring(0,3));
		}
	}
	/**
	 * Este metodo encapsula o algoritimo responsavel pela conversão de hora e dia do cliente
	 * @param horaExata
	 * @param dia
	 * @return
	 */
	private static Object[] pegarHoraEDia(long horaExata,String dia) {
		DiaSemana diaEmPortugues;
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