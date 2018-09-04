package com.vetweb.jms;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class JMSNotificacaoOcorrenciaCliente {
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	private JmsTemplate jmsTemplate;
	
	@PostConstruct
	public void construct() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}
	
	public void sendNotification(String queue, String message) {
		System.out.println("MENSAGEM: " + message);
		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
	public void receive(String queue) {
		Message message = jmsTemplate.receive(queue);
		TextMessage textMessage = (TextMessage)message;
		System.out.println("MESSAGE: " + textMessage);
	}

}
