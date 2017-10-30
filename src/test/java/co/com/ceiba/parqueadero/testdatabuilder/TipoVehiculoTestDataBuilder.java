package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;

public class TipoVehiculoTestDataBuilder {

	public static final TipoVehiculoEnum PAR_TIPO1 = TipoVehiculoEnum.CARRO;
	public static final TipoVehiculoEnum PAR_TIPO2 = TipoVehiculoEnum.MOTO;
	public static final Long PAR_CODIGO = 1L;
	public static final Boolean PAR_ESTADO = true;

	private Long codigo;
	private TipoVehiculoEnum tipoVehiculo;
	private boolean estado;

	/**
	 * Constructor del builder.
	 */
	public TipoVehiculoTestDataBuilder() {
		this.codigo = PAR_CODIGO;
		this.tipoVehiculo = PAR_TIPO1;
		this.estado = PAR_ESTADO;
	}

	public TipoVehiculoTestDataBuilder conCodigo(Long codigo) {
		this.codigo = codigo;
		return this;
	}

	public TipoVehiculoTestDataBuilder conTipoVehiculo(TipoVehiculoEnum tipo) {
		this.tipoVehiculo = tipo;
		return this;
	}

	public TipoVehiculoTestDataBuilder conEstado(Boolean estado) {
		this.estado = estado;
		return this;
	}

	public TipoVehiculoEntidad build() {
		return new TipoVehiculoEntidad().conCodigo(codigo).conNombre(tipoVehiculo).conEstado(estado);
	}
}
