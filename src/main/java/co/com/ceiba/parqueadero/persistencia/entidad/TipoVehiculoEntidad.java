package co.com.ceiba.parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.comun.AbstraccionEntidad;

@Entity(name = "TipoVehiculo")
@NamedQueries({
		@NamedQuery(name = TipoVehiculoEntidad.TIPOVEHICULO_POR_ESTADO, query = TipoVehiculoEntidad.Q_TIPOVEHICULO_POR_ESTADO),
		@NamedQuery(name = TipoVehiculoEntidad.TIPOVEHICULO_POR_NOMBRE, query = TipoVehiculoEntidad.Q_TIPOVEHICULO_POR_NOMBRE) })
public class TipoVehiculoEntidad extends AbstraccionEntidad {

	private static final long serialVersionUID = 1L;

	public static final String TIPOVEHICULO_POR_ESTADO = "TipoVehiculo.buscarXEstado";
	public static final String TIPOVEHICULO_POR_NOMBRE = "TipoVehiculo.buscarXNombre";
	public static final String Q_TIPOVEHICULO_POR_ESTADO = "SELECT tv FROM TipoVehiculo tv WHERE tv.estado = ?1";
	public static final String Q_TIPOVEHICULO_POR_NOMBRE = "SELECT tv FROM TipoVehiculo tv WHERE tv.nombre = ?1";

	@Id
	@GenericGenerator(name = "codigoTipoGen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "tipo_sequence"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "codigoTipoGen")
	@Column(name = "codigotipo", nullable = false)
	private Long codigoTipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipovehiculo", unique = true)
	private TipoVehiculoEnum nombre;

	/**
	 * @return the codigoTipo
	 */
	public Long getCodigoTipo() {
		return codigoTipo;
	}

	/**
	 * @param codigoTipo
	 *            the codigoTipo to set
	 */
	public void setCodigoTipo(Long codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	/**
	 * @return the nombre
	 */
	public TipoVehiculoEnum getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(TipoVehiculoEnum nombre) {
		this.nombre = nombre;
	}

	// Implementación para fácil construcción

	public TipoVehiculoEntidad conCodigo(Long codigo) {
		setCodigoTipo(codigo);
		return this;
	}

	public TipoVehiculoEntidad conNombre(TipoVehiculoEnum nombre) {
		setNombre(nombre);
		return this;
	}

	public TipoVehiculoEntidad conEstado(Boolean estado) {
		setEstado(estado);
		return this;
	}

}
