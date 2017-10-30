package co.com.ceiba.parqueadero.testdatabuilder;

import java.math.BigDecimal;
import java.util.Date;

import co.com.ceiba.parqueadero.persistencia.entidad.RegistroEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

public class RegistroTestDataBuilder {

	public static final Date PAR_FECHAINGRESO = new Date();
	public static final Date PAR_FECHASALIDA = new Date();
	public static final BigDecimal PAR_COBRO = BigDecimal.ZERO;
	public static final Boolean PAR_ESTADO = true;

	private Long codigoRegistro;
	private Date fechaIngreso;
	private Date fechaSalida;
	private BigDecimal cobro;
	private VehiculoEntidad vehiculo;
	private Boolean estado;

	public RegistroTestDataBuilder() {
		this.fechaIngreso = PAR_FECHAINGRESO;
		this.fechaSalida = PAR_FECHASALIDA;
		this.cobro = PAR_COBRO;
		this.estado = PAR_ESTADO;
	}

	public RegistroTestDataBuilder conCodigo(Long codigo) {
		this.codigoRegistro = codigo;
		return this;
	}

	public RegistroTestDataBuilder conFechaIngreso(Date fechaI) {
		this.fechaIngreso = fechaI;
		return this;
	}

	public RegistroTestDataBuilder conFechaSalida(Date fechaS) {
		this.fechaSalida = fechaS;
		return this;
	}

	public RegistroTestDataBuilder conCobro(BigDecimal cobro) {
		this.cobro = cobro;
		return this;
	}

	public RegistroTestDataBuilder conVehiculo(VehiculoEntidad v) {
		this.vehiculo = v;
		return this;
	}

	public RegistroTestDataBuilder conEstado(Boolean estado) {
		this.estado = estado;
		return this;
	}

	public RegistroEntidad build() {
		RegistroEntidad re = new RegistroEntidad();
		re.setCodigoRegistro(this.codigoRegistro);
		re.setCobro(this.cobro);
		re.setEstado(this.estado);
		re.setFechaIngreso(this.fechaIngreso);
		re.setFechaSalida(this.fechaSalida);
		re.setVehiculo(this.vehiculo);
		return re;
	}

}
