package view.abstract_factory;

/**
 * Interface uniformizadora do produto fornecido, que eh a
 * TelaConfiguracaoAdmin fornecendo os metodos que 
 * devem ser implementados
 * 
 * @author bruno
 */

public interface InterfaceTelaConfiguracaoAdmin {

	/**
	 * metodo uniformizado, que provem a unica funcionalidade, que eh
	 * habilitar/desabilitar um administrador
	 * 
	 * @param matriculaAdministrador, matricula
	 */
	public abstract void habilitarAdministrador(long matriculaAdministrador, long matricula) throws Exception;
}
