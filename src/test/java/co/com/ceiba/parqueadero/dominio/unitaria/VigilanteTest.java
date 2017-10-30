package co.com.ceiba.parqueadero.dominio.unitaria;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.parqueadero.constantes.ConstantesParqueadero;
import co.com.ceiba.parqueadero.dominio.Vigilante;
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
import co.com.ceiba.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.RegistroTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.TipoVehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VigilanteTestDataBuilder;

public class VigilanteTest {

	private TipoVehiculoTestDataBuilder tvdb;
	private ParqueaderoTestDataBuilder pdb;
	private VehiculoTestDataBuilder vdb;
	private RegistroTestDataBuilder rdb;
	private VigilanteTestDataBuilder vidb;
	private TipoVehiculoEntidad tv;
	private VehiculoEntidad v;
	private ParqueaderoEntidad p;
	private RegistroEntidad r;
	private Vigilante vi;
	private IRegistroJPA rBO;
	private IParqueaderoJPA pBO;
	private IVehiculoJPA vBO;
	private ITipoVehiculoJPA tvBO;
	private ITarifaXParqueaderoJPA txpBO;
	private ITopeXParqueaderoJPA toxpBO;
	private List<ParqueaderoEntidad> lp;

	@Before
	public void setUp() {
		// arrange
		tvdb = new TipoVehiculoTestDataBuilder();
		vdb = new VehiculoTestDataBuilder();
		pdb = new ParqueaderoTestDataBuilder();
		rdb = new RegistroTestDataBuilder();
		vidb = new VigilanteTestDataBuilder();
		tv = tvdb.build();
		v = vdb.build().conTipo(tv);
		p = pdb.build();
		r = rdb.build();
		vi = vidb.build();
		lp = new ArrayList<>();
		lp.add(p);
		rBO = Mockito.mock(IRegistroJPA.class);
		txpBO = Mockito.mock(ITarifaXParqueaderoJPA.class);
		toxpBO = Mockito.mock(ITopeXParqueaderoJPA.class);
		pBO = Mockito.mock(IParqueaderoJPA.class);
		vBO = Mockito.mock(IVehiculoJPA.class);
		tvBO = Mockito.mock(ITipoVehiculoJPA.class);
	}

