package com.vetweb.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Entity
@Table(name = "tbl_patologia_event")
public class OcorrenciaPatologia extends OcorrenciaProntuario implements Serializable {


	private static final long serialVersionUID = 1651108074834160258L;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;
	
	@ManyToOne
	@JoinColumn(name="patologiaId", referencedColumnName = "patologiaId")
	private Patologia patologia;
	
	private LocalDateTime inclusaoPatologia;
	
	@Override
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
