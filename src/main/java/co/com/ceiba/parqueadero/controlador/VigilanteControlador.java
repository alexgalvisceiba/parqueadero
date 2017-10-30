package co.com.ceiba.parqueadero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.constantes.ConstantesServicio;
import co.com.ceiba.parqueadero.servicio.IVigilanteServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Controller
@RequestMapping(value = ConstantesServicio.U_VIGILANTE)
public class VigilanteControlador {

	/**
	 * Definición del servicio
	 */
	@Autowired
	private IVigilanteServicio vis;

	/**
	 * Controlador para exponer el servicio REST de ingreso.
	 * 
	 * @param nombre,
	 *            el nombre del parqueadero
	 * @param placa,
	 *            la placa del vehiculo que ingresa
	 * @param tipo,
	 *            el tipo de vehiculo a registrar
	 * @param cilindraje,
	 *            el cilindraje del vehiculo
	 * @return objeto de respuesta con los datos devueltos.
	 */
	@GetMapping(value = ConstantesServicio.M_REGISTRO_INGRESO, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public RespuestaRest<Boolean> registrarIngreso(
			@RequestParam(value = ConstantesServicio.P_NOMBRE, required = true) final String nombre,
			@RequestParam(value = ConstantesServicio.P_PLACA, required = true) final String placa,
			@RequestParam(value = ConstantesServicio.P_TIPO, required = true) final String tipo,
			@RequestParam(value = ConstantesServicio.P_CILIN, required = true) final String cilindraje) {
		return vis.registrarIngresoConRespuesta(nombre, placa, cilindraje, tipo);
	}
}
