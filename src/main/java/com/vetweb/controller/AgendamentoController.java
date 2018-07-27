package com.vetweb.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vetweb.model.Atendimento;

@Controller
@Transactional
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	@GetMapping
	public String agenda() {
		return "agendamento/agendamento";
	}
	
	@ResponseBody
	@RequestMapping("/eventos")
	public List<Atendimento> todosOsAtendimentos() {
		return null;
	}

}
