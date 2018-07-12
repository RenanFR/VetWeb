package com.vetweb.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class EndpointSession {
	
	private List<Session> sessions = new ArrayList<>();

	public void add(Session session) {
		this.sessions.add(session);
	}
	
	public List<Session> getSessions() {
		return sessions;
	}

}
