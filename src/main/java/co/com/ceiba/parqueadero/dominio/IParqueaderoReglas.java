package co.com.ceiba.parqueadero.dominio;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

public interface IParqueaderoReglas extends ITarifa {

	public Boolean placaEmpiezaConA(String placa);

	public Boolean validarIngresoXPlaca(String placa);

	public int obtenerTopeParqueadero(ParqueaderoEntidad pe, VehiculoEntidad ve);

	public Boolean topeAlcanzado(ParqueaderoEntidad pe, VehiculoEntidad ve, int tope);
}
