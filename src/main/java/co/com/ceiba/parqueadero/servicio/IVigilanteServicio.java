package co.com.ceiba.parqueadero.servicio;

import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

public interface IVigilanteServicio {

	/**
	 * Permite registrar un ingreso de vehiculo común.
	 * 
	 * @param nombreParqueadero
	 * @param placa
	 * @param cilindraje
	 * @param tipo
	 * @return
	 */
	Boolean registrarIngreso(String nombreParqueadero, String placa, String cilindraje, String tipo);

	/**
	 * Expone el servicio rest simple para mapeo customizado.
	 * 
	 * @param nombreParqueadero
	 * @param placa
	 * @param cilindraje
	 * @param tipo
	 * @return
	 */
	RespuestaRest<Boolean> registrarIngresoConRespuesta(String nombreParqueadero, String placa, String cilindraje,
			String tipo);
}
