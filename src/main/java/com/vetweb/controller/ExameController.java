package com.vetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.dao.ExameDAO;
import com.vetweb.model.Exame;

@Controller
@Transactional
@RequestMapping("/exames")
public class ExameController {
	
	@Autowired
	private ExameDAO exameDAO;
	
	@RequestMapping("form")
	public ModelAndView form(@ModelAttribute("exame") Exame exame) {
		ModelAndView modelAndView = new ModelAndView("exame/cadastroExame");
		return modelAndView;
	}
	
	@PostMapping("/submitForm")
	public ModelAndView submit(@ModelAttribute("exame") Exame exame) {
		ModelAndView modelAndView = new ModelAndView("redirect:/exames");
		exameDAO.salvar(exame);
		return modelAndView;
	}
	
	@GetMapping
	public ModelAndView exames() {
		ModelAndView modelAndView = new ModelAndView("exame/exames");
		modelAndView.addObject("exames", exameDAO.listarTodos());
		return modelAndView;
	}
	
	@GetMapping("remover/{exameId}")
	public ModelAndView removerExame(@PathVariable("exameId") Long exameId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/exames");
		Exame exame = exameDAO.buscarPorId(exameId);
		exameDAO.remover(exame);
		return modelAndView;
	}
	
	@GetMapping("atualizar/{exameId}")
	public ModelAndView atualizarExame(@PathVariable("exameId") Long exameId) {
		ModelAndView modelAndView = new ModelAndView("exame/cadastroExame");
		Exame exame = exameDAO.buscarPorId(exameId);
		modelAndView.addObject("exame", exame);
		return modelAndView;
	}
	

}
