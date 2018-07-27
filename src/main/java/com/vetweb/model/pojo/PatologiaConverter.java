//@author renan.rodrigues@metasix.com.br

package com.vetweb.model.pojo;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vetweb.dao.AnimalDAO;
import com.vetweb.model.Patologia;

@Component
public class PatologiaConverter implements Converter<String, Patologia>{
	
	@Autowired	
	private AnimalDAO animalDAO;
	
	private static final Logger LOGGER = Logger.getLogger(PatologiaConverter.class);
	
	@Override
	public Patologia convert(String patologia) { 
		LOGGER.info(("Realizando conversão de " + patologia + " para objeto Patologia").toUpperCase());
		return animalDAO.buscarPatologiaPorDescricao(patologia);	
	}
	
}
