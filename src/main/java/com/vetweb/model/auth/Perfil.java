package com.vetweb.model.auth;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private String descricaoPerfil;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Permissao> permissoes = new HashSet<>();
    
	public Perfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
	}
	
	public Perfil() {
	}
	
    @Override
    public String getAuthority() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
    
}
