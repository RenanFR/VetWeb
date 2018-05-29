/**
 * @author renanfr
 *
 */
package com.vetweb.scheduled;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vetweb.dao.ProprietarioDAO;

@Component
@Transactional
@EnableScheduling
public class JobsAgendados {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	private static final Logger LOGGER = Logger.getLogger(JobsAgendados.class);
	
    @Scheduled(fixedDelay = 60000)
    public void verificacaoClientesEmDebito() {
    	proprietarioDAO.getClientesEmDebito().stream()
    		.peek(prop -> LOGGER.info("INATIVANDO CLIENTE " + prop.getNome()))
    		.forEach(prop ->  {prop.setAtivo(false); proprietarioDAO.salvar(prop);});
    	
    	proprietarioDAO.getClientesInativosAdimplentes().stream()
    		.peek(prop -> LOGGER.info("REATIVANDO CLIENTE " + prop.getNome()))
    		.forEach(prop -> {prop.setAtivo(true); proprietarioDAO.salvar(prop); });
    }
    
}
