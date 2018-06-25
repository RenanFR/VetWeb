package com.vetweb.model.pojo;

//@author renan.rodrigues@metasix.com.br

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Profissao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("profissoes")
    private static List<String> profissoes;

    public List<String> getProfissoes() {
        return profissoes;
    }

    public void setProfissoes(List<String> profissoes) {
        this.profissoes = profissoes;
    }
    
}
