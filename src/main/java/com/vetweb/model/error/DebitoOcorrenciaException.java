package com.vetweb.model.error;

import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

public class DebitoOcorrenciaException extends RuntimeException	{
	
	private static final long serialVersionUID = 1L;
	
	private TipoOcorrenciaProntuario tipoOcorrenciaProntuario;
	
	public DebitoOcorrenciaException(TipoOcorrenciaProntuario tipoOcorrenciaProntuario) {
		this.tipoOcorrenciaProntuario = tipoOcorrenciaProntuario;
	}
	
	public TipoOcorrenciaProntuario getTipoOcorrenciaProntuario() {
		return tipoOcorrenciaProntuario;
	}

}
