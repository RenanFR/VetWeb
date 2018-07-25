package com.vetweb.service;

import java.io.File;

//@author renan.rodrigues@metasix.com.br

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component(value = "local")
public class LocalFileService implements FileService{
	
	@Autowired
	private HttpServletRequest request;
	
	private static final Logger LOGGER = Logger.getLogger(LocalFileService.class);
	
	public String salvarArquivo(MultipartFile file) {
		String dirBase = "/imagens";
		String realPath = request.getServletContext().getRealPath(dirBase);
		try {
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			return dirBase + "/" + file.getOriginalFilename();
		} catch (FileAlreadyExistsException fileAlreadyExistsException) {
			LOGGER.error("ARQUIVO COM MESMO NOME JÁ EXISTE NO SERVIDOR.");
		} catch (IOException ioException) {
			LOGGER.error("ERRO AO ARMAZENAR ARQUIVO: " + ioException.getMessage());
		}
		return null;
	}

}
