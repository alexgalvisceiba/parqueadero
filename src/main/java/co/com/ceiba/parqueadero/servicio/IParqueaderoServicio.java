package co.com.ceiba.parqueadero.servicio;

import java.util.List;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

public interface IParqueaderoServicio {

	/**
	 * Permite agregar un parqueadero.
	 * 
	 * @param parqueadero
	 */
	public ParqueaderoEntidad agregar(ParqueaderoEntidad parqueadero);
	
	/**
	 * Permite agregar un parqueadero.
	 * 
	 * @param nombre
	 */
	public ParqueaderoEntidad agregar(String nombre);

	/**
	 * Permite actualizar un parqueadero..
	 * 
	 * @param parqueadero
	 */
	public ParqueaderoEntidad actualizar(ParqueaderoEntidad parqueadero);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public ParqueaderoEntidad encontrarXPk(Long codigo);

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public ParqueaderoEntidad encontrarXNombre(String nombre);

	/**
	 * 
	 * @param codigo
	 */
	public void eliminar(Long codigo);

	/**
	 * Permite obtener una lista de todos los parqueaderos disponibles en BD.
	 * 
	 * @return
	 */
	public List<ParqueaderoEntidad> listar();

	/**
	 * Permite consultar un parqueadero.
	 * 
	 * @param id
	 * @return
	 */
	public RespuestaRest<ParqueaderoEntidad> consultar(String id);

}
