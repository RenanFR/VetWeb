package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_documento")
public class Documento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentoId;
	
	@ManyToOne
	@JoinColumn(name = "modeloDocumento", referencedColumnName = "nome")
	private ModeloDocumento modeloDocumento;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;
	
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

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
    
}
