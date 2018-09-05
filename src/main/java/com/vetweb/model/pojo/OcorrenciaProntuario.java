package com.vetweb.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vetweb.model.Agendamento;
import com.vetweb.model.Prontuario;


@Entity
@Table(name = "tbl_ocorrencia")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class OcorrenciaProntuario implements Serializable	{
	
	private static final long serialVersionUID = 3695573793611769516L;

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long ocorrenciaId;
	
	@OneToMany(mappedBy = "ocorrencia")
	@JsonBackReference
	private List<Agendamento> agendamentos;
	
	@Enumerated(EnumType.STRING)
	private TipoOcorrenciaProntuario tipo;
	
	private LocalDateTime data;
	
	public Long getOcorrenciaId() {
		return ocorrenciaId;
	}

	public void setOcorrenciaId(Long ocorrenciaId) {
		this.ocorrenciaId = ocorrenciaId;
	}

	public abstract String getDescricao();

	public abstract Prontuario getProntuario();

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public TipoOcorrenciaProntuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoOcorrenciaProntuario tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ocorrenciaId == null) ? 0 : ocorrenciaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OcorrenciaProntuario other = (OcorrenciaProntuario) obj;
		if (ocorrenciaId == null) {
			if (other.ocorrenciaId != null)
				return false;
		} else if (!ocorrenciaId.equals(other.ocorrenciaId))
			return false;
		return true;
	}
	
}
