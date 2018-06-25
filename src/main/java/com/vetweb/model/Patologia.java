package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patologias")
public class Patologia implements Serializable, ElementoProntuario {
	
	private static final long serialVersionUID = -5607289540887122702L;
	
	@Id
    private String nome;
	
    private boolean ativo;
    
    private StringBuilder descricao;

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

    public StringBuilder getDescricao() {
        return descricao;
    }

    public void setDescricao(StringBuilder descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
    	return "Patologia";
    }
}
