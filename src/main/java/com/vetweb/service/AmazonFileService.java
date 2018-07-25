package com.vetweb.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component(value = "amazon")
public class AmazonFileService implements FileService{
	
	@Autowired
	private AmazonS3Client s3Client;
	
	private static final String BUCKET = "http://localhost:9444/s3/vetweb/";
	
	public String salvarArquivo(MultipartFile file) {
		try {
			s3Client.putObject("vetweb", 
					file.getOriginalFilename(), 
					file.getInputStream(), 
					new ObjectMetadata());
			
			return BUCKET + file.getOriginalFilename() + "?noAuth=true";
		} catch (AmazonClientException | IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
