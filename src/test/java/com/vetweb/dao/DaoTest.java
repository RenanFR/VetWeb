package com.vetweb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vetweb.config.AmazonConfig;
import com.vetweb.config.AppWebConfiguration;
import com.vetweb.config.ConfigJPA;
import com.vetweb.config.DispatcherServlet;
import com.vetweb.config.TestDataSource;
import com.vetweb.config.security.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ConfigJPA.class, DispatcherServlet.class, AppWebConfiguration.class, SecurityConfig.class, TestDataSource.class, AmazonConfig.class})
//@ActiveProfiles("testProfile")
@ActiveProfiles("production")
public class DaoTest {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	@Autowired
	private RelatorioDAO relatorioDAO;
	
	@Autowired
	private AnimalDAO animalDAO;
	
	@Test
	public void verificaConsultaDeRacaPorDescricao() {
		assertEquals(" Tucano", animalDAO.buscarRacaPorDescricao("Tucano").getDescricao());
	}
	
	@Ignore
	public void verificaConsultaDeValoresPendentes() {
		assertEquals(new BigDecimal(10.0), relatorioDAO.buscarTotalAReceber());
	}
	
	@Ignore
	public void verificaConsultaClientesEmDebito() {
		assertTrue(proprietarioDAO.buscarClientesEmDebito().contains(proprietarioDAO.buscarPorId(6L)));
	}

}
