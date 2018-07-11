package com.vetweb.service;

//@author renan.rodrigues@metasix.com.br

import java.io.IOException;
import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoService {
	
	public String escreverArquivo(String dirBase, MultipartFile file) {
		String caminhoReal = System.getProperty("jboss.home.dir") + "/" + dirBase;
		try {
			String caminho = caminhoReal + "/" + file.getOriginalFilename();
			file.transferTo(new File(caminho));
			return dirBase + "/" + file.getOriginalFilename();
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

}
