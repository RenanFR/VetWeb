package com.vetweb.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("exames")
public class ExameController {
	
	@RequestMapping("cadastro")
	private ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("exame/cadastroExame");
		return modelAndView;
	}

}
