package com.vetweb.controller;
//	@author renan.rodrigues@metasix.com.br


import com.vetweb.dao.ConfigDAO;
import com.vetweb.model.Clinica;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller @Transactional  @RequestMapping("/config")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ConfigController {
	
    @Autowired
    private ConfigDAO configDAO;
    
    private static final Logger LOGGER = Logger.getLogger(ConfigController.class);
    @RequestMapping(value = "/cadastroClinica", method = RequestMethod.GET)
    public ModelAndView cadClinica(@ModelAttribute("clinica") Clinica c){
        ModelAndView modelAndView = new ModelAndView("config/cadastroClinica");
        return modelAndView;
    }
    
    @RequestMapping(value = "/atualizarClinica/{cnpj}", method = RequestMethod.GET)
    public ModelAndView cadClinica(@PathVariable("cnpj") String cnpj){
        ModelAndView modelAndView = new ModelAndView("config/cadastroClinica");
        modelAndView.addObject("clinica", configDAO.buscarClinicaPorCnpj(cnpj));
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
        modelAndView.addObject("clinica", configDAO.buscarClinicaPorCnpj(cnpj));
        return modelAndView;
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception exception) {
        LOGGER.error(exception);
    }    
    
}
