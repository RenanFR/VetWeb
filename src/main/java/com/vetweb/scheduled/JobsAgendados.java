/**
 * @author renanfr
 *
 */
package com.vetweb.scheduled;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.Proprietario;

@Component
@Transactional
@EnableScheduling
public class JobsAgendados {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	private static final Logger LOGGER = Logger.getLogger(JobsAgendados.class);
	
	private static final long MINUTO = 60000;
	
    @Scheduled(fixedDelay = MINUTO/4)
    public void verificacaoClientesEmDebito() {
    	Set<Proprietario> proprietariosComDebito = proprietarioDAO.getClientesEmDebito(); 
    	proprietariosComDebito
    		.stream()
    		.filter(prop -> prop.isAtivo())
    		.peek(prop -> LOGGER.info("INATIVANDO CLIENTE " + prop.getNome()))
    		.forEach(prop ->  {prop.setAtivo(false); proprietarioDAO.salvar(prop);});
    	
    	Set<Proprietario> proprietariosInativosAdimplentes = proprietarioDAO.getClientesInativosAdimplentes();
    	proprietariosInativosAdimplentes
    		.stream()
    		.peek(prop -> LOGGER.info("REATIVANDO CLIENTE " + prop.getNome()))
    		.forEach(prop -> {prop.setAtivo(true); proprietarioDAO.salvar(prop); });
    }
    
}
