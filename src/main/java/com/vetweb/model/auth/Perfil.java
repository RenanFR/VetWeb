package com.vetweb.model.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("descricao")
    private String descricaoPerfil;
	
	@JsonProperty("permissoes")
	private Set<Permissao> permissoes = new HashSet<>();
    
	public Perfil(String descricaoPerfil) {
		this.descricaoPerfil = "ROLE_".concat(descricaoPerfil);
	}
	
	public Perfil() {
	}
	
    @Override
    public String getAuthority() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = "ROLE_".concat(descricaoPerfil);
    }

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
    
}