	@Ignore
	public void registrarIngresoTest() {
		//TODO: lanza nullpointer
		// arrange
		Mockito.when(pBO.findByNombre(p.getNombre())).thenReturn(p);
		Mockito.when(tvBO.obtenerEntidadXNombre(v.getTipo().getNombre())).thenReturn(tv);
		Mockito.when(vBO.findOne(v.getPlaca())).thenReturn(v);
		Mockito.when(rBO.obtenerParqueados(tv)).thenReturn(new ArrayList<>());
		Mockito.when(rBO.save(r)).thenReturn(r);
		// act
		Boolean b = vi.registrarIngreso(p.getNombre(), v.getPlaca(), tv.getNombre().name(),
				String.valueOf(v.getCilindraje()));
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void registrarIngresoNoValidoTest() {
		// arrange
		Mockito.when(rBO.obtenerParqueados(v.getTipo())).thenReturn(new ArrayList<>());
		// act
		try {
			vi.registrarIngreso(p.getNombre(), v.getPlaca(), null, String.valueOf(v.getCilindraje()));
			fail();
		} catch (IngresoException e) {
			// assert
			Assert.assertEquals(ConstantesParqueadero.NO_VALIDO, e.getMessage());
		}
	}

	@Test
	public void registrarIngresoDiaHabilTest() {
		// arrange
		v.conPlaca(VehiculoTestDataBuilder.PAR_PLACA);
		Mockito.when(rBO.obtenerParqueados(v.getTipo())).thenReturn(new ArrayList<>());
		Mockito.when(rBO.obtenerParqueados(v.getTipo())).thenReturn(new ArrayList<>());
		Calendar dia = Mockito.mock(Calendar.class);
		Mockito.when(dia.get(Calendar.DAY_OF_WEEK)).thenReturn(3);
		// act
		try {
			Vigilante vvi = vidb.build();
			vvi.registrarIngreso(p.getNombre(), v.getPlaca(), v.getTipo().getNombre().name(),
					String.valueOf(v.getCilindraje()));
			fail();
		} catch (IngresoException e) {
			// assert
			Assert.assertEquals(ConstantesParqueadero.NO_PUEDE_INGRESAR, e.getMessage());
		}
	}

	@Test
	public void registrarIngresoDiaNoHabilTest() {
		// arrange
		v.conPlaca(VehiculoTestDataBuilder.PAR_PLACA);
		Mockito.when(rBO.obtenerParqueados(v.getTipo())).thenReturn(new ArrayList<>());
		Calendar dia = Mockito.mock(Calendar.class);
		Mockito.when(dia.get(Calendar.DAY_OF_WEEK)).thenReturn(1);
		// act
		try {
			Vigilante vvi = vidb.build();
			vvi.registrarIngreso(p.getNombre(), v.getPlaca(), v.getTipo().getNombre().name(),
					String.valueOf(v.getCilindraje()));
			fail();
		} catch (IngresoException e) {
			// assert
			Assert.assertEquals(ConstantesParqueadero.NO_PUEDE_INGRESAR, e.getMessage());
		}
	}

	@Test
	public void registrarIngresoSinCupoCarroTest() {
		// arrange
		List<RegistroEntidad> lr = new ArrayList<>();
		RegistroTestDataBuilder rdb = new RegistroTestDataBuilder();
		for (int i = 0; i <= ConstantesParqueadero.TOPE_CARRO; i++) {
			lr.add(rdb.conVehiculo(vdb.conPlaca(vdb.generarPlacaAleatoria()).build()).build());
		}
		Mockito.when(rBO.obtenerParqueados(v.getTipo())).thenReturn(lr);
		Mockito.when(pBO.findByNombre(p.getNombre())).thenReturn(p);
		Mockito.when(vBO.findOne(v.getPlaca())).thenReturn(v);
		// act
		try {
			Vigilante vvi = new Vigilante(pBO, rBO, txpBO, toxpBO, vBO, tvBO);
			vvi.registrarIngreso(p.getNombre(), v.getPlaca(), v.getTipo().getNombre().name(),
					String.valueOf(v.getCilindraje()));
			fail();
		} catch (IngresoException e) {
			// assert
			Assert.assertEquals(ConstantesParqueadero.NO_CUPO, e.getMessage());
		}
	}

	@Test
	public void registrarIngresoSinCupoMotoTest() {
		// arrange
		List<RegistroEntidad> lr = new ArrayList<>();
		RegistroTestDataBuilder rdb = new RegistroTestDataBuilder();
		TipoVehiculoEntidad tvv = tv;
		VehiculoEntidad vv = v;
		tvv.conNombre(TipoVehiculoEnum.MOTO);
		vv.conTipo(tvv);
		for (int i = 0; i <= ConstantesParqueadero.TOPE_MOTO; i++) {
			lr.add(rdb.conVehiculo(vdb.conPlaca(vdb.generarPlacaAleatoria()).conTipo(tvv).build()).build());
		}
		Mockito.when(rBO.obtenerParqueados(v.getTipo())).thenReturn(lr);
		Mockito.when(pBO.findByNombre(p.getNombre())).thenReturn(p);
		Mockito.when(vBO.findOne(v.getPlaca())).thenReturn(v);
		// act
		try {
			Vigilante vvi = new Vigilante(pBO, rBO, txpBO, toxpBO, vBO, tvBO);
			vvi.registrarIngreso(p.getNombre(), v.getPlaca(), v.getTipo().getNombre().name(),
					String.valueOf(v.getCilindraje()));
			fail();
		} catch (IngresoException e) {
			// assert
			Assert.assertEquals(ConstantesParqueadero.NO_CUPO, e.getMessage());
		}
	}

}
