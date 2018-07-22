package com.vetweb.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vetweb.model.auth.Perfil;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileClient {
	
	private static final String URL_AUTH_SERVICE = "http://localhost:8080/vetweb-auth/service/perfil";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Perfil> getProfilesIntegration() {
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL_AUTH_SERVICE)
				.path("/all");
		ResponseEntity<List<Perfil>> response = restTemplate
				.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Perfil>>() {});
		return response.getBody();
		
	}
	
	public Map<String, List<String>> getPermissionsWithProfiles() {
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL_AUTH_SERVICE)
				.path("/permissions");
		ResponseEntity<Map<String, List<String>>> response = restTemplate
				.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<String>>>() {});
		if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new RuntimeException("Não foi possível se conectar no módulo de permissões");
		}
		return response.getBody();
		
	}
	
}
