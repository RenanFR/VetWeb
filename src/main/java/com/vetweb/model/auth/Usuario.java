package com.vetweb.model.auth;

import java.util.ArrayList;
import java.util.Base64;

//@author renan.rodrigues@metasix.com.br

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
    private String username;
	
	@JsonProperty
    private String password;
    
    private String name;
    
    @JsonProperty("perfis")
    private List<Perfil> authorities = new ArrayList<>();
    
    public Usuario() {
    	
    }
    
    public Usuario(String username, String password, String name) {
    	this.username = username;
    	this.name = name;
    	this.password = password;
    }
    
    @Deprecated
    public String decodePassword(String password ) {
    	return new String(Base64.getDecoder().decode(password.getBytes()));
    }
    
    @Override
    public Collection<Perfil> getAuthorities() {
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(List<Perfil> authorities) {
        this.authorities = authorities;
    }
    
}
