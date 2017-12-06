package co.com.ceiba.parqueadero.dominio;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

public interface IParqueaderoReglas extends ITarifa {

	Boolean placaEmpiezaConA(String placa);

	Boolean validarIngresoXPlaca(String placa);

	int obtenerTopeParqueadero(ParqueaderoEntidad pe, VehiculoEntidad ve);

	Boolean topeAlcanzado(ParqueaderoEntidad pe, VehiculoEntidad ve, int tope);
}
