package com.vetweb.controller.advice;
// @author Maria Jéssica

import static com.vetweb.controller.AnimalController.modelDML;
import com.vetweb.dao.AnimalDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@ControllerAdvice
    //Controladora genérica com parâmetros de inicialização, tratamento de exceções, etc. compartilhadas por todas as controladoras
public class ExceptionController {
	
    @Autowired
    private AnimalDAO animalDAO;
    
    private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);
    
    @ExceptionHandler(DataIntegrityViolationException.class)//Irá cair nesse método de tratamento de exceção sempre que der DataIntegrityViolationException
    public ModelAndView handleViolationException(HttpServletRequest request, ConstraintViolationException violationException){
        ModelAndView modelAndView = null;
        if(modelDML.equals("Especie")){
            modelAndView = new ModelAndView("animal/especies");//Caminho da página de erro para esse tipo de exceção
            modelAndView.addObject("especies", animalDAO.especies());
        } else if(modelDML.equals("Raca")) {
            modelAndView = new ModelAndView("animal/racas");//Caminho da página de erro para esse tipo de exceção
            modelAndView.addObject("racas", animalDAO.racas());
        } else if(modelDML.equals("Pelagem")){
            modelAndView = new ModelAndView("animal/pelagens");//Caminho da página de erro para esse tipo de exceção
            modelAndView.addObject("pelagens", animalDAO.pelagens());
        } else {
            modelAndView = new ModelAndView("index");
        }
        modelAndView.addObject("mensagemErro", violationException.getSQLException().getMessage());//Adiciona mais detalhes sobre erro para exibir na página p/ usuário
        return modelAndView;
    }
    
//    @ExceptionHandler @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handle(Exception exception) {
//        LOGGER.error(exception);
//        return "index";
//    }
    
    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointer(NullPointerException npe) {
        LOGGER.error(npe);
        npe.printStackTrace();
    }
    
}
