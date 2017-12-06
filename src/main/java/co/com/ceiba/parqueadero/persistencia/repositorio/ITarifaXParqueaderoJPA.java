package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TarifaXParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;

/**
 * Define la interfaz para interaccion con la persistencia de
 * tarifaxparqueadero.
 * 
 * @author Ceiba Software <BR>
 *
 */
@Repository
@Transactional
public interface ITarifaXParqueaderoJPA extends JpaRepository<TarifaXParqueaderoEntidad, Long> {

	/**
	 * Obtiene una lista de tarifaxparqueadero dependiendo del campo estado.
	 * 
	 * @param estado
	 * @return
	 */
	@Query(TarifaXParqueaderoEntidad.Q_TARIFAXPARQUEADERO_POR_ESTADO)
	List<TarifaXParqueaderoEntidad> obtenerEntidadXEstado(Boolean estado);

	/**
	 * Obtiene un tarifaxparqueadero dependiendo de los datos suministrados.
	 * 
	 * @param estado
	 * @param p
	 * @param tv
	 * @return
	 */
	@Query(TarifaXParqueaderoEntidad.Q_TARIFAXPARQUEADERO_POR_PARQUEADEROYTIPO)
	TarifaXParqueaderoEntidad obtenerEntidadXTipoYParqueadero(Boolean estado, ParqueaderoEntidad p,
			TipoVehiculoEntidad tv);
}
