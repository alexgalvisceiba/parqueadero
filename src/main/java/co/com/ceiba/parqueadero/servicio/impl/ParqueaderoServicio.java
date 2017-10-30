package co.com.ceiba.parqueadero.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.repositorio.IParqueaderoJPA;
import co.com.ceiba.parqueadero.servicio.IParqueaderoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Service
public class ParqueaderoServicio implements IParqueaderoServicio {

	/**
	 * Definición del modelo/repositorio
	 */
	@Autowired
	private IParqueaderoJPA pm;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParqueaderoEntidad agregar(ParqueaderoEntidad parqueadero) {
		return pm.save(parqueadero);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParqueaderoEntidad agregar(String nombre) {
		ParqueaderoEntidad pe = encontrarXNombre(nombre);
		if (pe == null) {
			pe = pm.save(new ParqueaderoEntidad().conNombre(nombre));
		}
		return pe;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParqueaderoEntidad actualizar(ParqueaderoEntidad parqueadero) {
		return pm.save(parqueadero);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParqueaderoEntidad encontrarXPk(Long codigo) {
		return pm.findOne(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParqueaderoEntidad encontrarXNombre(String nombre) {
		return pm.findByNombre(nombre);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminar(Long codigo) {
		pm.delete(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParqueaderoEntidad> listar() {
		return pm.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RespuestaRest<ParqueaderoEntidad> consultar(String id) {
		RespuestaRest<ParqueaderoEntidad> r = new RespuestaRest<>();
		try {
			r.setObjeto(encontrarXPk(Long.parseLong(id)));
		} catch (Exception e) {
			r.obtenerError(e.getMessage());
		}
		return r;
	}

}
