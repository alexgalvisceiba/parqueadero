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
	public TipoVehiculoEntidad agregar(TipoVehiculoEntidad tipo);

	/**
	 * Permite actualizar un tipo de vehiculo.
	 * 
	 * @param tipo
	 */
	public TipoVehiculoEntidad actualizar(TipoVehiculoEntidad tipo);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public TipoVehiculoEntidad encontrarXPk(Long codigo);
	
	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public TipoVehiculoEntidad encontrarXTipo(String tipo);

	/**
	 * 
	 * @param codigo
	 */
	public void eliminar(Long codigo);

	/**
	 * Permite obtener una lista de todos los tipo de vehiculo disponibles en BD.
	 * 
	 * @return
	 */
	public List<TipoVehiculoEntidad> listar();

	/**
	 * Permite consultar un parqueadero.
	 * 
	 * @param id
	 * @return
	 */
	public RespuestaRest<TipoVehiculoEntidad> consultar(String id);
}
