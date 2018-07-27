package com.vetweb.controller.rest;
//@author renan.rodrigues@metasix.com.br

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.model.Atendimento;
import com.vetweb.model.ProntuarioPatologia;
import com.vetweb.model.ProntuarioVacina;
import com.vetweb.model.Raca;

@RestController
@Transactional
@RequestMapping("/ajax/prontuario")
public class AjaxController {
	
    @Autowired
    private AtendimentoDAO atendimentoDAO;
    
    @Autowired
    private ProntuarioDAO prontuarioDAO;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @RequestMapping(value = "/editarAtendimento/{atendimentoId}", method = RequestMethod.GET)
    public Atendimento atendimentoParaEdicao(@PathVariable("atendimentoId") final Long atendimentoId) {
    	Atendimento atendimento = atendimentoDAO.buscarPorId(atendimentoId);
    	return atendimento;
    }
    
    @RequestMapping(value = "/editarProntuarioPatologia/{prontuarioPatologiaId}", method = RequestMethod.GET)
    public ProntuarioPatologia prontuarioPatologiaParaEdicao(@PathVariable("prontuarioPatologiaId") final Long prontuarioPatologiaId) {
    	ProntuarioPatologia prontuarioPatologia = prontuarioDAO.buscarOcorrenciaDaPatologia(prontuarioPatologiaId);
    	return prontuarioPatologia;
    }
    
    @RequestMapping(value = "/editarProntuarioVacina/{prontuarioVacinaId}", method = RequestMethod.GET)
    public ProntuarioVacina ProntuarioVacinaParaEdicao(@PathVariable("prontuarioVacinaId") final Long prontuarioVacinaId) {
    	ProntuarioVacina prontuarioVacina = prontuarioDAO.buscarOcorrenciaDaVacina(prontuarioVacinaId);
    	return prontuarioVacina;
    }
    
    @RequestMapping(value = "/atualizaStatusPagoAtendimento/{atendimentoId}", method = RequestMethod.GET)
    public boolean atualizaStatusPagamento(@PathVariable("atendimentoId") final Long atendimentoId) {
    	Atendimento atendimento = atendimentoDAO.buscarPorId(atendimentoId);
    	atendimento.setPago(!atendimento.isPago());
    	atendimentoDAO.salvar(atendimento);
    	return atendimento.isPago();
    }
    
    @RequestMapping(value = "/atualizaStatusPagoVacina/{prontuarioVacinaId}", method = RequestMethod.GET)
    public boolean atualizaStatusPagamentoVacina(@PathVariable("prontuarioVacinaId") final Long prontuarioVacinaId) {
    	ProntuarioVacina vacina = prontuarioDAO.buscarOcorrenciaDaVacina(prontuarioVacinaId);
    	vacina.setPago(!vacina.isPago());
    	return vacina.isPago();    	
    }
    
    @RequestMapping(value = "/racasPorEspecie/{especie}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Raca> racasPorEspecie(@PathVariable("especie")String especie){
        return animalDAO.buscarRacasPorEspecie(especie);
    }    
    
    @RequestMapping(value = "/modeloPorTipoDeAtendimento/{tipoDeAtendimento}", method = RequestMethod.GET)
    public @ResponseBody String modeloDoTipoDeAtendimento(@PathVariable("tipoDeAtendimento") String nomeTipoAtendimento) {
        return atendimentoDAO.buscarModeloDoTipoDeAtendimento(nomeTipoAtendimento);
    }    
    
}
