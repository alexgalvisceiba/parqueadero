package co.com.ceiba.parqueadero.servicio;

import java.util.List;

import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

public interface IVehiculoServicio {

	/**
	 * Permite agregar un vehiculo.
	 * 
	 * @param vehiculo
	 */
	VehiculoEntidad agregar(VehiculoEntidad vehiculo);

	/**
	 * Permite agregar un vehiculo.
	 * 
	 * @param placa
	 * @param cilindraje
	 * @param tipo
	 * @return
	 */
	VehiculoEntidad agregar(String placa, String cilindraje, String tipo);

	/**
	 * Permite actualizar un vehiculo.
	 * 
	 * @param vehiculo
	 */
	VehiculoEntidad actualizar(VehiculoEntidad vehiculo);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	VehiculoEntidad encontrarXPk(String codigo);

	/**
	 * 
	 * @param codigo
	 */
	void eliminar(String codigo);

	/**
	 * Permite listar los vehiculos en BD.
	 * 
	 * @return
	 */
	List<VehiculoEntidad> listar();

	/**
	 * Permite consultar un tipo de vehiculo.
	 * 
	 * @param id
	 * @return
	 */
	RespuestaRest<VehiculoEntidad> consultar(String placa);
}
