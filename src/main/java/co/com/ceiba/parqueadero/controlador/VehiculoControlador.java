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
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.ITipoVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.IVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Controller
@RequestMapping(value = ConstantesServicio.U_VEHICULO)
public class VehiculoControlador {

	/**
	 * Definición del servicio
	 */
	@Autowired
	private IVehiculoServicio vs;
	@Autowired
	private ITipoVehiculoServicio tvs;
	private static final String TV_LISTA = "tipos";
	private static final String VEH_OBJ = "vehiculo";
	private static final String VEH_VISTA = "vehiculos";

	@GetMapping(ConstantesServicio.M_CANCELAR)
	public String cancelar() {
		return redireccionLista();
	}

	/**
	 * Permite mapear a vista la lista de vehiculo.
	 * 
	 * @return
	 */
	@GetMapping(ConstantesServicio.M_LISTAR)
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView(VEH_VISTA);
		mav.addObject(VEH_VISTA, vs.listar());
		return mav;
	}

	@GetMapping(ConstantesServicio.F_VEHICULO)
	public String redireccionForm(@RequestParam(name = ConstantesServicio.P_ID, required = false) String id,
			Model modelo) {
		VehiculoEntidad ve = new VehiculoEntidad();
		if (id != null) {
			ve = vs.encontrarXPk(id);
		}
		modelo.addAttribute(VEH_OBJ, ve);
		obtenerListaTipo(modelo);
		return ConstantesServicio.F_VEHICULO;
	}

	@PostMapping(value = ConstantesServicio.M_GUARDAR)
	public String guardar(@ModelAttribute(VEH_OBJ) VehiculoEntidad vehiculo, Model modelo) {
		if (vehiculo.getPlaca() == null) {
			vs.agregar(vehiculo);
		} else {
			vs.actualizar(vehiculo);
		}
		return redireccionLista();
	}

	@GetMapping(value = ConstantesServicio.M_ELIMINAR)
	public ModelAndView eliminar(@RequestParam(name = ConstantesServicio.P_ID, required = true) String id) {
		vs.eliminar(id);
		return listar();
	}

	/**
	 * Obtiene la lista de vehiculo para presentación.
	 * 
	 * @param modelo
	 * @return
	 */
	public Model obtenerListaTipo(Model modelo) {
		modelo.addAttribute(TV_LISTA, tvs.listar());
		return modelo;
	}

	@RequestMapping(value = ConstantesServicio.M_CONSULTAR, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public RespuestaRest<VehiculoEntidad> consultarVehiculo(
			@RequestParam(value = ConstantesServicio.P_ID, required = true) final String placa) {
		return vs.consultar(placa);
	}

	/**
	 * Permite redireccionar al listado de vehiculo.
	 * 
	 * @return
	 */
	private String redireccionLista() {
		StringBuilder sb = new StringBuilder().append(ConstantesServicio.REDIRECT).append(ConstantesServicio.U_VEHICULO)
				.append(ConstantesServicio.SLASH).append(ConstantesServicio.M_LISTAR);
		return sb.toString();
	}
}
