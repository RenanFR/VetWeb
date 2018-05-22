package com.vetweb.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProntuarioPatologia implements Serializable, ElementoProntuario {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long prontuarioPatologiaId;
	
	@JsonIgnore
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

	public String getInclusaoPatologia() {
		return inclusaoPatologia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	@Override
	public String toString() {
		return patologia.getNome();
	}

	public void setInclusaoPatologia(String inclusaoPatologia) {
		if (inclusaoPatologia.contains("-")) {
			this.inclusaoPatologia = LocalDate.parse(inclusaoPatologia, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} else if (inclusaoPatologia.contains("/")) {
			this.inclusaoPatologia = LocalDate.parse(inclusaoPatologia, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} else {
			throw new RuntimeException("FORMATO DESCONHECIDO DE DATA. ");
		}		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prontuarioPatologiaId == null) ? 0 : prontuarioPatologiaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProntuarioPatologia other = (ProntuarioPatologia) obj;
		if (prontuarioPatologiaId == null) {
			if (other.prontuarioPatologiaId != null)
				return false;
		} else if (!prontuarioPatologiaId.equals(other.prontuarioPatologiaId))
			return false;
		return true;
	}
	
}
