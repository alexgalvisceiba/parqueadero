package co.com.ceiba.parqueadero;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import co.com.ceiba.parqueadero.constantes.ConstantesParqueadero;
import co.com.ceiba.parqueadero.dominio.util.Cifrado;

@SpringBootApplication
@ComponentScan({ ConstantesParqueadero.PACK_REPO, ConstantesParqueadero.PACK_CONTROL, ConstantesParqueadero.PACK_SERV,
		ConstantesParqueadero.PACK_CONF, ConstantesParqueadero.PACK_COMP1, ConstantesParqueadero.PACK_COMP2 })
public class ParqueaderoApplication extends WebMvcConfigurerAdapter {

	/**
	 * Logger de la clase.
	 */
	private static final Logger LOGGER = Logger.getLogger(ParqueaderoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
		LOGGER.info(Cifrado.cifrarDato("postgres"));
	}

	// Extenciones de WebMvcConfigurerAdapter

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(ConstantesParqueadero.C_MENSAJES);
		messageSource.setDefaultEncoding(ConstantesParqueadero.UTF_8);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName(ConstantesParqueadero.C_LANG);
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
