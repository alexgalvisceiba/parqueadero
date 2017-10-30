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
	public VehiculoEntidad agregar(VehiculoEntidad vehiculo);

	/**
	 * Permite agregar un vehiculo.
	 * 
	 * @param placa
	 * @param cilindraje
	 * @param tipo
	 * @return
	 */
	public VehiculoEntidad agregar(String placa, String cilindraje, String tipo);

	/**
	 * Permite actualizar un vehiculo.
	 * 
	 * @param vehiculo
	 */
	public VehiculoEntidad actualizar(VehiculoEntidad vehiculo);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public VehiculoEntidad encontrarXPk(String codigo);

	/**
	 * 
	 * @param codigo
	 */
	public void eliminar(String codigo);

	/**
	 * Permite listar los vehiculos en BD.
	 * 
	 * @return
	 */
	public List<VehiculoEntidad> listar();

	/**
	 * Permite consultar un tipo de vehiculo.
	 * 
	 * @param id
	 * @return
	 */
	public RespuestaRest<VehiculoEntidad> consultar(String placa);
}
