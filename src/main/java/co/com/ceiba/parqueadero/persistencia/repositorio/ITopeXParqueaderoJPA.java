package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TopeXParqueaderoEntidad;

/**
 * Define la interfaz para interaccion con la persistencia de topexparqueadero.
 * 
 * @author Ceiba Software <BR>
 *
 */
@Repository
@Transactional
public interface ITopeXParqueaderoJPA extends JpaRepository<TopeXParqueaderoEntidad, Long> {

	/**
	 * Obtiene una lista de topexparqueadero dependiendo del campo estado.
	 * 
	 * @param estado
	 * @return
	 */
	@Query(TopeXParqueaderoEntidad.Q_TOPEXPARQUEADERO_POR_ESTADO)
	List<TopeXParqueaderoEntidad> obtenerEntidadXEstado(Boolean estado);

	/**
	 * Obtiene un topexparqueadero dependiendo de los datos suministrados.
	 * 
	 * @param estado
	 * @param p
	 * @param tv
	 * @return
	 */
	@Query(TopeXParqueaderoEntidad.Q_TOPEXPARQUEADERO_POR_PARQUEADEROYTIPO)
	TopeXParqueaderoEntidad obtenerEntidadXTipoYParqueadero(Boolean estado, ParqueaderoEntidad p,
			TipoVehiculoEntidad tv);
}
