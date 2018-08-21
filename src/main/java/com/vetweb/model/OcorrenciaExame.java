package com.vetweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

import static com.vetweb.model.pojo.TipoOcorrenciaProntuario.EXAME;

@Entity
@Table(name = "tbl_exame_event")
public class OcorrenciaExame extends OcorrenciaProntuario implements Serializable {
	
	private static final long serialVersionUID = 3695573793611769516L;
	
	@ManyToOne
	@JoinColumn(name = "exameId", referencedColumnName = "exameId")
	private Exame exame;
    
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
    private Prontuario prontuario;
    
    public boolean pago;

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	@Override
	public String getDescricao() {
		return exame.getDescricao();
	}

	@Override
	public Prontuario getProntuario() {
		return prontuario;
	}
	
	@Override
	public TipoOcorrenciaProntuario getTipo() {
		return EXAME;
	}
	
}
