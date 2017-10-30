package co.com.ceiba.parqueadero.dominio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.parqueadero.constantes.ConstantesParqueadero;
import co.com.ceiba.parqueadero.dominio.util.Validacion;
import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TarifaXParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TopeXParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITarifaXParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITopeXParqueaderoJPA;

public class ParqueoMoto implements IParqueaderoReglas {

	private ITarifaXParqueaderoJPA txpBO;
	private ITopeXParqueaderoJPA toxpBO;

	public ParqueoMoto(ITarifaXParqueaderoJPA txp, ITopeXParqueaderoJPA toxp) {
		this.txpBO = txp;
		this.toxpBO = toxp;
	}

	@Override
	public int obtenerTopeParqueadero(ParqueaderoEntidad pe, VehiculoEntidad ve) {
		int retorno = ConstantesParqueadero.TOPE_MOTO;
		TopeXParqueaderoEntidad txp = toxpBO.obtenerEntidadXTipoYParqueadero(true, pe, ve.getTipo());
		if (txp != null) {
			retorno = txp.getCantidad();
		}
		return retorno;
	}

	@Override
	public Boolean topeAlcanzado(ParqueaderoEntidad pe, VehiculoEntidad ve, int tope) {
		return obtenerTopeParqueadero(pe, ve) <= tope;
	}

	@Override
	public Boolean placaEmpiezaConA(String placa) {
		return placa.startsWith(ConstantesParqueadero.PLACA_A);
	}

	@Override
	public Boolean validarIngresoXPlaca(String placa) {
		Boolean retorno = true;
		Boolean placaA = placaEmpiezaConA(placa);
		Boolean vDia = Validacion.diaNoLunesoDomingo(Calendar.getInstance());
		if (placaA && vDia) {
			retorno = false;
		}
		return retorno;
	}

	@Override
	public Tarifa obtenerTarifa(ParqueaderoEntidad pe, VehiculoEntidad ve) {
		Tarifa t = new Tarifa();
		TarifaXParqueaderoEntidad txp = txpBO.obtenerEntidadXTipoYParqueadero(true, pe, ve.getTipo());
		if (txp != null) {
			t.setCodigoParqueadero(txp.getParqueadero().getCodigoParqueadero());
			t.setTipo(txp.getTipoVehiculo().getNombre());
			t.setValorDia(txp.getValorDia());
			t.setValorHora(txp.getValorHora());
		} else {
			t.setTipo(TipoVehiculoEnum.MOTO);
			t.setValorDia(new BigDecimal(ConstantesParqueadero.DIA_MOTO));
			t.setValorHora(new BigDecimal(ConstantesParqueadero.HORA_MOTO));
		}
		return t;
	}

	@Override
	public BigDecimal obtenerCobro(Date fechaInicial, Date fechaFinal, Tarifa t, VehiculoEntidad ve) {
		long horas = Validacion.obtenerHorasParqueo(fechaInicial, fechaFinal);
		long dias = horas / ConstantesParqueadero.HORAS_DIA;
		long hs = horas % ConstantesParqueadero.HORAS_DIA;
		BigDecimal cd = BigDecimal.valueOf(dias).multiply(t.getValorDia());
		BigDecimal ch = BigDecimal.valueOf(hs).multiply(t.getValorHora());
		BigDecimal ce = BigDecimal.ZERO;
		if (ve.getCilindraje() > ConstantesParqueadero.TOPE_CILIN) {
			ce = BigDecimal.valueOf(ConstantesParqueadero.CC_MOTO);
		}
		return cd.add(ch).add(ce);
	}

}
