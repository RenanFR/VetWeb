package com.vetweb.model;

import javax.persistence.Column;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_patologia")
public class Patologia implements Serializable {
	

	private static final long serialVersionUID = -5007814873079706537L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patologiaId;
	
    private String nome;
	
    private boolean ativo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;

    public Long getPatologiaId() {
		return patologiaId;
	}

	public void setPatologiaId(Long patologiaId) {
		this.patologiaId = patologiaId;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
    	return "Patologia";
    }
}
