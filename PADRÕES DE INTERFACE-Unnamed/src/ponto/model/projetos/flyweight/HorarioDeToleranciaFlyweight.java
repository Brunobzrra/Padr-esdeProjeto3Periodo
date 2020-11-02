package ponto.model.projetos.flyweight;
/**
 * parte INSTRÍNSECO - Flyweight
 * @author Antônio Amorim
 *
 */
public class HorarioDeToleranciaFlyweight implements HorarioPrevistoFlyweight{
	private short toleranciaMinutos;
	
	public HorarioDeToleranciaFlyweight(short toleranciaMinutos) {
		super();
		this.toleranciaMinutos = toleranciaMinutos;
	}

	public short getToleranciaMinutos() {
		return toleranciaMinutos;
	}

}
