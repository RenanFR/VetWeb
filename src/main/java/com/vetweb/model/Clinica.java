package com.vetweb.model;
//	@author renan.rodrigues@metasix.com.br

import java.io.Serializable;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity @Table(name = "estabelecimento")
public class Clinica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String razaoSocial;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private LocalDate fundadaEm;
    
    @Id
    private String cnpj;
    
    private String proprietario;
    
    public Clinica(String razaoSocial, LocalDate fundadaEm, String cnpj, String proprietario) {
    	super();
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
