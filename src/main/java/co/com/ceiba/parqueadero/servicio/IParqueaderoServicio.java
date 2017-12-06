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
	ParqueaderoEntidad agregar(ParqueaderoEntidad parqueadero);

	/**
	 * Permite agregar un parqueadero.
	 * 
	 * @param nombre
	 */
	ParqueaderoEntidad agregar(String nombre);

	/**
	 * Permite actualizar un parqueadero..
	 * 
	 * @param parqueadero
	 */
	ParqueaderoEntidad actualizar(ParqueaderoEntidad parqueadero);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	ParqueaderoEntidad encontrarXPk(Long codigo);

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	ParqueaderoEntidad encontrarXNombre(String nombre);

	/**
	 * 
	 * @param codigo
	 */
	void eliminar(Long codigo);

	/**
	 * Permite obtener una lista de todos los parqueaderos disponibles en BD.
	 * 
	 * @return
	 */
	List<ParqueaderoEntidad> listar();

	/**
	 * Permite consultar un parqueadero.
	 * 
	 * @param id
	 * @return
	 */
	RespuestaRest<ParqueaderoEntidad> consultar(String id);

}
