package co.com.ceiba.parqueadero.dominio.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Cifrado {

	private static final String SHA256 = "SHA-256";
	private static final Logger LOGGER = LogManager.getLogger(Cifrado.class);

	/**
	 * Constructor por defecto.
	 */
	private Cifrado() {

	}

	/**
	 * Permite cifrar un dato.
	 * @param dato
	 * @return
	 */
	public static String cifrarDato(String dato) {
		String retorno = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(SHA256);
			byte[] hash = digest.digest(dato.getBytes(StandardCharsets.UTF_8));
			retorno = Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return retorno;
	}
}
