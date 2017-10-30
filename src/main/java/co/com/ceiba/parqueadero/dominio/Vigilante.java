package co.com.ceiba.parqueadero.dominio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.constantes.ConstantesParqueadero;
import co.com.ceiba.parqueadero.dominio.excepcion.IngresoException;
import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.RegistroEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.repositorio.IParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.IRegistroJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITarifaXParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITipoVehiculoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITopeXParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.IVehiculoJPA;

@Component
public class Vigilante {

	@Autowired
	private IParqueaderoJPA pBO;
	@Autowired
	private IRegistroJPA rBO;
	@Autowired
	private IVehiculoJPA vBO;
	@Autowired
	private ITipoVehiculoJPA tvBO;
	@Autowired
	private ITarifaXParqueaderoJPA txpBO;
	@Autowired
	private ITopeXParqueaderoJPA toxpBO;

	/**
	 * Constructor explicito.
	 */
	public Vigilante() {
		super();
	}

	/**
	 * 
	 * @param pBO
	 * @param rBO
	 * @param txpBO
	 * @param toxpBO
	 * @param vBO
	 * @param tvBO
	 */
	public Vigilante(IParqueaderoJPA pBO, IRegistroJPA rBO, ITarifaXParqueaderoJPA txpBO, ITopeXParqueaderoJPA toxpBO,
			IVehiculoJPA vBO, ITipoVehiculoJPA tvBO) {
		super();
		this.pBO = pBO;
		this.rBO = rBO;
		this.txpBO = txpBO;
		this.toxpBO = toxpBO;
		this.vBO = vBO;
		this.tvBO = tvBO;
	}

	/**
	 * Permite registrar el ingreso de un vehiculo en el parqueadero.
	 * 
	 * @param ve
	 *            el vehiculo
	 * @param pe
	 *            el parqueadero
	 * @return
	 */
	public Boolean registrarIngreso(String nombreParqueadero, String placa, String tipo, String cilindraje) {
		Boolean retorno = false;
		IParqueaderoReglas tp = obtenerTipoVehiculoParaParqueo(tipo);
		TipoVehiculoEnum tve = TipoVehiculoEnum.valueOf(tipo);
		if (tp.validarIngresoXPlaca(placa)) {
			VehiculoEntidad v = validarVehiculo(placa, cilindraje, tve);
			ParqueaderoEntidad p = validarParqueadero(nombreParqueadero);
			List<RegistroEntidad> lr = rBO.obtenerParqueados(v.getTipo());
			if (validarCupo(p, v, lr, tp)) {
				throw new IngresoException(ConstantesParqueadero.NO_CUPO);
			} else {
				RegistroEntidad re = new RegistroEntidad().conFechaIngreso(Calendar.getInstance().getTime())
						.conVehiculo(v).conParqueadero(p);
				rBO.save(re);
				retorno = true;
			}
		} else {
			throw new IngresoException(ConstantesParqueadero.NO_PUEDE_INGRESAR);
		}
		return retorno;
	}

	/**
	 * Permite registrar la salida de un vehiculo en el parqueadero.
	 * 
	 * @param nombreParqueadero
	 * @param placa
	 */
	public void registrarSalida(String nombreParqueadero, String placa) {
		ParqueaderoEntidad p = validarParqueadero(nombreParqueadero);
		VehiculoEntidad v = vBO.findOne(placa);
		List<RegistroEntidad> lr = rBO.obtenerXVehiculo(v);
		if (lr != null && !lr.isEmpty()) {
			IParqueaderoReglas tp = obtenerTipoVehiculoParaParqueo(v.getTipo().getNombre().name());
			Tarifa t = tp.obtenerTarifa(p, v);
			RegistroEntidad re = lr.get(0);
			Date fechaSalida = new Date();
			BigDecimal cobro = tp.obtenerCobro(re.getFechaIngreso(), fechaSalida, t, v);
			re.conCobro(cobro).conFechaSalida(fechaSalida);
			rBO.save(re);
		}
	}

	private VehiculoEntidad validarVehiculo(String placa, String cilindraje, TipoVehiculoEnum tipo) {
		VehiculoEntidad v = vBO.findOne(placa);
		if (v == null) {
			v = vBO.save(new VehiculoEntidad().conPlaca(placa).conCilindraje(Long.parseLong(cilindraje))
					.conTipo(validarTipoVehiculo(tipo)));
		}
		return v;
	}

	private TipoVehiculoEntidad validarTipoVehiculo(TipoVehiculoEnum tipo) {
		TipoVehiculoEntidad tv = tvBO.obtenerEntidadXNombre(tipo);
		if (tv == null) {
			tv = tvBO.save(new TipoVehiculoEntidad().conNombre(tipo));
		}
		return tv;
	}

	private ParqueaderoEntidad validarParqueadero(String nombreParqueadero) {
		ParqueaderoEntidad p = pBO.findByNombre(nombreParqueadero);
		if (p == null) {
			p = pBO.save(new ParqueaderoEntidad().conNombre(nombreParqueadero));
		}
		return p;
	}

	private IParqueaderoReglas obtenerTipoVehiculoParaParqueo(String tipo) {
		IParqueaderoReglas tp = null;
		if (tipo != null) {
			if (TipoVehiculoEnum.CARRO.name().equalsIgnoreCase(tipo)) {
				tp = new ParqueoCarro(txpBO, toxpBO);
			} else if (TipoVehiculoEnum.MOTO.name().equalsIgnoreCase(tipo)) {
				tp = new ParqueoMoto(txpBO, toxpBO);
			}
		}
		if (tp == null) {
			throw new IngresoException(ConstantesParqueadero.NO_VALIDO);
		}
		return tp;
	}

	private Boolean validarCupo(ParqueaderoEntidad pe, VehiculoEntidad ve, List<RegistroEntidad> lr,
			IParqueaderoReglas tp) {
		Boolean retorno = false;
		if (lr != null && !lr.isEmpty()) {
			retorno = tp.topeAlcanzado(pe, ve, lr.size());
		}
		return retorno;
	}

}
