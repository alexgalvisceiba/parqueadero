package co.com.ceiba.parqueadero.persistencia.entidad;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.ceiba.parqueadero.persistencia.entidad.comun.AbstraccionEntidad;

@Entity(name = "Registro")
@NamedQueries({
		@NamedQuery(name = RegistroEntidad.REGISTRO_POR_VEHICULO, query = RegistroEntidad.Q_REGISTRO_POR_VEHICULO),
		@NamedQuery(name = RegistroEntidad.REGISTRO_EN_PARQUEADERO, query = RegistroEntidad.Q_REGISTRO_EN_PARQUEADERO) })
public class RegistroEntidad extends AbstraccionEntidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String REGISTRO_POR_VEHICULO = "Registro.buscarXVehiculo";
	public static final String REGISTRO_EN_PARQUEADERO = "Registro.buscarParqueados";
	public static final String Q_REGISTRO_POR_VEHICULO = "SELECT r FROM Registro r WHERE r.vehiculo = ?1 AND r.fechaSalida IS NULL";
	public static final String Q_REGISTRO_EN_PARQUEADERO = "SELECT r FROM Registro r WHERE r.vehiculo.tipo = ?1 AND r.fechaSalida IS NULL";

	@Id
	@GenericGenerator(name = "codigoRegistroGen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "registro_sequence"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "codigoRegistroGen")
	@Column(name = "codigoregistro", nullable = false)
	private Long codigoRegistro;

	@ManyToOne
	@JoinColumn(name = "parqueadero", nullable = false)
	private ParqueaderoEntidad parqueadero;

	@ManyToOne
	@JoinColumn(name = "vehiculo", nullable = false)
	private VehiculoEntidad vehiculo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;

	private BigDecimal cobro;

	/**
	 * @return the codigoRegistro
	 */
	public Long getCodigoRegistro() {
		return codigoRegistro;
	}

	/**
	 * @param codigoRegistro
	 *            the codigoRegistro to set
	 */
	public void setCodigoRegistro(Long codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
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
	 * @return the vehiculo
	 */
	public VehiculoEntidad getVehiculo() {
		return vehiculo;
	}

	/**
	 * @param vehiculo
	 *            the vehiculo to set
	 */
	public void setVehiculo(VehiculoEntidad vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso
	 *            the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the fechaSalida
	 */
	public Date getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * @param fechaSalida
	 *            the fechaSalida to set
	 */
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/**
	 * @return the cobro
	 */
	public BigDecimal getCobro() {
		return cobro;
	}

	/**
	 * @param cobro
	 *            the cobro to set
	 */
	public void setCobro(BigDecimal cobro) {
		this.cobro = cobro;
	}

	// Implementación para fácil construcción

	public RegistroEntidad conCodigo(Long codigo) {
		setCodigoRegistro(codigo);
		return this;
	}

	public RegistroEntidad conParqueadero(ParqueaderoEntidad p) {
		setParqueadero(p);
		return this;
	}

	public RegistroEntidad conVehiculo(VehiculoEntidad v) {
		setVehiculo(v);
		return this;
	}

	public RegistroEntidad conFechaIngreso(Date fechaIngreso) {
		setFechaIngreso(fechaIngreso);
		return this;
	}

	public RegistroEntidad conFechaSalida(Date fechaSalida) {
		setFechaSalida(fechaSalida);
		return this;
	}

	public RegistroEntidad conCobro(BigDecimal cobro) {
		setCobro(cobro);
		return this;
	}

}
