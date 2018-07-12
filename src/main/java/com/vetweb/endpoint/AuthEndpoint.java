package com.vetweb.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetweb.config.security.SystemURL;

@ServerEndpoint(value = "/endpoint/auth")
public class AuthEndpoint {
	
	@Inject
	private EndpointSession endpointSession;
	
	private static final Logger LOGGER = Logger.getLogger(AuthEndpoint.class);
	
	@OnOpen
	public void onOpen(Session session) {
		endpointSession.add(session);
		sendMappingsIntegration();
	}
	
	@OnMessage
	public void onMessage(String param) {
		LOGGER.info("MENSAGEM RECEBIDA COM PARÂMETRO " + param);
	}
	
	public List<String> getMappings() {
		return SystemURL.all();
	}
	
	public void sendMappingsIntegration() {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(getMappings());
		} catch (JsonProcessingException jsonProcessingException) {
			LOGGER.error("ERRO AO CONVERTER A LISTA DE ENDEREÇOS DA APLICAÇÃO EM FORMATO JSON: " + jsonProcessingException.getMessage());
		}
		for (Session session : endpointSession.getSessions()) {
			if (session.isOpen()) {
				try {
					session.getBasicRemote()
						.sendText(json);
				} catch (Exception exception) {
					LOGGER.error(exception.getMessage());
				}
			}
		}
	}
}
