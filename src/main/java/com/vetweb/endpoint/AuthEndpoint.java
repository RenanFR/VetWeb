package com.vetweb.endpoint;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/endpoint/auth")
public class AuthEndpoint {
	
//	@Autowired
//	private RequestMappingHandlerMapping handlerMapping;
	
	@Inject
	private EndpointSession endpointSession;
	
	private static final Logger LOGGER = Logger.getLogger(AuthEndpoint.class);
	
	@OnOpen
	public void onOpen(Session session) {
		endpointSession.add(session);
	}
	
	@OnMessage
	public void onMessage(String string) {
		sendMappingsIntegration();
	}
	
	public void sendMappingsIntegration() {
		for (Session session : endpointSession.getSessions()) {
			if (session.isOpen()) {
				try {
					session.getBasicRemote()
						.sendText("ServerEndpoint");
				} catch (Exception exception) {
					LOGGER.error(exception.getMessage());
				}
			}
		}
	}
}
