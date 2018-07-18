package com.vetweb.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vetweb.model.auth.Usuario;

@Component
public class UsersClient {
	
	private static final String URL_AUTH_SERVICE = "http://localhost:8080/vetweb-auth/service/usuarios";

	@Autowired
	private RestTemplate restTemplate;
	
	private List<Usuario> usuariosIntegration = new ArrayList<>();

	public List<Usuario> getUsuariosIntegration() {
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL_AUTH_SERVICE)
				.path("/all");
		ResponseEntity<List<Usuario>> response = restTemplate
					.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<Usuario>>() {});
		this.usuariosIntegration = response.getBody();
		return usuariosIntegration;
		
	}
	
	public Usuario loadByUsername(String username) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL_AUTH_SERVICE)
				.path("/".concat(username));
		HttpEntity<Usuario> response = restTemplate
				.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Usuario.class);
		return response.getBody();
		
	}

}
