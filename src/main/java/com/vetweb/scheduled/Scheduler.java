package com.vetweb.scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.Pessoa;
import com.vetweb.model.Proprietario;
import com.vetweb.service.EmailService;

@Component
@Transactional
@EnableScheduling
public class Scheduler {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	@Autowired
	private AtendimentoDAO atendimentoDAO;
	
	@Autowired
	private ProntuarioDAO prontuarioDAO; 
	
	@Autowired
	private EmailService emailService;
	
	private static final Logger LOGGER = Logger.getLogger(Scheduler.class);
	
	private static final long MINUTO = 60000;
	
	private static final long HORA = 3600000;
	
    @Scheduled(fixedDelay = MINUTO)
    public void verificacaoClientesEmDebito() {
    	List<Proprietario> proprietariosComDebito = proprietarioDAO.buscarClientesEmDebito(); 
    	proprietariosComDebito
    		.stream()
    		.filter(prop -> prop.isAtivo())
    		.peek(prop -> LOGGER.info("INATIVANDO CLIENTE " + prop.getNome()))
    		.forEach(prop ->  {prop.setAtivo(false); proprietarioDAO.salvar(prop);});
    	
    	Set<Proprietario> proprietariosInativosAdimplentes = proprietarioDAO.buscarClientesInativosAdimplentes();
    	proprietariosInativosAdimplentes
    		.stream()
    		.peek(prop -> LOGGER.info("REATIVANDO CLIENTE " + prop.getNome()))
    		.forEach(prop -> {prop.setAtivo(true); proprietarioDAO.salvar(prop); });
    }
    
    @Scheduled(fixedDelay = HORA)
    public void verificacaoRetornoAtendimento() {
    	atendimentoDAO
    		.listarTodos()
    		.stream()
    		.filter(ate -> 
    			LocalDate.of(ate.getData().getYear(), ate.getData().getMonthValue(), ate.getData().getDayOfMonth())
    			.isEqual(LocalDate.now()))
    		.forEach(ate -> this.notificaRetornoAtendimento(ate));
    }
    
    @SuppressWarnings("static-access")
	private void notificaRetornoAtendimento(OcorrenciaAtendimento atendimento) {
    	Pessoa pessoaDestinatario = prontuarioDAO.buscarProntuarioDoAtendimento(atendimento).getAnimal().getProprietario();
    	StringBuilder mensagemRetorno = new StringBuilder();
    	mensagemRetorno.append("O RETORNO DO ATENDIMENTO "
    				+ atendimento.getTipoDeAtendimento().getNome() + " FEITO EM " + atendimento.getData() + " E HOJE. FAVOR COMPARECER A CLINICA. ");
    	emailService.enviar(pessoaDestinatario, mensagemRetorno.toString(), "RETORNO DE ATENDIMENTO");
    }
    
}
