package com.vetweb.model.pojo;

import java.time.LocalDateTime;

public interface OcorrenciaProntuario {
	
	public abstract Long getOcorrenciaId();

	public abstract String getDescricao();

	public abstract LocalDateTime getData();

	public abstract TipoOcorrenciaProntuario getTipo();
	
}
