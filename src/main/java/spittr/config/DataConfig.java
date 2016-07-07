package spittr.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages = {"spittr.entities" , "spittr.repositories"})
@PropertySource("classpath:/config/databaseConnection.properties")
@EnableJpaRepositories(basePackages = "spittr.repositories")
@EnableTransactionManagement
public class DataConfig {
	
	private static final Logger LOG = Logger.getLogger(DataConfig.class);
    
    private final String schema = "spittr";

	@Autowired
	private Environment env;
	
	public DataConfig() {
		super();
		LOG.info("|-drb-| == > Constructing...");
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("|-drb-| == > PostConstructing...");
	}

	@Bean
	public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {

            dataSource.setDriverClass(env.getProperty("db." + schema + ".driver"));
            dataSource.setJdbcUrl(env.getProperty("db." + schema + ".url"));
            dataSource.setUser(env.getProperty("db." + schema + ".user"));
            dataSource.setPassword(env.getProperty("db." + schema + ".pass"));
            dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("pool." + schema + ".acquire.increment")));
            dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("pool." + schema + ".init.size")));
            dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("pool." + schema + ".min.size")));
            dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("pool." + schema + ".max.size")));
            dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("pool." + schema + ".idle.test.period")));
            dataSource.setCheckoutTimeout(Integer.parseInt(env.getProperty("pool." + schema + ".checkout.timeout")));
            // hibernate batch size?
            //dataSource.setCheckoutTimeout(Integer.parseInt(env.getProperty("pool." + schema + ".checkout.timeout")));
        }
        catch (PropertyVetoException e) {
            LOG.warn("|-drb-| == > Connecting " + schema + " dataSource failed!");
            e.printStackTrace();
        }
        LOG.info("|-drb-| == > Connecting " + schema + " dataSource to " + dataSource.getJdbcUrl() + " as " + dataSource.getUser());
        return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabase(Database.MYSQL);
		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {

        Properties props = new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate." + schema + ".show_sql"));
        props.put("hibernate.dialect", env.getProperty("hibernate." + schema + ".dialect"));
        props.put("hibernate.format_sql", env.getProperty("hibernate." + schema + ".format_sql"));
        String auto = env.getProperty("hibernate." + schema + ".hbm2ddl.auto");
        if (auto != null) {
            props.put("hibernate." + schema + ".hbm2ddl.auto", auto);
        }

		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("spittr.entities");
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setJpaProperties(props);

		return emf;
	}
	
	@Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
