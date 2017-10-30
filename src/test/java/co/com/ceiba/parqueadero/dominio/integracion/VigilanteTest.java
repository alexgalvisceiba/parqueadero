package co.com.ceiba.parqueadero.dominio.integracion;

import static org.junit.Assert.fail;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.parqueadero.constantes.ConstantesParqueadero;
import co.com.ceiba.parqueadero.dominio.Vigilante;
import co.com.ceiba.parqueadero.dominio.excepcion.IngresoException;
import co.com.ceiba.parqueadero.dominio.util.Validacion;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.TipoVehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntidad;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.TipoVehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VigilanteTest {

	private TipoVehiculoTestDataBuilder tvdb;
	private VehiculoTestDataBuilder vdb;
	private ParqueaderoTestDataBuilder pdb;
	private TipoVehiculoEntidad tv;
	private VehiculoEntidad v;
	private ParqueaderoEntidad p;
	@Autowired
	private Vigilante vi;

	@Before
	public void setUp() {
		// arrange
		tvdb = new TipoVehiculoTestDataBuilder();
		vdb = new VehiculoTestDataBuilder();
		pdb = new ParqueaderoTestDataBuilder();
		p = pdb.build();
		tv = tvdb.build();
		v = vdb.build().conTipo(tv);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void registrarIngresoTest() {
		// arrange
		VehiculoEntidad ve = v;
		ve.conPlaca(VehiculoTestDataBuilder.PAR_PLACA2);
		// Vigilante vi = new Vigilante();
		// act
		Boolean b = vi.registrarIngreso(p.getNombre(), ve.getPlaca(), ve.getTipo().getNombre().name(),
				String.valueOf(ve.getCilindraje()));
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void registrarIngresoNoValidoTest() {
		// arrange
		VehiculoEntidad ve = v;
		// act
		try {
			Vigilante vi = new Vigilante();
			vi.registrarIngreso(p.getNombre(), ve.getPlaca(), null, String.valueOf(ve.getCilindraje()));
			fail();
		} catch (IngresoException e) {
			// assert
			Assert.assertEquals(ConstantesParqueadero.NO_VALIDO, e.getMessage());
		}
	}

	@Test
	@Rollback(true)
	public void registrarIngresoDiaHabilTest() {
		// arrange
		VehiculoEntidad ve = v;
		ve.conPlaca(VehiculoTestDataBuilder.PAR_PLACA);
		Boolean dia = Validacion.diaNoLunesoDomingo(Calendar.getInstance());
		Vigilante vi = new Vigilante();
		if (dia) {
			// act
			try {
				vi.registrarIngreso(p.getNombre(), ve.getPlaca(), ve.getTipo().getNombre().name(),
						String.valueOf(ve.getCilindraje()));
				fail();
			} catch (IngresoException e) {
				// assert
				Assert.assertEquals(ConstantesParqueadero.NO_PUEDE_INGRESAR, e.getMessage());
			}
		} else {
			// act
			Boolean b = vi.registrarIngreso(p.getNombre(), ve.getPlaca(), ve.getTipo().getNombre().name(),
					String.valueOf(ve.getCilindraje()));
			// assert
			Assert.assertTrue(b);
		}
	}

}
