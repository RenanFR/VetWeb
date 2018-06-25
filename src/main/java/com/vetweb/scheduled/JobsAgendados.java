package com.vetweb.scheduled;

//@author renan.rodrigues@metasix.com.br

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
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
import com.vetweb.model.Atendimento;
import com.vetweb.model.Pessoa;
import com.vetweb.model.Proprietario;
import com.vetweb.service.EmailService;

@Component
@Transactional
@EnableScheduling
public class JobsAgendados {
	
	@Autowired
	private ProprietarioDAO proprietarioDAO;
	
	@Autowired
	private AtendimentoDAO atendimentoDAO;
	
	@Autowired
	private ProntuarioDAO prontuarioDAO; 
	
	@Autowired
	private EmailService emailService;
	
	private static final Logger LOGGER = Logger.getLogger(JobsAgendados.class);
	
	private static final long MINUTO = 60000;
	
	private static final long HORA = 3600000;
	
    @Scheduled(fixedDelay = HORA)
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
    
    @Scheduled(fixedDelay = HORA)
    public void verificacaoRetornoAtendimento() {
    	atendimentoDAO
    		.listar()
    		.stream()
    		.filter(ate -> 
    			LocalDate.parse(ate.getDataAtendimento(),DateTimeFormatter.ofPattern("dd/MM/yyyy")).plus(ate.getTipoDeAtendimento().getFrequencia())
    			.isEqual(LocalDate.now()))
    		.forEach(ate -> this.notificaRetornoAtendimento(ate));
    }
    
    private void notificaRetornoAtendimento(Atendimento atendimento) {
    	Pessoa pessoaDestinatario = prontuarioDAO.buscarProntuarioDoAtendimento(atendimento).getAnimal().getProprietario();
    	StringBuilder mensagemRetorno = new StringBuilder();
    	mensagemRetorno.append("O RETORNO DO ATENDIMENTO "
    				+ atendimento.getTipoDeAtendimento().getNome() + " FEITO EM " + atendimento.getDataAtendimento() + " E HOJE. FAVOR COMPARECER A CLINICA. ");
    	emailService.enviar(pessoaDestinatario, mensagemRetorno.toString(), "RETORNO DE ATENDIMENTO");
    }
    
}
