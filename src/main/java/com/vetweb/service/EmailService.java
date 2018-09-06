package com.vetweb.service;

//@author renan.rodrigues@metasix.com.br

import org.apache.commons.mail.Email;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.vetweb.model.Pessoa;

@Service
public class EmailService {
	
	public void enviar(Pessoa destinatario, String msg, String subject) {
		try {
			
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("springbootalura@gmail.com", "springboot"));
			email.setSSLOnConnect(true);
			email.setFrom("springbootalura@gmail.com");
			email.setSubject(subject);
			email.setMsg(msg);
			email.addTo(destinatario.getContato().getEmail());
			email.send();
			
		} catch (EmailException emailException) {
			
			emailException.printStackTrace();
			
		}
	}
	
}
