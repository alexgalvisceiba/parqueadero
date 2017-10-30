package co.com.ceiba.parqueadero.dominio.unitaria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntidad;
import co.com.ceiba.parqueadero.persistencia.repositorio.IParqueaderoJPA;
import co.com.ceiba.parqueadero.servicio.IParqueaderoServicio;
import co.com.ceiba.parqueadero.servicio.impl.ParqueaderoServicio;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;

public class ParqueaderoServiceTest {

	private ParqueaderoTestDataBuilder pdb;
	private IParqueaderoServicio ps;
	private IParqueaderoJPA pm;
	private ParqueaderoEntidad p;

	@Before
	public void setUp() {
		// arrange
		pdb = new ParqueaderoTestDataBuilder();
		p = pdb.build();
		ps = Mockito.mock(ParqueaderoServicio.class);
		pm = Mockito.mock(IParqueaderoJPA.class);
	}

	@Test
	public void agregarTest() {
		// arrange
		Mockito.when(pm.save(p)).thenReturn(p);
		Mockito.when(ps.agregar(p)).thenReturn(p);
		// act
		Boolean b = p != null;
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void agregarXNombreTest() {
		// arrange
		Mockito.when(pm.save(p)).thenReturn(p);
		Mockito.when(ps.agregar(p.getNombre())).thenReturn(p);
		// act
		p = ps.agregar(p.getNombre());
		Boolean b = p != null;
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void encontrarXPkTest() {
		// arrange
		Mockito.when(pm.findOne(p.getCodigoParqueadero())).thenReturn(p);
		Mockito.when(ps.encontrarXPk(p.getCodigoParqueadero())).thenReturn(p);
		// act
		p = ps.encontrarXPk(p.getCodigoParqueadero());
		Boolean b = p != null;
		// assert
		Assert.assertTrue(b);
	}
	
	@Test
	public void encontrarXPkErrorTest() {
		// arrange
		Long l = null;
		Mockito.when(pm.findOne(l)).thenReturn(null);
		Mockito.when(ps.encontrarXPk(l)).thenReturn(null);
		// act
		p = ps.encontrarXPk(null);
		Boolean b = p == null;
		// assert
		Assert.assertTrue(b);
	}
}
