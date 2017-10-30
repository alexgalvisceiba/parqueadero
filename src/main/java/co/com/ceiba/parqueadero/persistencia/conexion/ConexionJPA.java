package co.com.ceiba.parqueadero.persistencia.conexion;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class ConexionJPA {

	@Value("${db.driver}")
	private String dbDriver;

	@Value("${db.password}")
	private String dbPassword;

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String dbUserName;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2Auto;

	@Value("${entitymanager.packagesToScan}")
	private String emPackagesScan;
	
	@Value("${hibernate.c3p0.identity}")
	private String c3p0Identity;

	@Value("${hibernate.c3p0.max_size}")
	private String c3p0MaxSize;

	@Value("${hibernate.c3p0.min_size}")
	private String c3p0MinSize;

	@Value("${hibernate.c3p0.idle_test_period}")
	private String c3p0IdlePeriod;

	@Value("${hibernate.c3p0.helper_threads}")
	private String c3p0HelperThreads;

	@Value("${hibernate.c3p0.acquire_increment}")
	private String c3p0Increment;

	private static final Logger LOGGER = LogManager.getLogger(ConexionJPA.class);

	@Bean
	public ComboPooledDataSource dataSource() {
		// a named datasource is best practice for later jmx monitoring
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(dbDriver);
		} catch (PropertyVetoException pve) {
			LOGGER.error("Cannot load datasource driver (" + dbDriver + ") : " + pve.getMessage());
			return null;
		}
		dataSource.setIdentityToken(c3p0Identity);
		dataSource.setJdbcUrl(dbUrl);
		dataSource.setUser(dbUserName);
		dataSource.setPassword(dbPassword);
		dataSource.setMinPoolSize(Integer.parseInt(c3p0MinSize));
		dataSource.setMaxPoolSize(Integer.parseInt(c3p0MaxSize));
		dataSource.setMaxIdleTime(Integer.parseInt(c3p0IdlePeriod));
		dataSource.setNumHelperThreads(Integer.parseInt(c3p0HelperThreads));
		dataSource.setAcquireIncrement(Integer.parseInt(c3p0Increment));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(emPackagesScan);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", hibernateDialect);
		hibernateProperties.put("hibernate.show_sql", hibernateShowSQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2Auto);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
}