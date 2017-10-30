package co.com.ceiba.parqueadero.dominio.integracion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.enums.TipoVehiculoEnum;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.ITipoVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;
import co.com.ceiba.parqueadero.testdatabuilder.TipoVehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipoVehiculoServiceTest {

	@Autowired
	private ITipoVehiculoServicio tvs;
	private TipoVehiculoTestDataBuilder tvdb;
	private TipoVehiculoEntidad tv;

	@Before
	public void setUp() {
		// arrange
		tvdb = new TipoVehiculoTestDataBuilder();
		tv = tvdb.build();
	}

	@Test
	@Rollback(true)
	public void agregarTest() {
		// act
		tv = tvs.agregar(tv);
		// assert
		Assert.assertTrue(tv != null);
	}

	@Test
	@Rollback(true)
	public void encontrarXTipoTest() {
		String nombre = TipoVehiculoTestDataBuilder.PAR_TIPO1.name();
		// act
		TipoVehiculoEntidad tve = tvs.encontrarXTipo(nombre);
		Boolean b = tve != null;
		// assert
		Assert.assertTrue(b);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void encontrarXPkErrorTest() {
		// act
		tvs.encontrarXPk(null);
		// assert
		Assert.fail();
	}

	@Test
	@Rollback(true)
	public void actualizarTest() {
		TipoVehiculoEnum tven = TipoVehiculoTestDataBuilder.PAR_TIPO2;
		// act
		TipoVehiculoEntidad tve = tvs.actualizar(tv.conNombre(tven));
		Boolean b = tve != null && tve.getNombre().equals(tven);
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void consultarTest() {
		// act
		RespuestaRest<TipoVehiculoEntidad> rrp = tvs.consultar("1");
		// assert
		Assert.assertTrue(rrp != null && rrp.getCodigo() == RespuestaRest.EXITO);
	}

	@Test
	public void consultarErrorTest() {
		// act
		RespuestaRest<TipoVehiculoEntidad> rrp = tvs.consultar("nada");
		// assert
		Assert.assertTrue(rrp != null && rrp.getCodigo() == RespuestaRest.ERROR);
	}

}
