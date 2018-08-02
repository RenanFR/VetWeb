package com.vetweb.model.pojo;

import java.time.LocalDateTime;

import com.vetweb.model.Prontuario;

public interface OcorrenciaProntuario {
	
	public abstract Long getOcorrenciaId();

	public abstract String getDescricao();

	public abstract LocalDateTime getData();

	public abstract TipoOcorrenciaProntuario getTipo();
	
	public abstract Prontuario getProntuario();
	
}
