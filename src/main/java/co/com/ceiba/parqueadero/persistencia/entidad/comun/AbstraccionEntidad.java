package co.com.ceiba.parqueadero.persistencia.entidad.comun;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

@MappedSuperclass
public class AbstraccionEntidad implements Serializable {

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1256501001903199589L;
	private static final Logger LOGGER = Logger.getLogger(AbstraccionEntidad.class);

	/**
	 * Versión para el optimistic lock.
	 */

	@Column(name = "estado", nullable = false)
	private Boolean estado;

	/**
	 * Método que se ejecuta justo antes de insertar.
	 */
	@PrePersist
	public void prePersist() {
		LOGGER.info("Guardando...");
		if (estado == null) {
			estado = true;
		}
	}

	/**
	 * Método que se ejecuta justo antes de actualizar.
	 */
	@PreUpdate
	public void preUpdate() {
		LOGGER.info("Actualizando...");
		if (estado == null) {
			estado = true;
		}
	}

	/**
	 * Método que se ejecuta justo antes de eliminar.
	 */
	@PreRemove
	public void preRemove() {
		LOGGER.info("Eliminando...");
	}

	/**
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
