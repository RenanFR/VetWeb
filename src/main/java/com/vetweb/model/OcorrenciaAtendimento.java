package com.vetweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Entity
@Table(name = "tbl_atendimento")
public class OcorrenciaAtendimento extends OcorrenciaProntuario implements Serializable {
	
	private static final long serialVersionUID = 3695573793611769516L;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "tipoDeAtendimentoId", referencedColumnName = "tipoDeAtendimentoId")
    private TipoDeAtendimento tipoDeAtendimento;
    
	@Column(columnDefinition = "TEXT")
    private String preenchimentoModeloAtendimento;
    
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
    private Prontuario prontuario;
    
    public boolean pago;
    
    public OcorrenciaAtendimento() {
    }
    
    public OcorrenciaAtendimento(TipoDeAtendimento tipoDeAtendimento, String preenchimentoModeloAtendimento) {
    	this.tipoDeAtendimento = tipoDeAtendimento;
    	this.preenchimentoModeloAtendimento = preenchimentoModeloAtendimento;
    }

    public TipoDeAtendimento getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

    public String getPreenchimentoModeloAtendimento() {
        return preenchimentoModeloAtendimento;
    }

    public void setPreenchimentoModeloAtendimento(String preenchimentoModeloAtendimento) {
        this.preenchimentoModeloAtendimento = preenchimentoModeloAtendimento;
    }
    
	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	@Override
    public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	@Override
    public String toString() {
    	return this.getTipoDeAtendimento().getNome();
    }

	@Override
	public String getDescricao() {
		return this.getTipoDeAtendimento().getNome();
	}

	@Override
	public TipoOcorrenciaProntuario getTipo() {
		return TipoOcorrenciaProntuario.ATENDIMENTO;
	}
	
}
