package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import java.math.BigDecimal;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_vacina")
public class Vacina implements Serializable, ElementoProntuario {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vacinaId;
	
    private String nome;
    
    private String grupo;
    
    private boolean status;
    
    private String laboratorio;
    
    @Transient
    private Protocolo protocolo;
    
    private BigDecimal preco;

    public Vacina(String text) {
        this.nome = text;
    }

    public Vacina(Long vacinaId, String nome, String grupo, boolean status, String laboratorio, Protocolo protocolo) {
        this.vacinaId = vacinaId;
        this.nome = nome;
        this.grupo = grupo;
        this.status = status;
        this.laboratorio = laboratorio;
        this.protocolo = protocolo;
    }

    public Vacina() {
    }
    

    public Long getVacinaId() {
        return vacinaId;
    }

    public void setVacinaId(Long vacinaId) {
        this.vacinaId = vacinaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
