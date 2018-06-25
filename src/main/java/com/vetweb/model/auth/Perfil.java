package com.vetweb.model.auth;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Id;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private String descricaoPerfil;
    
    @Override
    public String getAuthority() {
        return descricaoPerfil;
    }
    
    public Perfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

    public Perfil() {
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }
    
}
