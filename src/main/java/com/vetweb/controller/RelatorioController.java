package com.vetweb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.model.report.ReportFactory;
import com.vetweb.model.report.ReportType;
import com.vetweb.service.JasperService;

@RequestMapping("/relatorios")
@Transactional
@Controller
public class RelatorioController {
	
	@Autowired
	private JasperService jasperService;
	
	@GetMapping
	public ModelAndView relatorios() {
		ModelAndView modelAndView = new ModelAndView("relatorios/relatorios");
		return modelAndView;
	}
	
	@PostMapping
	public void print(@RequestParam("tipoRelatorio") ReportType reportType, HttpServletResponse response) throws IOException {
		jasperService.gerarRelatorio(ReportFactory.createReport(reportType, null), response.getOutputStream());
	}

}
