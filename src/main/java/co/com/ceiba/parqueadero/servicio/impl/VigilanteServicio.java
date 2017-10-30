package co.com.ceiba.parqueadero.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Vigilante;
import co.com.ceiba.parqueadero.servicio.IVigilanteServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Service
public class VigilanteServicio implements IVigilanteServicio {

	/**
	 * Define el objeto vigilante.
	 */
	@Autowired
	private Vigilante v;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean registrarIngreso(String nombreParqueadero, String placa, String cilindraje, String tipo) {
		return registrarIngresoConRespuesta(nombreParqueadero, placa, cilindraje, tipo).getObjeto();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RespuestaRest<Boolean> registrarIngresoConRespuesta(String nombreParqueadero, String placa,
			String cilindraje, String tipo) {
		Boolean retorno = true;
		RespuestaRest<Boolean> r = new RespuestaRest<>();
		try {
			retorno = v.registrarIngreso(nombreParqueadero, placa, tipo, cilindraje);
			r.setObjeto(retorno);
		} catch (Exception e) {
			r.obtenerError(e.getMessage());
			r.setObjeto(false);
		}
		return r;
	}

}
