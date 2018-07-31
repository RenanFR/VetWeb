package com.vetweb.model;

import javax.persistence.Column;

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
@Table(name = "tbl_exame")

public class Exame implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long exameId;
    
	@Column(columnDefinition = "TEXT")
    private StringBuilder apresentacao;
	
	@Column(columnDefinition = "TEXT")
    private StringBuilder encerramento;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;

    public Exame() {
	}
    
    
    
	public Exame(Long exameId, StringBuilder apresentacao, StringBuilder encerramento) {
		this.exameId = exameId;
		this.apresentacao = apresentacao;
		this.encerramento = encerramento;
	}



	public Long getExameId() {
		return exameId;
	}

	public void setExameId(Long exameId) {
		this.exameId = exameId;
	}

	public StringBuilder getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(StringBuilder apresentacao) {
		this.apresentacao = apresentacao;
	}

	public StringBuilder getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(StringBuilder encerramento) {
		this.encerramento = encerramento;
	}



	public Prontuario getProntuario() {
		return prontuario;
	}



	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
    
}
