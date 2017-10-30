package co.com.ceiba.parqueadero.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.repositorio.IVehiculoJPA;
import co.com.ceiba.parqueadero.servicio.ITipoVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.IVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;

@Service
public class VehiculoServicio implements IVehiculoServicio {

	/**
	 * Definición del modelo/repositorio
	 */
	@Autowired
	private IVehiculoJPA vm;
	/**
	 * Definición del servicio
	 */
	@Autowired
	private ITipoVehiculoServicio tvs;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VehiculoEntidad agregar(VehiculoEntidad vehiculo) {
		return vm.save(vehiculo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VehiculoEntidad agregar(String placa, String cilindraje, String tipo) {
		VehiculoEntidad ve = encontrarXPk(placa);
		if (ve == null) {
			Long cil = Long.parseLong(cilindraje);
			ve = vm.save(new VehiculoEntidad().conCilindraje(cil).conPlaca(placa).conTipo(obtenerTipo(tipo)));
		}
		return ve;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VehiculoEntidad actualizar(VehiculoEntidad vehiculo) {
		return vm.save(vehiculo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VehiculoEntidad encontrarXPk(String codigo) {
		return vm.findOne(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminar(String codigo) {
		vm.delete(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VehiculoEntidad> listar() {
		return vm.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RespuestaRest<VehiculoEntidad> consultar(String placa) {
		RespuestaRest<VehiculoEntidad> r = new RespuestaRest<>();
		try {
			r.setObjeto(vm.findOne(placa));
		} catch (Exception e) {
			r.obtenerError(e.getMessage());
		}
		return r;
	}

	/**
	 * Permite obtener un tipo de vehiculo valido.
	 * 
	 * @param tipo
	 * @return
	 */
	private TipoVehiculoEntidad obtenerTipo(String tipo) {
		return tvs.encontrarXTipo(tipo);
	}

}
