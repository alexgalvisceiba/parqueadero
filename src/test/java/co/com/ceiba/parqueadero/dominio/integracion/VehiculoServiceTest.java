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

import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.servicio.IVehiculoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoServiceTest {

	@Autowired
	private IVehiculoServicio vs;
	private VehiculoTestDataBuilder vdb;
	private VehiculoEntidad v;

	@Before
	public void setUp() {
		// arrange
		vdb = new VehiculoTestDataBuilder();
		v = vdb.build();
	}

	@Test
	@Rollback(true)
	public void agregarTest() {
		// act
		v = vs.agregar(v);
		// assert
		Assert.assertTrue(v != null);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void encontrarXPkErrorTest() {
		// act
		vs.encontrarXPk(null);
		// assert
		Assert.fail();
	}

	@Test
	@Rollback(true)
	public void actualizarTest() {
		// act
		VehiculoEntidad ve = vs.actualizar(v.conCilindraje(VehiculoTestDataBuilder.PAR_CILIN2));
		Boolean b = ve != null && ve.getCilindraje().equals(VehiculoTestDataBuilder.PAR_CILIN2);
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void consultarTest() {
		// act
		RespuestaRest<VehiculoEntidad> rrp = vs.consultar("1");
		// assert
		Assert.assertTrue(rrp != null && rrp.getCodigo() == RespuestaRest.EXITO);
	}

	@Test
	public void consultarErrorTest() {
		// act
		RespuestaRest<VehiculoEntidad> rrp = vs.consultar("nada");
		// assert
		Assert.assertTrue(rrp != null && rrp.getCodigo() == RespuestaRest.ERROR);
	}

}
