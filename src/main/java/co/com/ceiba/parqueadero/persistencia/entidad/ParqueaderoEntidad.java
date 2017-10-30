package co.com.ceiba.parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.ceiba.parqueadero.persistencia.entidad.comun.AbstraccionEntidad;

@Entity(name = "Parqueadero")
@NamedQueries({
		@NamedQuery(name = ParqueaderoEntidad.PARQUEADERO_POR_ESTADO, query = ParqueaderoEntidad.Q_PARQUEADERO_POR_ESTADO),
		@NamedQuery(name = ParqueaderoEntidad.PARQUEADERO_POR_NOMBRE, query = ParqueaderoEntidad.Q_PARQUEADERO_POR_NOMBRE) })
public class ParqueaderoEntidad extends AbstraccionEntidad {

	private static final long serialVersionUID = 1L;

	public static final String PARQUEADERO_POR_ESTADO = "Parqueadero.buscarXEstado";
	public static final String PARQUEADERO_POR_NOMBRE = "Parqueadero.buscarXNombre";
	public static final String Q_PARQUEADERO_POR_ESTADO = "SELECT p FROM Parqueadero p WHERE p.estado = ?1";
	public static final String Q_PARQUEADERO_POR_NOMBRE = "SELECT p FROM Parqueadero p WHERE p.nombre = ?1";

	@Id
	@GenericGenerator(name = "codigoParqGen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "parqueadero_sequence"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "codigoParqGen")
	@Column(name = "codigoparqueadero", nullable = false)
	private Long codigoParqueadero;

	@Column(name = "nombre", unique = true)
	private String nombre;

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Implementación para fácil construcción

	public ParqueaderoEntidad conCodigo(Long codigo) {
		setCodigoParqueadero(codigo);
		return this;
	}

	public ParqueaderoEntidad conNombre(String nombre) {
		setNombre(nombre);
		return this;
	}

	public ParqueaderoEntidad conEstado(Boolean estado) {
		setEstado(estado);
		return this;
	}

}
