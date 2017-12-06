package co.com.ceiba.parqueadero.dominio;

import java.math.BigDecimal;
import java.util.Date;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

public interface ITarifa {

	Tarifa obtenerTarifa(ParqueaderoEntidad pe, VehiculoEntidad ve);

	BigDecimal obtenerCobro(Date fechaInicial, Date fechaFinal, Tarifa t, VehiculoEntidad ve);
}
