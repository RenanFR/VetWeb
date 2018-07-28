package com.vetweb.config;

import org.springframework.context.annotation.Bean;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

public class AmazonConfig {
	
	@Bean
	public AmazonS3Client client() {
		AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
		AmazonS3Client s3Client = new AmazonS3Client(awsCredentials, new ClientConfiguration());
		s3Client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		s3Client.setEndpoint("http://localhost:9444/s3");
		return s3Client;
	}

}