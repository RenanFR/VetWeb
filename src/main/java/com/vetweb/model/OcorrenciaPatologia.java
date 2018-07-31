package com.vetweb.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Entity
@Table(name = "tbl_patologia_event")
public class OcorrenciaPatologia implements OcorrenciaProntuario, Serializable {


	private static final long serialVersionUID = 1651108074834160258L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prontuarioPatologiaId;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;
	
	@ManyToOne
	@JoinColumn(name="patologiaId", referencedColumnName = "patologiaId")
	private Patologia patologia;
	
	private LocalDateTime inclusaoPatologia;

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

	public LocalDateTime getInclusaoPatologia() {
		return inclusaoPatologia;
	}
	
	public void setInclusaoPatologia(LocalDateTime inclusaoPatologia) {
		this.inclusaoPatologia = inclusaoPatologia;
	}
	
	@Override
	public String toString() {
		return patologia.getNome();
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
		OcorrenciaPatologia other = (OcorrenciaPatologia) obj;
		if (prontuarioPatologiaId == null) {
			if (other.prontuarioPatologiaId != null)
				return false;
		} else if (!prontuarioPatologiaId.equals(other.prontuarioPatologiaId))
			return false;
		return true;
	}

	@Override
	public Long getOcorrenciaId() {
		return this.getProntuarioPatologiaId();
	}

	@Override
	public String getDescricao() {
		return this.patologia.getNome();
	}

	@Override
	public LocalDateTime getData() {
		return this.getInclusaoPatologia();
	}

	@Override
	public TipoOcorrenciaProntuario getTipo() {
		return TipoOcorrenciaProntuario.PATOLOGIA;
	}
	
}
