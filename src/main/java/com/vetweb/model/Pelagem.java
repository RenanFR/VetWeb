package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pelagem")
public class Pelagem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pelagemId;
    
    private String descricao;

    public Pelagem() {
    }

    public Pelagem(Long pelagemId, String descricao) {
        this.pelagemId = pelagemId;
        this.descricao = descricao;
    }

    public Long getPelagemId() {
        return pelagemId;
    }

    public void setPelagemId(Long pelagemId) {
        this.pelagemId = pelagemId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
