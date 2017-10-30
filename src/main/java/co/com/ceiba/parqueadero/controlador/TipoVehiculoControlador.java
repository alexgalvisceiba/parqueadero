package co.com.ceiba.parqueadero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.ceiba.parqueadero.constantes.ConstantesServicio;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.ITipoVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Controller
@RequestMapping(value = ConstantesServicio.U_TIPOVEHICULO)
public class TipoVehiculoControlador {

	/**
	 * Definición del servicio
	 */
	@Autowired
	private ITipoVehiculoServicio tvs;
	private static final String TV_OBJ = "tipoVehiculo";
	private static final String TV_VISTA = "tipoVehiculos";


	@GetMapping(ConstantesServicio.M_CANCELAR)
	public String cancelar() {
		return redireccionLista();
	}

	/**
	 * Permite mapear a vista la lista de tipo de vehiculo.
	 * 
	 * @return
	 */
	@GetMapping(ConstantesServicio.M_LISTAR)
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView(TV_VISTA);
		mav.addObject(TV_VISTA, tvs.listar());
		return mav;
	}

	@GetMapping(ConstantesServicio.F_TIPOVEHICULO)
	public String redireccionForm(@RequestParam(name = ConstantesServicio.P_ID, required = false) Long id,
			Model modelo) {
		TipoVehiculoEntidad tve = new TipoVehiculoEntidad();
		if (id != null) {
			tve = tvs.encontrarXPk(id);
		}
		modelo.addAttribute(TV_OBJ, tve);
		return ConstantesServicio.F_TIPOVEHICULO;
	}

	@PostMapping(value = ConstantesServicio.M_GUARDAR)
	public String guardar(@ModelAttribute(TV_OBJ) TipoVehiculoEntidad tipoVehiculo, Model modelo) {
		if (tipoVehiculo.getCodigoTipo() == null) {
			tvs.agregar(tipoVehiculo);
		} else {
			tvs.actualizar(tipoVehiculo);
		}
		return redireccionLista();
	}

	@GetMapping(value = ConstantesServicio.M_ELIMINAR)
	public ModelAndView eliminar(@RequestParam(name = ConstantesServicio.P_ID, required = true) Long id) {
		tvs.eliminar(id);
		return listar();
	}

	@RequestMapping(value = ConstantesServicio.M_CONSULTAR, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public RespuestaRest<TipoVehiculoEntidad> consultar(
			@RequestParam(value = ConstantesServicio.P_ID, required = true) final String id) {
		return tvs.consultar(id);
	}

	/**
	 * Permite redireccionar al listado de tipo de vehiculo.
	 * 
	 * @return
	 */
	private String redireccionLista() {
		StringBuilder sb = new StringBuilder().append(ConstantesServicio.REDIRECT)
				.append(ConstantesServicio.U_TIPOVEHICULO).append(ConstantesServicio.SLASH)
				.append(ConstantesServicio.M_LISTAR);
		return sb.toString();
	}

}
