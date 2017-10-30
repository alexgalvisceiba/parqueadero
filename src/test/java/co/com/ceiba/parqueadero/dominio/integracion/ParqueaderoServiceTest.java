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

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.servicio.IParqueaderoServicio;
import co.com.ceiba.parqueadero.servicio.util.RespuestaRest;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoServiceTest {

	@Autowired
	private IParqueaderoServicio ps;
	private ParqueaderoTestDataBuilder pdb;
	private ParqueaderoEntidad p;

	@Before
	public void setUp() {
		// arrange
		pdb = new ParqueaderoTestDataBuilder();
		p = pdb.build();
	}

	@Test
	@Rollback(true)
	public void agregarTest() {
		// act
		p = ps.agregar(p);
		// assert
		Assert.assertTrue(p != null);
	}

	@Test
	@Rollback(true)
	public void agregarXNombreTest() {
		String nombre = ParqueaderoTestDataBuilder.PAR_NOMBRE;
		// act
		ParqueaderoEntidad pe = ps.agregar(nombre);
		Boolean b = pe != null;
		// assert
		Assert.assertTrue(b);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void encontrarXPkErrorTest() {
		// act
		ps.encontrarXPk(null);
		// assert
		Assert.fail();
	}

	@Test
	@Rollback(true)
	public void actualizarTest() {
		String nombre = ParqueaderoTestDataBuilder.PAR_NOMBRE2;
		// act
		ParqueaderoEntidad pe = ps.agregar(p.conNombre(nombre));
		Boolean b = pe != null && pe.getNombre().equals(nombre);
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void consultarTest() {
		// act
		RespuestaRest<ParqueaderoEntidad> rrp = ps.consultar("1");
		// assert
		Assert.assertTrue(rrp != null && rrp.getCodigo() == RespuestaRest.EXITO);
	}

	@Test
	public void consultarErrorTest() {
		// act
		RespuestaRest<ParqueaderoEntidad> rrp = ps.consultar("nada");
		// assert
		Assert.assertTrue(rrp != null && rrp.getCodigo() == RespuestaRest.ERROR);
	}

}
