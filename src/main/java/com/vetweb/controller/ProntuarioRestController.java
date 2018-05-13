package com.vetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.model.Atendimento;
import com.vetweb.model.ProntuarioPatologia;
import com.vetweb.model.ProntuarioVacina;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioRestController {//Provê os serviços Rest p/ o prontuário
	
    @Autowired
    private AtendimentoDAO atendimentoDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @RequestMapping(value = "/editarAtendimento/{atendimentoId}", method = RequestMethod.GET)
    public Atendimento atendimentoParaEdicao(@PathVariable("atendimentoId") final Long atendimentoId) {
    	Atendimento atendimento = atendimentoDAO.consultarPorId(atendimentoId);
    	return atendimento;
    }
    
    @RequestMapping(value = "/editarProntuarioPatologia/{prontuarioPatologiaId}", method = RequestMethod.GET)
    public ProntuarioPatologia prontuarioPatologiaParaEdicao(@PathVariable("prontuarioPatologiaId") final Long prontuarioPatologiaId) {
    	ProntuarioPatologia prontuarioPatologia = prontuarioDAO.buscarOcorrenciaPatologiaProntuarioPorId(prontuarioPatologiaId);
    	return prontuarioPatologia;
    }
    
    @RequestMapping(value = "/editarProntuarioVacina/{prontuarioVacinaId}", method = RequestMethod.GET)
    public ProntuarioVacina ProntuarioVacinaParaEdicao(@PathVariable("prontuarioVacinaId") final Long prontuarioVacinaId) {
    	ProntuarioVacina prontuarioVacina = prontuarioDAO.buscarOcorrenciaVacinaProntuarioPorId(prontuarioVacinaId);
    	return prontuarioVacina;
    }
    
}
