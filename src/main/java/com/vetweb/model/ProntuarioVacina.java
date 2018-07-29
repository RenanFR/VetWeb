package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_vacina_event")
public class ProntuarioVacina implements Serializable, ElementoProntuario {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prontuarioVacinaId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;
	
	@ManyToOne
	@JoinColumn(name="vacinaId", referencedColumnName = "vacinaId")
	private Vacina vacina;
	
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

	public LocalDate getInclusaoVacina() {
		return inclusaoVacina;
	}

	public void setInclusaoVacina(LocalDate inclusaoVacina) {
		this.inclusaoVacina = inclusaoVacina;
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
