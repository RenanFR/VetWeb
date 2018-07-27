package com.vetweb.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vetweb.config.AppWebConfiguration;
import com.vetweb.config.ConfigJPA;
import com.vetweb.config.DispatcherServlet;
import com.vetweb.config.TestDataSource;
import com.vetweb.config.security.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ConfigJPA.class, DispatcherServlet.class, AppWebConfiguration.class, SecurityConfig.class, TestDataSource.class})
@ActiveProfiles("production")
public class DaoTest {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	@Test
	public void verificaConsultaClientesEmDebito() {
		assertTrue(proprietarioDAO.buscarClientesEmDebito().contains(proprietarioDAO.buscarPorId(6L)));
	}

}
