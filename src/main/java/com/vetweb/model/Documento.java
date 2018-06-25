package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documentos")
public class Documento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer documentoId;
	
	private ModeloDocumento modeloDocumento;
	
	public Documento() {
	}
	
	public Documento(Integer documentoId, ModeloDocumento modeloDocumento) {
		super();
		this.documentoId = documentoId;
		this.modeloDocumento = modeloDocumento;
	}

	public Integer getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}

	public ModeloDocumento getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(ModeloDocumento modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}
    
}
