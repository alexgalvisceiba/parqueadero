package co.com.ceiba.parqueadero.servicio;

import java.util.List;

import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

public interface ITipoVehiculoServicio {

	/**
	 * Permite agregar un tipo de vehiculo.
	 * 
	 * @param tipo
	 */
	TipoVehiculoEntidad agregar(TipoVehiculoEntidad tipo);

	/**
	 * Permite actualizar un tipo de vehiculo.
	 * 
	 * @param tipo
	 */
	TipoVehiculoEntidad actualizar(TipoVehiculoEntidad tipo);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	TipoVehiculoEntidad encontrarXPk(Long codigo);

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	TipoVehiculoEntidad encontrarXTipo(String tipo);

	/**
	 * 
	 * @param codigo
	 */
	void eliminar(Long codigo);

	/**
	 * Permite obtener una lista de todos los tipo de vehiculo disponibles en BD.
	 * 
	 * @return
	 */
	List<TipoVehiculoEntidad> listar();

	/**
	 * Permite consultar un parqueadero.
	 * 
	 * @param id
	 * @return
	 */
	RespuestaRest<TipoVehiculoEntidad> consultar(String id);
}
