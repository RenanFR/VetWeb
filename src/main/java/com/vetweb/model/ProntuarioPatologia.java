package com.vetweb.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ProntuarioPatologia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long prontuarioPatologiaId;
	
	@JoinColumn(name="prontuarioId")
	private Prontuario prontuario;
	
	@JoinColumn(name="nome")
	private Patologia patologia;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inclusaoPatologia;

	public Long getProntuarioPatologiaId() {
		return prontuarioPatologiaId;
	}

	public void setProntuarioPatologiaId(Long prontuarioPatologiaId) {
		this.prontuarioPatologiaId = prontuarioPatologiaId;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Patologia getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}

	public LocalDate getInclusaoPatologia() {
		return inclusaoPatologia;
	}

	public void setInclusaoPatologia(LocalDate inclusaoPatologia) {
		this.inclusaoPatologia = inclusaoPatologia;
	}
}
