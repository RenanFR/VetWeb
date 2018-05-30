/**
 * @author renanfr
 *
 */
package com.vetweb.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoService {
	
	public String escreverArquivo(String dirBase, MultipartFile file) {
		String caminhoReal = System.getenv("catalina_base") + "/" + dirBase;
		try {
			String caminho = caminhoReal + "/" + file.getOriginalFilename();
			file.transferTo(new File(caminho));
			return dirBase + "/" + file.getOriginalFilename();
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

}
