package co.com.ceiba.parqueadero.servicio.util;

public class RespuestaRest<T> {

	public static final int EXITO = 200;
	public static final int ERROR = 600;

	private int codigo;
	private String mensaje;
	private T objeto;

	public RespuestaRest() {
		this.codigo = EXITO;
		this.mensaje = "";
	}
	
	public void obtenerError(String msj) {
		this.codigo = ERROR;
		this.mensaje = msj;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the objeto
	 */
	public T getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto
	 *            the objeto to set
	 */
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

}
