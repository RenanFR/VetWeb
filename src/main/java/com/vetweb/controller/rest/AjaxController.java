package com.vetweb.controller.rest;
//@author renan.rodrigues@metasix.com.br

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vetweb.dao.AgendamentoDAO;
import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.AtendimentoDAO;
import com.vetweb.dao.ProntuarioDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.model.Agendamento;
import com.vetweb.model.Animal;
import com.vetweb.model.OcorrenciaAtendimento;
import com.vetweb.model.OcorrenciaExame;
import com.vetweb.model.OcorrenciaFactory;
import com.vetweb.model.OcorrenciaPatologia;
import com.vetweb.model.OcorrenciaVacina;
import com.vetweb.model.Raca;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

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
    
    @Autowired
    private ProprietarioDAO proprietarioDAO;
    
    @Autowired
    private OcorrenciaFactory ocorrenciaFactory;
    
    @Autowired
    private AgendamentoDAO agendamentoDAO;
    
    @RequestMapping(value = "/editarAtendimento/{atendimentoId}", method = RequestMethod.GET)
    public OcorrenciaAtendimento atendimentoParaEdicao(@PathVariable("atendimentoId") final Long atendimentoId) {
    	OcorrenciaAtendimento atendimento = atendimentoDAO.buscarPorId(atendimentoId);
    	return atendimento;
    }
    
    @RequestMapping(value = "/editar-ocorrencia/exame/{id}", method = RequestMethod.GET)
    public OcorrenciaExame editarOcorrenciaExame(@PathVariable("id") final Long codigoExame) {
    	OcorrenciaExame ocorrenciaExame = prontuarioDAO.buscarOcorrenciaExame(codigoExame);
    	return ocorrenciaExame;
    }
    
    @RequestMapping(value = "/editarProntuarioPatologia/{prontuarioPatologiaId}", method = RequestMethod.GET)
    public OcorrenciaPatologia prontuarioPatologiaParaEdicao(@PathVariable("prontuarioPatologiaId") final Long prontuarioPatologiaId) {
    	OcorrenciaPatologia prontuarioPatologia = prontuarioDAO.buscarOcorrenciaPatologia(prontuarioPatologiaId);
    	return prontuarioPatologia;
    }
    
    @RequestMapping(value = "/editarProntuarioVacina/{prontuarioVacinaId}", method = RequestMethod.GET)
    public OcorrenciaVacina ProntuarioVacinaParaEdicao(@PathVariable("prontuarioVacinaId") final Long prontuarioVacinaId) {
    	OcorrenciaVacina prontuarioVacina = prontuarioDAO.buscarOcorrenciaVacina(prontuarioVacinaId);
    	return prontuarioVacina;
    }
    
    @RequestMapping(value = "/atualizaStatusPagoAtendimento/{atendimentoId}", method = RequestMethod.GET)
    public boolean atualizaStatusPagamento(@PathVariable("atendimentoId") final Long atendimentoId) {
    	OcorrenciaAtendimento atendimento = atendimentoDAO.buscarPorId(atendimentoId);
    	atendimento.setPago(!atendimento.isPago());
    	atendimentoDAO.salvar(atendimento);
    	return atendimento.isPago();
    }
    
    @RequestMapping(value = "/atualizaStatusPagoVacina/{prontuarioVacinaId}", method = RequestMethod.GET)
    public boolean atualizaStatusPagamentoVacina(@PathVariable("prontuarioVacinaId") final Long prontuarioVacinaId) {
    	OcorrenciaVacina vacina = prontuarioDAO.buscarOcorrenciaVacina(prontuarioVacinaId);
    	vacina.setPago(!vacina.isPago());
    	return vacina.isPago();    	
    }
    
    @RequestMapping(value = "/atualizaStatusPagoExame/{exameId}", method = RequestMethod.GET)
    public boolean atualizaStatusPagoExame(@PathVariable("exameId") final Long exameId) {
    	OcorrenciaExame ocorrenciaExame = prontuarioDAO.buscarOcorrenciaExame(exameId);
    	ocorrenciaExame.setPago(!ocorrenciaExame.isPago());
    	return ocorrenciaExame.isPago();
    }
    
    @RequestMapping(value = "/racasPorEspecie/{especie}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Raca> racasPorEspecie(@PathVariable("especie")String especie){
        return animalDAO.buscarRacasPorEspecie(especie);
    }    
    
    @RequestMapping(value = "/modeloPorTipoDeAtendimento/{tipoDeAtendimento}", method = RequestMethod.GET)
    public @ResponseBody String modeloDoTipoDeAtendimento(@PathVariable("tipoDeAtendimento") String nomeTipoAtendimento) {
        return atendimentoDAO.buscarModeloDoTipoDeAtendimento(nomeTipoAtendimento);
    }
    
    @RequestMapping(value = "/animaisPorCliente/{codigoCliente}", method = RequestMethod.GET)
    public @ResponseBody List<Animal> buscarAnimaisDoCliente(@PathVariable("codigoCliente") Long codigoCliente) {
    	return proprietarioDAO
    			.buscarAnimaisDoCliente(codigoCliente);
    }
    
    @RequestMapping(value = "/ocorrencia/{codigoOcorrencia}", method = RequestMethod.GET)
    public Agendamento remarcarOcorrencia(@PathVariable("codigoOcorrencia") Long idOcorrencia, 
    		@RequestParam("tipoOcorrencia") String tipoOcorrencia,
    		@RequestParam("dataHoraInicial") LocalDateTime dataHoraInicial,
    		@RequestParam("dataHoraFinal") LocalDateTime dataHoraFinal) {
    	Agendamento ocorrenciaAgendamento = agendamentoDAO.buscarPorIdOcorrencia(idOcorrencia);
    	if (ocorrenciaAgendamento != null) {
    		ocorrenciaAgendamento.setTipo(TipoOcorrenciaProntuario.valueOf(tipoOcorrencia));
    		ocorrenciaAgendamento.setDataHoraInicial(dataHoraInicial);
    		ocorrenciaAgendamento.setDataHoraFinal(dataHoraInicial);
    		agendamentoDAO.salvar(ocorrenciaAgendamento);
    		return ocorrenciaAgendamento;
    	} else {
    		OcorrenciaProntuario ocorrenciaProntuario = ocorrenciaFactory.getOcorrencia(tipoOcorrencia, idOcorrencia);
    		Agendamento agendamento = new Agendamento();
    		agendamento.setOcorrencia(ocorrenciaProntuario);
    		agendamento.setDataHoraInicial(dataHoraInicial);
    		agendamento.setDataHoraFinal(dataHoraFinal);
    		agendamento.setTipo(TipoOcorrenciaProntuario.valueOf(tipoOcorrencia));
    		agendamentoDAO.salvar(agendamento);
    		return agendamento;
    		
    	}
    }
    
}
