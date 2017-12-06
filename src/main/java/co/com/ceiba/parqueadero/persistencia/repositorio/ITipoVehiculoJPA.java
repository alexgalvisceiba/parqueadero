package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;

/**
 * Define la interfaz para interaccion con la persistencia de tipovehiculo.
 * 
 * @author Ceiba Software <BR>
 *
 */
@Repository
@Transactional
public interface ITipoVehiculoJPA extends JpaRepository<TipoVehiculoEntidad, Long> {

	/**
	 * Obtiene una lista de tipo vehiculo dependiendo del campo estado.
	 * 
	 * @param estado
	 * @return
	 */
	@Query(TipoVehiculoEntidad.Q_TIPOVEHICULO_POR_ESTADO)
	List<TipoVehiculoEntidad> obtenerEntidadXEstado(Boolean estado);

	/**
	 * Obtiene un tipo vehiculo dependiendo del tipo.
	 * 
	 * @param tipo
	 * @return
	 */
	@Query(TipoVehiculoEntidad.Q_TIPOVEHICULO_POR_NOMBRE)
	TipoVehiculoEntidad obtenerEntidadXNombre(TipoVehiculoEnum tipo);
}
