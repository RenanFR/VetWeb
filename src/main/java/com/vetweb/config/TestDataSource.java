package com.vetweb.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestDataSource {
	
	@Bean
	@Profile("testProfile")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/vetweb_test");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;		
	}

}
