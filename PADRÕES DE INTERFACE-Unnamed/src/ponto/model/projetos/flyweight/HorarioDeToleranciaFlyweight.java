package ponto.model.projetos.flyweight;
/**
 * parte INSTR�NSECO - Flyweight
 * @author Ant�nio Amorim
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
