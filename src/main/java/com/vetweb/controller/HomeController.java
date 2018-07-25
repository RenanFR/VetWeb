package com.vetweb.controller;
//@author renan.rodrigues@metasix.com.br

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.ConfigDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.dao.RelatorioDAO;

@Controller
public class HomeController {
	
    @Autowired
    private ProprietarioDAO proprietarioDAO;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ConfigDAO configDAO;
    
    @Autowired
    private RelatorioDAO relatorioDAO;
    
    @RequestMapping("/")
    public ModelAndView index (){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("quantidadeClientes", proprietarioDAO.quantidadeRegistros());
        modelAndView.addObject("quantidadeAnimais", animalDAO.quantidadeRegistros());
        modelAndView.addObject("urlClinica", !configDAO.clinica().isPresent()? "/config/cadastroClinica" : "/config/detalhesClinica/" + configDAO.clinica().get().getRazaoSocial());
        modelAndView.addObject("totalPendente", relatorioDAO.contasAReceber());
        modelAndView.addObject("clientesDevedores", proprietarioDAO.getClientesEmDebito().stream().count());
        return modelAndView;
    }
    
    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }
    
    @RequestMapping("/fail")
    public String failLogin(){
        return "exception/401";
    }    
    
}
