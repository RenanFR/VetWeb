package com.vetweb.model;
//	@author renan.rodrigues@metasix.com.br

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "tbl_clinica")
public class Clinica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition = "VARCHAR(30)")
	private String razaoSocial;
    
    private LocalDate fundadaEm;
    
    @Id @Column(columnDefinition = "VARCHAR(30)")
    private String cnpj;
    
    @Column(columnDefinition = "VARCHAR(30)")
    private String proprietario;
    
    public Clinica(String razaoSocial, LocalDate fundadaEm, String cnpj, String proprietario) {
    	this.razaoSocial = razaoSocial;
    	this.fundadaEm = fundadaEm;
    	this.cnpj = cnpj;
    	this.proprietario = proprietario;
    }
    
    public Clinica() {
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public LocalDate getFundadaEm() {
        return fundadaEm;
    }

    public void setFundadaEm(LocalDate fundadaEm) {
        this.fundadaEm = fundadaEm;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

}
