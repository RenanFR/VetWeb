package com.vetweb.service;

import java.io.File;

//@author renan.rodrigues@metasix.com.br

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoService {
	
	private static final Logger LOGGER = Logger.getLogger(ArquivoService.class);
	
	public String escreverArquivo(String dirBase, MultipartFile file) {
		String caminhoReal = System.getProperty("jboss.home.dir") + "/" + dirBase;
		try {
			String caminho = caminhoReal + "/" + file.getOriginalFilename();
			file.transferTo(new File(caminho));
			return dirBase + "/" + file.getOriginalFilename();
		} catch (FileAlreadyExistsException fileAlreadyExistsException) {
			LOGGER.error("ARQUIVO COM MESMO NOME JÁ EXISTE NO SERVIDOR.");
			return null;
			
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

}
