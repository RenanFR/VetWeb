package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Protocolo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String nome;
    
    private String especie;
    
    private Integer aplicacao;
    
    private Integer intervalo;
    
    private Vacina vemApos;
    
    public Protocolo() {
	}

	public Protocolo(String nome, String especie, Integer aplicacao, Integer intervalo, Vacina vemApos) {
		this.nome = nome;
		this.especie = especie;
		this.aplicacao = aplicacao;
		this.intervalo = intervalo;
		this.vemApos = vemApos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Integer getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Integer aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public Vacina getVemApos() {
		return vemApos;
	}

	public void setVemApos(Vacina vemApos) {
		this.vemApos = vemApos;
	}
    
}
