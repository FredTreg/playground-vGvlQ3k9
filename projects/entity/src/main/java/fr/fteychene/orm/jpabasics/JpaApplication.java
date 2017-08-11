package fr.fteychene.orm.jpabasics;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by fteychene on 14/03/17.
 */
@Configuration
@ComponentScan(basePackages = "fr.fteychene.orm.jpabasics", excludeFilters = @ComponentScan.Filter(JpaApplication.OverrideConfiguration.class))
@PropertySource("classpath:jpa-workshop.properties")
@EnableJpaRepositories("fr.fteychene.orm.jpabasics")
@EnableTransactionManagement
public class JpaApplication {

    public @interface OverrideConfiguration {
    }

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DELEGATOR = "hibernate.ejb.naming_strategy_delegator";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_DBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_IMPORT_FILES = "hibernate.hbm2ddl.import_files";

    @Resource
    private Environment env;

    @Profile("!embeddedDatasource")
    @Bean(destroyMethod = "close")
    public DataSource comboDataSource() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        cpds.setDriverClass(env.getProperty(PROPERTY_NAME_DATABASE_DRIVER));
        cpds.setJdbcUrl(env.getProperty(PROPERTY_NAME_DATABASE_URL));
        cpds.setUser(env.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
        cpds.setPassword(env.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);

        return cpds;
    }

    @Profile("embeddedDatasource")
    @Bean
    public DataSource embeddedDatasource(@Qualifier("scripts") String[] scripts) throws Exception {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2.HSQL)
                .addScripts(scripts)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(datasource);
        entityManagerFactoryBean.setPersistenceXmlLocation("META-INF/jpa-persistance.xml");
        entityManagerFactoryBean.setPersistenceUnitName("jpa-workshop");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());


        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws Exception {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DELEGATOR, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DELEGATOR));
        properties.put(PROPERTY_NAME_HIBERNATE_DBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DBM2DDL_AUTO));
        properties.put(PROPERTY_NAME_HIBERNATE_IMPORT_FILES, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_IMPORT_FILES));
        return properties;
    }
}
