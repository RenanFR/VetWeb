package com.vetweb.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vetweb.model.auth.Perfil;

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
		try {
			ResponseEntity<Map<String, List<String>>> response = restTemplate
					.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<String>>>() {});
			if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			}
			return response.getBody();
		} catch (ResourceAccessException | HttpClientErrorException connectException) {
			return null;
		}
		
	}
	
}
