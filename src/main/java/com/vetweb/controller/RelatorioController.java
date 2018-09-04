package com.vetweb.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vetweb.model.report.Report;
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
	public void print(@RequestParam("tipoRelatorio") ReportType reportType, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		Report report = ReportFactory.createReport(reportType);
		Enumeration<String> parametersRequest = request.getParameterNames();
		Object[] parameters = null;
		if (report.getParameters() != null) {
			
			parameters = new Object[report.getParameters().size() + 2];
			int i = 0;
			while (parametersRequest.hasMoreElements()) {
				String parameter = parametersRequest.nextElement();
				parameters[i] = request.getParameter(parameter);
				System.out.println(parameter + "	" + parameters[i]);
				i++;
			}
		}
		response.setContentType("application/pdf");
		jasperService.gerarRelatorio(ReportFactory.createReport(reportType, parameters), response.getOutputStream());
	}
	
	@GetMapping("/{tipoRelatorio}")
	public @ResponseBody Report buscarRelatorioPorTipo(@PathVariable("tipoRelatorio") String tipoRelatorio) {
		ReportType reportType = ReportType.valueOf(tipoRelatorio);
		return ReportFactory.createReport(reportType);		
	}

}
