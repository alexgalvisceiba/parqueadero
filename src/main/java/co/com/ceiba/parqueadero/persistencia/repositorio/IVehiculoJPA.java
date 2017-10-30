package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

/**
 * Define la interfaz para interaccion con la persistencia de vehiculo.
 * 
 * @author Ceiba Software <BR>
 *
 */
@Repository
@Transactional
public interface IVehiculoJPA extends JpaRepository<VehiculoEntidad, String> {

	/**
	 * Obtiene una lista de vehiculos dependiendo del campo estado.
	 * 
	 * @param estado
	 * @return
	 */
	@Query(VehiculoEntidad.Q_VEHICULO_POR_ESTADO)
	public List<VehiculoEntidad> obtenerEntidadXEstado(Boolean estado);
}
