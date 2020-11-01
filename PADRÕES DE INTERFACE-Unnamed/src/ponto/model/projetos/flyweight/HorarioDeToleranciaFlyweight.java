package ponto.model.projetos.flyweight;

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
