package co.com.ceiba.parqueadero.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITipoVehiculoJPA;
import co.com.ceiba.parqueadero.servicio.ITipoVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Service
public class TipoVehiculoServicio implements ITipoVehiculoServicio {

	/**
	 * Definición del modelo/repositorio
	 */
	@Autowired
	private ITipoVehiculoJPA tvm;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoVehiculoEntidad agregar(TipoVehiculoEntidad tipo) {
		return tvm.save(tipo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoVehiculoEntidad actualizar(TipoVehiculoEntidad tipo) {
		return tvm.save(tipo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoVehiculoEntidad encontrarXPk(Long codigo) {
		return tvm.findOne(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoVehiculoEntidad encontrarXTipo(String tipo) {
		TipoVehiculoEntidad te = null;
		TipoVehiculoEnum tve = TipoVehiculoEnum.valueOf(tipo);
		if (tve != null) {
			te = tvm.obtenerEntidadXNombre(tve);
			te = te == null ? tvm.save(new TipoVehiculoEntidad().conNombre(tve)) : te;
		}
		return te;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminar(Long codigo) {
		tvm.delete(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoVehiculoEntidad> listar() {
		return tvm.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RespuestaRest<TipoVehiculoEntidad> consultar(String id) {
		RespuestaRest<TipoVehiculoEntidad> r = new RespuestaRest<>();
		try {
			Long pk = Long.parseLong(id);
			r.setObjeto(tvm.findOne(pk));
		} catch (Exception e) {
			r.obtenerError(e.getMessage());
		}
		return r;
	}

}
