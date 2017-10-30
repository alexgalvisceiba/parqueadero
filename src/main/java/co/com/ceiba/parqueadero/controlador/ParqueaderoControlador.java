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
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.servicio.IParqueaderoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Controller
@RequestMapping(value = ConstantesServicio.U_PARQUEADERO)
public class ParqueaderoControlador {

	/**
	 * Definición del servicio
	 */
	@Autowired
	private IParqueaderoServicio ps;
	private static final String PARQ_OBJ = "parqueadero";
	private static final String PARQ_LISTA = "parqueaderos";

	@GetMapping(ConstantesServicio.M_CANCELAR)
	public String cancelar() {
		return redireccionLista();
	}

	/**
	 * Permite mapear a vista la lista de parqueaderos.
	 * 
	 * @return
	 */
	@GetMapping(ConstantesServicio.M_LISTAR)
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView(PARQ_LISTA);
		mav.addObject(PARQ_LISTA, ps.listar());
		return mav;
	}

	@GetMapping(ConstantesServicio.F_PARQUEADERO)
	public String redireccionForm(@RequestParam(name = ConstantesServicio.P_ID, required = false) Long id,
			Model modelo) {
		ParqueaderoEntidad pe = new ParqueaderoEntidad();
		if (id != null) {
			pe = ps.encontrarXPk(id);
		}
		modelo.addAttribute(PARQ_OBJ, pe);
		return ConstantesServicio.F_PARQUEADERO;
	}

	@PostMapping(value = ConstantesServicio.M_GUARDAR)
	public String guardar(@ModelAttribute(PARQ_OBJ) ParqueaderoEntidad parqueadero, Model modelo) {
		if (parqueadero.getCodigoParqueadero() == null) {
			ps.agregar(parqueadero);
		} else {
			ps.actualizar(parqueadero);
		}
		return redireccionLista();
	}

	@GetMapping(value = ConstantesServicio.M_ELIMINAR)
	public ModelAndView eliminar(@RequestParam(name = ConstantesServicio.P_ID, required = true) Long id) {
		ps.eliminar(id);
		return listar();
	}

	/**
	 * Controlador para exponer el servicio REST de consulta.
	 * 
	 * @param id
	 *            la pk para obtener el parqueadero.
	 * @return objeto de respuesta con los datos devueltos.
	 */
	@RequestMapping(value = ConstantesServicio.M_CONSULTAR, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public RespuestaRest<ParqueaderoEntidad> consultar(
			@RequestParam(value = ConstantesServicio.P_ID, required = true) final String id) {
		return ps.consultar(id);
	}

	/**
	 * Permite redireccionar al listado de parqueadero.
	 * 
	 * @return
	 */
	private String redireccionLista() {
		StringBuilder sb = new StringBuilder().append(ConstantesServicio.REDIRECT)
				.append(ConstantesServicio.U_PARQUEADERO).append(ConstantesServicio.SLASH)
				.append(ConstantesServicio.M_LISTAR);
		return sb.toString();
	}

}
