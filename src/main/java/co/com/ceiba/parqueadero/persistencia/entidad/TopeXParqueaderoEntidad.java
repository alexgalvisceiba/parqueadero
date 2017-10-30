package co.com.ceiba.parqueadero.persistencia.entidad;

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

@Entity(name = "TopeXParqueadero")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "parqueadero",
		"tipoVehiculo" }))
@NamedQueries({
		@NamedQuery(name = TopeXParqueaderoEntidad.TOPEXPARQUEADERO_POR_ESTADO, query = TopeXParqueaderoEntidad.Q_TOPEXPARQUEADERO_POR_ESTADO),
		@NamedQuery(name = TopeXParqueaderoEntidad.TOPEXPARQUEADERO_POR_PARQUEADEROYTIPO, query = TopeXParqueaderoEntidad.Q_TOPEXPARQUEADERO_POR_PARQUEADEROYTIPO) })
public class TopeXParqueaderoEntidad extends AbstraccionEntidad {

	public static final String TOPEXPARQUEADERO_POR_ESTADO = "TopeXParqueadero.buscarXEstado";
	public static final String TOPEXPARQUEADERO_POR_PARQUEADEROYTIPO = "TopeXParqueadero.buscarXParqueaderoYTipo";
	public static final String Q_TOPEXPARQUEADERO_POR_ESTADO = "SELECT txp FROM TopeXParqueadero txp WHERE txp.estado = ?1";
	public static final String Q_TOPEXPARQUEADERO_POR_PARQUEADEROYTIPO = "SELECT txp FROM TopeXParqueadero txp WHERE txp.estado = ?1 AND txp.parqueadero = ?2 AND txp.tipoVehiculo = ?3";

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "codigoTopeGen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "tope_sequence"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "codigoTopeGen")
	@Column(name = "codigotope", nullable = false)
	private Long codigoTope;

	@ManyToOne
	@JoinColumn(name = "parqueadero", nullable = false)
	private ParqueaderoEntidad parqueadero;

	@ManyToOne
	@JoinColumn(name = "tipoVehiculo", nullable = false)
	private TipoVehiculoEntidad tipoVehiculo;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	/**
	 * @return the codigoTope
	 */
	public Long getCodigoTope() {
		return codigoTope;
	}

	/**
	 * @param codigoTope
	 *            the codigoTope to set
	 */
	public void setCodigoTope(Long codigoTope) {
		this.codigoTope = codigoTope;
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
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// Implementación para fácil construcción

	public TopeXParqueaderoEntidad conCodigo(Long codigo) {
		setCodigoTope(codigo);
		return this;
	}

	public TopeXParqueaderoEntidad conParqueadero(ParqueaderoEntidad p) {
		setParqueadero(p);
		return this;
	}

	public TopeXParqueaderoEntidad conTipo(TipoVehiculoEntidad tv) {
		setTipoVehiculo(tv);
		return this;
	}

	public TopeXParqueaderoEntidad conCantidad(int cantidad) {
		setCantidad(cantidad);
		return this;
	}

}
