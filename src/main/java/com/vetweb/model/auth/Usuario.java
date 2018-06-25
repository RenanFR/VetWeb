package com.vetweb.model.auth;

//@author renan.rodrigues@metasix.com.br

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private String username;
	
    private String password;
    
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> authorities = new ArrayList<>();
    
    public Usuario() {
    	
    }
    
    public Usuario(String username, String password, String name) {
    	this.username = username;
    	this.name = name;
    	this.password = new BCryptPasswordEncoder().encode(password);
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
