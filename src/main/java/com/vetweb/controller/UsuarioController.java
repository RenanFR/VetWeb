package com.vetweb.controller;
//@author renan.rodrigues@metasix.com.br

import com.vetweb.model.auth.Usuario;
import com.vetweb.dao.auth.UsuarioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("/usuarios")
public class UsuarioController {
	
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @RequestMapping("/cadastro")
    public String frmCadastro(){
        return "login/cadastroUsuario";
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(Usuario usuario){
        ModelAndView modelAndView = new ModelAndView("login/cadastroUsuario");
        try{
            usuarioDAO.salvar(usuario);
            modelAndView.addObject("result", "Usuário cadastrado com sucesso.   ".toUpperCase());
        } catch (Exception exception){
            modelAndView.addObject("result", "Erro ao cadastrar usuário.    ".toUpperCase());
        }
        return modelAndView;
    }
    
    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }
    
    @RequestMapping("/fail")
    public String failLogin(){
        return "login/fail";
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception exception){
        exception.printStackTrace();
    }
    
}
