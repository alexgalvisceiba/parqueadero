package co.com.ceiba.parqueadero.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.ceiba.parqueadero.constantes.ConstantesServicio;

@Controller
public class IndexControlador {
	
	@RequestMapping(ConstantesServicio.U_INDEX)
	public String index() {
		return ConstantesServicio.INDEX;
	}
}