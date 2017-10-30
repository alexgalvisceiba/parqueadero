package co.com.ceiba.parqueadero.testdatabuilder;

import org.mockito.Mockito;

import co.com.ceiba.parqueadero.dominio.Vigilante;
import co.com.ceiba.parqueadero.persistencia.repositorio.IParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.IRegistroJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITarifaXParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITipoVehiculoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.ITopeXParqueaderoJPA;
import co.com.ceiba.parqueadero.persistencia.repositorio.IVehiculoJPA;

public class VigilanteTestDataBuilder {

	private IParqueaderoJPA pBO;
	private IRegistroJPA rBO;
	private IVehiculoJPA vBO;
	private ITarifaXParqueaderoJPA txpBO;
	private ITopeXParqueaderoJPA toxpBO;
	private ITipoVehiculoJPA tvBO;

	public static final IParqueaderoJPA PBO = Mockito.mock(IParqueaderoJPA.class);
	public static final IRegistroJPA RBO = Mockito.mock(IRegistroJPA.class);
	public static final IVehiculoJPA VBO = Mockito.mock(IVehiculoJPA.class);
	public static final ITarifaXParqueaderoJPA TXPBO = Mockito.mock(ITarifaXParqueaderoJPA.class);
	public static final ITopeXParqueaderoJPA TOXPBO = Mockito.mock(ITopeXParqueaderoJPA.class);
	public static final ITipoVehiculoJPA TVBO = Mockito.mock(ITipoVehiculoJPA.class);

	public VigilanteTestDataBuilder() {
		this.pBO = PBO;
		this.rBO = RBO;
		this.vBO = VBO;
		this.txpBO = TXPBO;
		this.toxpBO = TOXPBO;
		this.tvBO = TVBO;
	}

	public Vigilante build() {
		return new Vigilante(pBO, rBO, txpBO, toxpBO, vBO, tvBO);
	}
}
