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
public class ProntuarioVacina implements Serializable, ElementoProntuario {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long prontuarioVacinaId;
	
	@JsonIgnore
	@JoinColumn(name="prontuarioId")
	private Prontuario prontuario;
	
	@JoinColumn(name="vacinaId")
	private Vacina vacina;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inclusaoVacina;
	
	private boolean pago;

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

	public String getInclusaoVacina() {
		return inclusaoVacina.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setInclusaoVacina(String inclusaoVacina) {
		if (inclusaoVacina.contains("-")) {
			this.inclusaoVacina = LocalDate.parse(inclusaoVacina, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} else if (inclusaoVacina.contains("/")) {
			this.inclusaoVacina = LocalDate.parse(inclusaoVacina, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} else {
			throw new RuntimeException("FORMATO DESCONHECIDO DE DATA. ");
		}
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	@Override
	public String toString() {
		return vacina.getNome();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prontuarioVacinaId == null) ? 0 : prontuarioVacinaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProntuarioVacina other = (ProntuarioVacina) obj;
		if (prontuarioVacinaId == null) {
			if (other.prontuarioVacinaId != null)
				return false;
		} else if (!prontuarioVacinaId.equals(other.prontuarioVacinaId))
			return false;
		return true;
	}
	
	
}
