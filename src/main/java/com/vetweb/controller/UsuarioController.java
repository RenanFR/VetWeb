package com.vetweb.controller;
//@author renan.rodrigues@metasix.com.br

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Transactional
@RequestMapping("/usuarios")
public class UsuarioController {
    
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
