package com.vetweb.controller;
 //@author est.renanfr
import com.vetweb.dao.AnimalDAO;
import com.vetweb.dao.ConfigDAO;
import com.vetweb.dao.ProprietarioDAO;
import com.vetweb.dao.RelatorioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//Indica que a classe e uma Controller. Atende requisições
//@RequestMapping("/home")
public class HomeController {//Respeita o sufixo Controller
	
    @Autowired
    private ProprietarioDAO proprietarioDAO;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Autowired
    private ConfigDAO configDAO;
    
    @Autowired
    private RelatorioDAO relatorioDAO; 
    
//    @RequestMapping("/index")//Indica a rota/URL pela qual o metodo e responsavel (binding)
    @RequestMapping("/")//Indica a rota/URL pela qual o metodo e responsavel (binding)
    public ModelAndView index (){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("quantidadeClientes", proprietarioDAO.quantidadeRegistros());
        modelAndView.addObject("quantidadeAnimais", animalDAO.quantidadeRegistros());
        modelAndView.addObject("clinica", configDAO.clinica());
        modelAndView.addObject("totalPendente", relatorioDAO.contasAReceber());
        return modelAndView;//Retorna a página buscando-a de acordo com as configurações no AppWebConfiguration
    }
//    @RequestMapping("/forbidden")
//    public String forbidden(){
//        return "/login/403";
//    }
}
