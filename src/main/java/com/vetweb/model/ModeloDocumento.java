package com.vetweb.model;

import java.io.Serializable;

import javax.persistence.Column;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_modelo_documento")
public class ModeloDocumento implements Serializable {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelodocumentoId;
    
    private String nome;
    
    private boolean infoCliente;
    
    @Column(columnDefinition = "TEXT")
    private String modelo;
    
    public ModeloDocumento() {
	}
    
	public Long getModelodocumentoId() {
		return modelodocumentoId;
	}

	public void setModelodocumentoId(Long modelodocumentoId) {
		this.modelodocumentoId = modelodocumentoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isInfoCliente() {
		return infoCliente;
	}

	public void setInfoCliente(boolean infoCliente) {
		this.infoCliente = infoCliente;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
    
}
