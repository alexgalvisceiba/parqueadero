package co.com.ceiba.parqueadero.constantes;

public final class ConstantesParqueadero {

	public static final String MI_PARQUEADERO = "Mi Parqueadero";
	public static final String VACIO = "";
	public static final String PLACA_A = "A";
	public static final int DOMINGO = 0;
	public static final int LUNES = 1;
	public static final String NO_PUEDE_INGRESAR = "No puede ingresar porque no es un día hábil";
	public static final String NO_VALIDO = "El parqueadero no recibe ese tipo de vehiculo";
	public static final String NO_CUPO = "No hay cupo disponible en el parqueadero";
	// Valores Carro
	public static final int TOPE_CARRO = 20;
	public static final int HORA_CARRO = 1000;
	public static final int DIA_CARRO = 8000;
	public static final long TOPE_HORA = 9;
	// Valores Moto
	public static final int TOPE_MOTO = 10;
	public static final int HORA_MOTO = 500;
	public static final int DIA_MOTO = 600;
	public static final int CC_MOTO = 2000;
	public static final long TOPE_CILIN = 500;
	
	public static final int HORAS_DIA = 24;
	
	// Configuración
	public static final String UTF_8 = "UTF-8";
	public static final String C_MENSAJES = "classpath:i18n/mensajes";
	public static final String C_LANG = "lang";
	public static final String PACK_REPO = "co.com.ceiba.parqueadero.persistencia.repositorio";
	public static final String PACK_CONTROL = "co.com.ceiba.parqueadero.controlador";
	public static final String PACK_SERV = "co.com.ceiba.parqueadero.servicio.impl";
	public static final String PACK_CONF = "co.com.ceiba.parqueadero.persistencia.conexion";
	public static final String PACK_COMP1 = "co.com.ceiba.parqueadero.dominio";
	public static final String PACK_COMP2 = "co.com.ceiba.parqueadero.converter";

	private ConstantesParqueadero() {

	}
}
