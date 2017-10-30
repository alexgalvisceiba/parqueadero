package co.com.ceiba.parqueadero.persistencia.entidad;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.ceiba.parqueadero.persistencia.entidad.comun.AbstraccionEntidad;

@Entity(name = "TarifaXParqueadero")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "parqueadero", "tipoVehiculo" }))
@NamedQueries({
		@NamedQuery(name = TarifaXParqueaderoEntidad.TARIFAXPARQUEADERO_POR_ESTADO, query = TarifaXParqueaderoEntidad.Q_TARIFAXPARQUEADERO_POR_ESTADO),
		@NamedQuery(name = TarifaXParqueaderoEntidad.TARIFAXPARQUEADERO_POR_PARQUEADEROYTIPO, query = TarifaXParqueaderoEntidad.Q_TARIFAXPARQUEADERO_POR_PARQUEADEROYTIPO) })
public class TarifaXParqueaderoEntidad extends AbstraccionEntidad {

	public static final String TARIFAXPARQUEADERO_POR_ESTADO = "TarifaXParqueadero.buscarXEstado";
	public static final String TARIFAXPARQUEADERO_POR_PARQUEADEROYTIPO = "TarifaXParqueadero.buscarXParqueaderoYTipo";
	public static final String Q_TARIFAXPARQUEADERO_POR_ESTADO = "SELECT txp FROM TarifaXParqueadero txp WHERE txp.estado = ?1";
	public static final String Q_TARIFAXPARQUEADERO_POR_PARQUEADEROYTIPO = "SELECT txp FROM TarifaXParqueadero txp WHERE txp.estado = ?1 AND txp.parqueadero = ?2 AND txp.tipoVehiculo = ?3";

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "codigoTarifaGen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "tarifa_sequence"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "codigoTarifaGen")
	@Column(name = "codigotarifa", nullable = false)
	private Long codigoTarifa;

	@ManyToOne
	@JoinColumn(name = "parqueadero", nullable = false)
	private ParqueaderoEntidad parqueadero;

	@ManyToOne
	@JoinColumn(name = "tipoVehiculo", nullable = false)
	private TipoVehiculoEntidad tipoVehiculo;

	@Column(name = "valorHora", nullable = false)
	private BigDecimal valorHora;

	@Column(name = "valorDia", nullable = false)
	private BigDecimal valorDia;

	/**
	 * @return the codigoTarifa
	 */
	public Long getCodigoTarifa() {
		return codigoTarifa;
	}

	/**
	 * @param codigoTarifa
	 *            the codigoTarifa to set
	 */
	public void setCodigoTarifa(Long codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	/**
	 * @return the parqueadero
	 */
	public ParqueaderoEntidad getParqueadero() {
		return parqueadero;
	}

	/**
	 * @param parqueadero
	 *            the parqueadero to set
	 */
	public void setParqueadero(ParqueaderoEntidad parqueadero) {
		this.parqueadero = parqueadero;
	}

	/**
	 * @return the tipoVehiculo
	 */
	public TipoVehiculoEntidad getTipoVehiculo() {
		return tipoVehiculo;
	}

	/**
	 * @param tipoVehiculo
	 *            the tipoVehiculo to set
	 */
	public void setTipoVehiculo(TipoVehiculoEntidad tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
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

	// Implementación para fácil construcción

	public TarifaXParqueaderoEntidad conCodigo(Long codigo) {
		setCodigoTarifa(codigo);
		return this;
	}

	public TarifaXParqueaderoEntidad conParqueadero(ParqueaderoEntidad p) {
		setParqueadero(p);
		return this;
	}

	public TarifaXParqueaderoEntidad conTipo(TipoVehiculoEntidad tv) {
		setTipoVehiculo(tv);
		return this;
	}

	public TarifaXParqueaderoEntidad conValorHora(BigDecimal valorHora) {
		setValorHora(valorHora);
		return this;
	}

	public TarifaXParqueaderoEntidad conValorDia(BigDecimal valorDia) {
		setValorDia(valorDia);
		return this;
	}

}
