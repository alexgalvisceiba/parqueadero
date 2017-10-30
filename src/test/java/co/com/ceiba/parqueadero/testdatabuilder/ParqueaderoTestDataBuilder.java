package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.constantes.ConstantesParqueadero;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;

public class ParqueaderoTestDataBuilder {

	public static final Long PAR_CODIGO = 1L;
	public static final String PAR_NOMBRE = ConstantesParqueadero.MI_PARQUEADERO;
	public static final String PAR_NOMBRE2 = ConstantesParqueadero.MI_PARQUEADERO + 2;
	public static final Boolean PAR_ESTADO = true;

	private Long codigo;
	private String nombre;
	private boolean estado;

	public ParqueaderoTestDataBuilder() {
		this.codigo = PAR_CODIGO;
		this.nombre = PAR_NOMBRE;
		this.estado = PAR_ESTADO;
	}

	public ParqueaderoTestDataBuilder conCodigo(Long codigo) {
		this.codigo = codigo;
		return this;
	}

	public ParqueaderoTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public ParqueaderoTestDataBuilder conEstado(Boolean estado) {
		this.estado = estado;
		return this;
	}

	public ParqueaderoEntidad build() {
		return new ParqueaderoEntidad().conCodigo(this.codigo).conEstado(this.estado).conNombre(this.nombre);
	}
}
