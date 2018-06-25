package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import javax.persistence.Embeddable;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Embeddable
public class Contato implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^\\(\\d{2}\\)\\d{4}-\\d{4}$")
    private String telefone;
    
    private String celular;
    
    @Email
    private String email;

    public Contato() {
    }

    public Contato(String telefone, String celular, String email) {
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
