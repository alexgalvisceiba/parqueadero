package co.com.ceiba.parqueadero.dominio.unitaria;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.parqueadero.dominio.util.Validacion;

public class ValidacionTest {

	@Test
	public void diaNoLunesoDomingoFalseTest() {
		// arrange
		Calendar dia = Mockito.mock(Calendar.class);
		Mockito.when(dia.get(Calendar.DAY_OF_WEEK)).thenReturn(1);
		// act
		Boolean b = Validacion.diaNoLunesoDomingo(dia);
		// assert
		Assert.assertFalse(b);
	}

	@Test
	public void diaNoLunesoDomingoTrueTest() {
		// arrange
		Calendar dia = Mockito.mock(Calendar.class);
		Mockito.when(dia.get(Calendar.DAY_OF_WEEK)).thenReturn(4);
		// act
		Boolean b = Validacion.diaNoLunesoDomingo(dia);
		// assert
		Assert.assertTrue(b);
	}

	@Test
	public void obtenerHorasParqueoTest() {
		// arrange
		long expected = 2L;
		Calendar ingreso = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		fin.add(Calendar.HOUR, 2);
		// act
		long r = Validacion.obtenerHorasParqueo(ingreso.getTime(), fin.getTime());
		// assert
		Assert.assertEquals(expected, r);
	}
	
	@Ignore
	public void obtenerHorasParqueoHMTest() {
		// TODO reparar este escenario
		// arrange
		long expected = 3L;
		Calendar ingreso = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		fin.add(Calendar.HOUR, 2);
		fin.add(Calendar.MINUTE, 30);
		// act
		long r = Validacion.obtenerHorasParqueo(ingreso.getTime(), fin.getTime());
		// assert
		Assert.assertEquals(expected, r);
	}

}
