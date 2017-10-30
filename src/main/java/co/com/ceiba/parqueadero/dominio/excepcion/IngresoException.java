package co.com.ceiba.parqueadero.dominio.excepcion;

/**
 * Define excepciones que son manejadas como reglas de negocio donde se pasará
 * el mensaje correspondiente.
 * 
 * @author Ceiba Software <BR>
 *
 */
public class IngresoException extends RuntimeException {

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase.
	 * 
	 * @param message
	 *            el mensaje de error de acuerdo a reglas de negocio.
	 */
	public IngresoException(String message) {
		super(message);
	}
}
