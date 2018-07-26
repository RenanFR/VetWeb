package com.vetweb.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("/agendamento")
public class Agendamento {
	
	@GetMapping
	public String agenda() {
		return "agendamento/agendamento";
	}

}
