package com.vetweb.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "tbl_agendamento")
public class Agendamento {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agendamentoId;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "ocorrenciaId", referencedColumnName = "ocorrenciaId")
	private OcorrenciaProntuario ocorrencia;
	
	private LocalDateTime dataHoraInicial;
	
	private LocalDateTime dataHoraFinal;
	
	@Enumerated(EnumType.STRING)
	private TipoOcorrenciaProntuario tipo;

	public Long getAgendamentoId() {
		return agendamentoId;
	}

	public void setAgendamentoId(Long agendamentoId) {
		this.agendamentoId = agendamentoId;
	}

	public OcorrenciaProntuario getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(OcorrenciaProntuario ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public LocalDateTime getDataHoraInicial() {
		return dataHoraInicial;
	}

	public void setDataHoraInicial(LocalDateTime dataHoraInicial) {
		this.dataHoraInicial = dataHoraInicial;
	}

	public LocalDateTime getDataHoraFinal() {
		return dataHoraFinal;
	}

	public void setDataHoraFinal(LocalDateTime dataHoraFinal) {
		this.dataHoraFinal = dataHoraFinal;
	}

	public TipoOcorrenciaProntuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoOcorrenciaProntuario tipo) {
		this.tipo = tipo;
	}

}
