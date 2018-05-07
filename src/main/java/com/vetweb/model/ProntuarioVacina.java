package com.vetweb.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class ProntuarioVacina implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long prontuarioVacinaId;
	
	@JoinColumn(name="prontuarioId")
	private Prontuario prontuario;
	
	@JoinColumn(name="vacinaId")
	private Vacina vacina;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inclusaoVacina;

	public Long getProntuarioVacinaId() {
		return prontuarioVacinaId;
	}

	public void setProntuarioVacinaId(Long prontuarioVacinaId) {
		this.prontuarioVacinaId = prontuarioVacinaId;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getInclusaoVacina() {
		return inclusaoVacina;
	}

	public void setInclusaoVacina(LocalDate inclusaoVacina) {
		this.inclusaoVacina = inclusaoVacina;
	}
}
