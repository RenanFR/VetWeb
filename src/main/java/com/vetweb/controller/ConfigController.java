package com.vetweb.controller;
// @author Maria Jéssica

import com.vetweb.dao.ConfigDAO;

import com.vetweb.model.Clinica;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller @Transactional  @RequestMapping("/config")//Caminho base da Controller
public class ConfigController {
	
    @Autowired
    private ConfigDAO configDAO;
    
    private static final Logger LOGGER = Logger.getLogger(ConfigController.class);
    @RequestMapping(value = "/cadastroClinica", method = RequestMethod.GET)//URL para o método. Composto por caminho base junto a este RequestMapping
    public ModelAndView cadClinica(@ModelAttribute("clinica") Clinica c){//Atributo de modelo a ser recebido no formulário
        ModelAndView modelAndView = new ModelAndView("config/cadastroClinica");//Caminho para a visão (Página)
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarClinica/{cnpj}", method = RequestMethod.GET)//URL para o método. Composto por caminho base junto a este RequestMapping
    public ModelAndView cadClinica(@PathVariable("cnpj") String cnpj){//Atributo de modelo a ser recebido no formulário
        ModelAndView modelAndView = new ModelAndView("config/cadastroClinica");//Caminho para a visão (Página)
        modelAndView.addObject("clinica", configDAO.clinicaPorCnpj(cnpj));
        return modelAndView;
    }
    
    @RequestMapping(value = "/addClinica", method = RequestMethod.POST)
    public ModelAndView addClinica(@ModelAttribute("clinica") Clinica clinica){
        ModelAndView modelAndView = new ModelAndView("redirect:detalhesClinica/" + clinica.getCnpj());
        configDAO.salvarClinica(clinica);
        LOGGER.info((clinica.getCnpj() + " inserida na base de dados com sucesso. ").toUpperCase());
        return modelAndView;
    }
    
    @RequestMapping(value = "/detalhesClinica/{cnpj}", method = RequestMethod.GET)
    public ModelAndView detalhesClinica(@PathVariable("cnpj") String cnpj){
        ModelAndView modelAndView = new ModelAndView("config/clinica");
        modelAndView.addObject("clinica", configDAO.clinicaPorCnpj(cnpj));
        return modelAndView;
    }
    
    @ExceptionHandler//Tratamento de exceções da Controller Spring
    @ResponseStatus(HttpStatus.BAD_REQUEST)//Responde p/ resposta Http BAD REQUEST
    public void handle(Exception exception) {
        LOGGER.error(exception);
    }    
}
