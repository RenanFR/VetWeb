package com.vetweb.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetweb.model.pojo.OcorrenciaProntuario;

@Entity
@Table(name = "tbl_agendamento")
public class Agendamento {
	
	@Id
	private Long agendamentoId;
	
	@ManyToOne
	@JoinColumn(name = "ocorrenciaId", referencedColumnName = "ocorrenciaId")
	private OcorrenciaProntuario ocorrencia;
	
	private LocalDateTime dataHoraInicial;
	
	private LocalDateTime dataHoraFinal;

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

}
