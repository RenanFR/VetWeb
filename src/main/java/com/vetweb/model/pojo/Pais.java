package com.vetweb.model.pojo;

//@author renan.rodrigues@metasix.com.br

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Pais implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("gentilico")
    private String gentilico;
	
    @JsonProperty("nome_pais")
    private String nomePais;
    
    @JsonProperty("nome_pais_int")
    private String nomeIngles;
    
    @JsonProperty("sigla")
    private String sigla;
    
    public Pais() {
	}
    
    public Pais(String gentilico, String nomePais, String nomeIngles, String sigla) {
		this.gentilico = gentilico;
		this.nomePais = nomePais;
		this.nomeIngles = nomeIngles;
		this.sigla = sigla;
	}

	public String getGentilico() {
		return gentilico;
	}

	public void setGentilico(String gentilico) {
		this.gentilico = gentilico;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getNomeIngles() {
		return nomeIngles;
	}

	public void setNomeIngles(String nomeIngles) {
		this.nomeIngles = nomeIngles;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
    public String toString() {
        return nomePais;
    }
    
}
