package com.vetweb.model.pojo;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vetweb.model.Agendamento;
import com.vetweb.model.Prontuario;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OcorrenciaProntuario {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long ocorrenciaId;
	
	@OneToMany(mappedBy = "ocorrencia")
	@JsonBackReference
	private List<Agendamento> agendamentos;
	
	@Enumerated(EnumType.STRING)
	private TipoOcorrenciaProntuario tipo;

	public Long getOcorrenciaId() {
		return ocorrenciaId;
	}

	public void setOcorrenciaId(Long ocorrenciaId) {
		this.ocorrenciaId = ocorrenciaId;
	}

	public abstract String getDescricao();

	public abstract LocalDateTime getData();

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
