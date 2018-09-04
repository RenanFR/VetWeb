package com.vetweb.config;
// @author renan.rodrigues@metasix.com.br

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class ConfigJPA {
	
	@Autowired
	private Environment environment;
	
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(new String[]{"com.vetweb.model", 
            "com.vetweb.model.auth"});
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(adapter);
        entityManagerFactory.setJpaProperties(properties());
        return entityManagerFactory;
    }
    
    @Bean
    @Profile("production")
    private DataSource dataSource() throws URISyntaxException{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        URI uri = new URI(environment.getProperty("DATABASE_URL"));
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/vetweb_database");
        dataSource.setUrl("jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + "/" + uri.getPath());
        dataSource.setUsername(uri.getUserInfo().split(":")[0]);
//        dataSource.setUsername("postgres");
        dataSource.setPassword(uri.getUserInfo().split(":")[1]);
//        dataSource.setPassword("postgres");
        return dataSource;
    }
    
    private Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
        return properties;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory managerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactory);
        return transactionManager;
    }
    
}
