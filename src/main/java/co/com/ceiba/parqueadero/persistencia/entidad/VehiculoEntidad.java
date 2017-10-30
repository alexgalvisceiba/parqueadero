package co.com.ceiba.parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import co.com.ceiba.parqueadero.persistencia.entidad.comun.AbstraccionEntidad;

@Entity(name = "Vehiculo")
@NamedQuery(name = VehiculoEntidad.VEHICULO_POR_ESTADO, query = VehiculoEntidad.Q_VEHICULO_POR_ESTADO)
public class VehiculoEntidad extends AbstraccionEntidad {

	private static final long serialVersionUID = 1L;

	public static final String VEHICULO_POR_ESTADO = "Vehiculo.buscarXEstado";
	public static final String Q_VEHICULO_POR_ESTADO = "SELECT v FROM Vehiculo v WHERE v.estado = ?1";

	@Id
	@Column(unique = true, nullable = false)
	private String placa;

	@ManyToOne
	@JoinColumn(name = "tipo", nullable = false)
	private TipoVehiculoEntidad tipo;

	@Column(name = "cilindraje", nullable = false)
	private Long cilindraje;

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa
	 *            the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the cilindraje
	 */
	public Long getCilindraje() {
		return cilindraje;
	}

	/**
	 * @param cilindraje
	 *            the cilindraje to set
	 */
	public void setCilindraje(Long cilindraje) {
		this.cilindraje = cilindraje;
	}

	/**
	 * @return the tipo
	 */
	public TipoVehiculoEntidad getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoVehiculoEntidad tipo) {
		this.tipo = tipo;
	}

	// Implementación para fácil construcción

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public VehiculoEntidad conPlaca(String placa) {
		setPlaca(placa);
		return this;
	}

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public VehiculoEntidad conTipo(TipoVehiculoEntidad tipo) {
		setTipo(tipo);
		return this;
	}

	/**
	 * 
	 * @param cilindraje
	 * @return
	 */
	public VehiculoEntidad conCilindraje(Long cilindraje) {
		setCilindraje(cilindraje);
		return this;
	}

	/**
	 * 
	 * @param estado
	 * @return
	 */
	public VehiculoEntidad conEstado(boolean estado) {
		super.setEstado(estado);
		return this;
	}

}
