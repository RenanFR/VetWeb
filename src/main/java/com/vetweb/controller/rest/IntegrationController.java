package com.vetweb.controller.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vetweb.config.security.SystemURL;

@RestController
@Transactional
@RequestMapping("/integration")
public class IntegrationController {
	
	private static final Logger LOGGER = Logger.getLogger(IntegrationController.class);
	
	@RequestMapping(value = "/mappings", method = RequestMethod.GET)
	public List<String> mappings() {
		LOGGER.debug("ENVIANDO AS URL(S) DO SISTEMA PARA GERENCIAMENTO DE PERMISSÕES");
		return SystemURL.all();
	}

}
