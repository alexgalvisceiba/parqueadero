package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.RegistroEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;

/**
 * Define la interfaz para interaccion con la persistencia de registro.
 * 
 * @author Ceiba Software <BR>
 *
 */
@Repository
@Transactional
public interface IRegistroJPA extends JpaRepository<RegistroEntidad, Long> {

	/**
	 * Obtiene una lista de registros basado en el vehiculo y sin fecha de retiro
	 * 
	 * @param v
	 * @return
	 */
	@Query(RegistroEntidad.Q_REGISTRO_POR_VEHICULO)
	public List<RegistroEntidad> obtenerXVehiculo(VehiculoEntidad v);

	/**
	 * Obtiene una lista de registros dependiendo del vehiculo
	 * 
	 * @param v
	 * @return
	 */
	@Query(RegistroEntidad.Q_REGISTRO_EN_PARQUEADERO)
	public List<RegistroEntidad> obtenerParqueados(TipoVehiculoEntidad tv);
}
