package com.vetweb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	public void verificaOcorrenciaAtendimentoParaOCliente() {
		assertTrue(proprietarioDAO.buscarAtendimentosParaOCliente(1L).get(0).getOcorrenciaId() == 1L);
	}
	
	@Test
	public void verificaConsultaDeProntuariosDoCliente() {
		assertTrue(proprietarioDAO.buscarProntuariosParaOCliente(1L).get(0).getProntuarioId() == 1L);
	}
	
	@Test
	public void verificaOcorrenciaVacinaParaOCliente() {
		assertTrue(proprietarioDAO.buscarVacinasParaOCliente(1L).get(0).getOcorrenciaId() == 1L);
	}
	
	@Test
	public void verificaConversaoTextoParaData() {
		assertTrue(LocalDate.parse("28/07/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")).isEqual(LocalDate.of(2018, 07, 28)));
	}
	
	@Test
	public void verificaConsultaDeRacaPorDescricao() {
		assertEquals(" Tucano", animalDAO.buscarRacaPorDescricao("Tucano").getDescricao());
	}
	
	@Test
	public void verificaConsultaDeValoresPendentes() {
		assertTrue(relatorioDAO.buscarTotalAReceber().compareTo(new BigDecimal(120.00)) == 0);
	}
	
	@Ignore
	public void verificaConsultaClientesEmDebito() {
		assertTrue(proprietarioDAO.buscarClientesEmDebito().contains(proprietarioDAO.buscarPorId(6L)));
	}

}
