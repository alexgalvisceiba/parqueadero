package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;

/**
 * Define la interfaz para interaccion con la persistencia de parqueadero.
 * 
 * @author Ceiba Software <BR>
 *
 */
@Repository
@Transactional
public interface IParqueaderoJPA extends JpaRepository<ParqueaderoEntidad, Long> {

	/**
	 * Obtiene una lista de parqueadero dependiendo del campo estado.
	 * 
	 * @param estado
	 * @return
	 */
	List<ParqueaderoEntidad> findByEstado(Boolean estado);

	/**
	 * Obtiene un parqueadero dependiendo del nombre.
	 * 
	 * @param nombre
	 * @return
	 */
	ParqueaderoEntidad findByNombre(String nombre);
}
