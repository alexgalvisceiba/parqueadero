package co.com.ceiba.parqueadero.dominio;

import java.math.BigDecimal;

import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.TarifaXParqueaderoEntidad;

public class Tarifa {

	private Long codigoParqueadero;
	private TipoVehiculoEnum tipo;
	private BigDecimal valorHora;
	private BigDecimal valorDia;
	private BigDecimal cargoAdicional;

	public Tarifa() {
		super();
	}

	public Tarifa(TarifaXParqueaderoEntidad te) {
		super();
		codigoParqueadero = te.getParqueadero().getCodigoParqueadero();
		tipo = te.getTipoVehiculo().getNombre();
		valorHora = te.getValorHora();
		valorDia = te.getValorDia();
		cargoAdicional = BigDecimal.ZERO;
	}

	/**
	 * @return the codigoParqueadero
	 */
	public Long getCodigoParqueadero() {
		return codigoParqueadero;
	}

	/**
	 * @param codigoParqueadero
	 *            the codigoParqueadero to set
	 */
	public void setCodigoParqueadero(Long codigoParqueadero) {
		this.codigoParqueadero = codigoParqueadero;
	}

	/**
	 * @return the tipo
	 */
	public TipoVehiculoEnum getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoVehiculoEnum tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the valorHora
	 */
	public BigDecimal getValorHora() {
		return valorHora;
	}

	/**
	 * @param valorHora
	 *            the valorHora to set
	 */
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	/**
	 * @return the valorDia
	 */
	public BigDecimal getValorDia() {
		return valorDia;
	}

	/**
	 * @param valorDia
	 *            the valorDia to set
	 */
	public void setValorDia(BigDecimal valorDia) {
		this.valorDia = valorDia;
	}

	/**
	 * @return the cargoAdicional
	 */
	public BigDecimal getCargoAdicional() {
		return cargoAdicional;
	}

	/**
	 * @param cargoAdicional
	 *            the cargoAdicional to set
	 */
	public void setCargoAdicional(BigDecimal cargoAdicional) {
		this.cargoAdicional = cargoAdicional;
	}

}
