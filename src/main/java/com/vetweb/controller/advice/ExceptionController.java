package com.vetweb.controller.advice;
//@author renan.rodrigues@metasix.com.br

import static com.vetweb.controller.AnimalController.modelDML;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.hibernate.exception.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vetweb.dao.AnimalDAO;
@EnableWebMvc
@ControllerAdvice
public class ExceptionController {
	
    @Autowired
    private AnimalDAO animalDAO;
    
    private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleViolationException(HttpServletRequest request, ConstraintViolationException violationException){
        ModelAndView modelAndView = null;
        if(modelDML.equals("Especie")){
            modelAndView = new ModelAndView("animal/especies");
            modelAndView.addObject("especies", animalDAO.especies());
        } else if(modelDML.equals("Raca")) {
            modelAndView = new ModelAndView("animal/racas");
            modelAndView.addObject("racas", animalDAO.racas());
        } else if(modelDML.equals("Pelagem")){
            modelAndView = new ModelAndView("animal/pelagens");
            modelAndView.addObject("pelagens", animalDAO.pelagens());
        } else {
            modelAndView = new ModelAndView("index");
        }
        modelAndView.addObject("mensagemErro", violationException.getSQLException().getMessage());
        return modelAndView;
    }
    
    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointer(NullPointerException npe) {
        LOGGER.error(npe);
        npe.printStackTrace();
    }
    
}
