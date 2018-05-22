/**
 * @author renanfr
 *
 */
package com.vetweb.service;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.vetweb.model.Pessoa;

@Service
public class EmailService {
	
	public static void enviar(Pessoa destinatario, String msg, String subject) {
		try {
			
			Email email = new SimpleEmail();//Utilizando biblioteca de envio de e-mail da apache
			email.setHostName("smtp.gmail.com");//Endereço do Host de e-mail smtp
			email.setSmtpPort(465);//Porta p/ conexão ao Host
			email.setAuthenticator(new DefaultAuthenticator("springbootalura@gmail.com", "springboot"));
				//Autenticação do remetente via usuário e senha 
			email.setSSLOnConnect(true);//Secure Socket Layer. Cria canal criptografado c/ chaves entre servidor e cliente
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
