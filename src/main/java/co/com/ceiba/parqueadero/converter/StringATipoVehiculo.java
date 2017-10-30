package co.com.ceiba.parqueadero.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.ITipoVehiculoServicio;

@Component
public class StringATipoVehiculo implements Converter<String, TipoVehiculoEntidad> {

	/**
	 * Definición del servicio
	 */
	@Autowired
	private ITipoVehiculoServicio tvs;

	@Override
	public TipoVehiculoEntidad convert(String arg0) {
		Long id = new Long(arg0);
		return tvs.encontrarXPk(id);
	}
}
