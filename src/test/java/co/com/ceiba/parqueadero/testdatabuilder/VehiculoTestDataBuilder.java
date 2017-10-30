package co.com.ceiba.parqueadero.testdatabuilder;

import java.util.Random;

import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

public class VehiculoTestDataBuilder {

	public static final String PAR_PLACA = "AGT-542";
	public static final String PAR_PLACA2 = "EGT-542";
	public static final Long PAR_CILIN = 300L;
	public static final Long PAR_CILIN2 = 600L;
	public static final boolean PAR_ESTADO = true;

	private TipoVehiculoEntidad tve;
	private String placa;
	private Long cilindraje;
	private boolean estado;

	/**
	 * Constructor del builder.
	 */
	public VehiculoTestDataBuilder() {
		this.placa = PAR_PLACA2;
		this.cilindraje = PAR_CILIN;
		this.estado = PAR_ESTADO;
	}

	/**
	 * 
	 * @param placa
	 *            asignacion de placa al builder
	 * @return la instancia
	 */
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	/**
	 * 
	 * @param cilindraje
	 *            asignacion de cilindraje al builder
	 * @return la instancia
	 */
	public VehiculoTestDataBuilder conCilindraje(Long cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	/**
	 * 
	 * @param estado
	 *            asignacion de estado al builder
	 * @return la instancia
	 */
	public VehiculoTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}

	public VehiculoTestDataBuilder conTipo(TipoVehiculoEntidad tipo) {
		this.tve = tipo;
		return this;
	}

	/**
	 * Construye la instancia del objeto parqueadero.
	 * 
	 * @return la construcci�n del objeto.
	 */
	public VehiculoEntidad build() {
		return new VehiculoEntidad().conPlaca(this.placa).conCilindraje(this.cilindraje).conEstado(this.estado)
				.conTipo(this.tve);
	}

	public String generarPlacaAleatoria() {
		String csd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String csn = "0123456789";
		StringBuilder sbd = new StringBuilder();
		StringBuilder sbn = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			sbd.append(csd.charAt(random.nextInt(csd.length())));
			sbn.append(csn.charAt(random.nextInt(csn.length())));
		}
		return sbd.toString() + "-" + sbn.toString();
	}

}
