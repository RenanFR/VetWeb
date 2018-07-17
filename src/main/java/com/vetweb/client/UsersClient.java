package com.vetweb.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vetweb.model.auth.Usuario;

public class UsersClient {
	
	private static final String HOST_AUTH_SERVICE = "http://localhost:8080/vetweb-auth/service/usuarios";

	@Autowired
	private RestTemplate restTemplate;
	
	private List<Usuario> usuariosIntegration = new ArrayList<>();

	public List<Usuario> getUsuariosIntegration() {
		ResponseEntity<List<Usuario>> response = restTemplate
					.exchange(HOST_AUTH_SERVICE.concat("/all"), HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<Usuario>>() {});
		this.usuariosIntegration = response.getBody();
		return usuariosIntegration;
	}
	
	public Usuario loadByUsername(String username) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("username", username);
		Usuario response = restTemplate
				.getForObject(HOST_AUTH_SERVICE.concat("/{username}"), Usuario.class, paramMap);
		return response;
	}

}
