package com.vetweb.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tbl_atendimento")
public class OcorrenciaAtendimento implements OcorrenciaProntuario, Serializable {
	
	
	private static final long serialVersionUID = 3695573793611769516L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendimentoId;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "tipoDeAtendimentoId", referencedColumnName = "tipoDeAtendimentoId")
    private TipoDeAtendimento tipoDeAtendimento;
    
	@Column(columnDefinition = "TEXT")
    private String preenchimentoModeloAtendimento;
    
    private LocalDateTime dataAtendimento;
    
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
    private Prontuario prontuario;
    
    public boolean pago;
    
    public OcorrenciaAtendimento() {
    }
    
    public OcorrenciaAtendimento(Long atendimentoId, TipoDeAtendimento tipoDeAtendimento, String preenchimentoModeloAtendimento) {
    	this.atendimentoId = atendimentoId;
    	this.tipoDeAtendimento = tipoDeAtendimento;
    	this.preenchimentoModeloAtendimento = preenchimentoModeloAtendimento;
    }
    
    public Long getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(Long atendimentoId) {
        this.atendimentoId = atendimentoId;
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
    
    public LocalDateTime getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDateTime dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
    
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
    public boolean equals(Object obj) {
    	if(!(obj instanceof OcorrenciaAtendimento)) return false;
    	OcorrenciaAtendimento atendimentoComparar = (OcorrenciaAtendimento)obj;
    	return this.getAtendimentoId().equals(atendimentoComparar.getAtendimentoId());
    }
    
    @Override
    public int hashCode() {
    	return this.atendimentoId.intValue();
    }

	@Override
	public Long getOcorrenciaId() {
		return this.getAtendimentoId();
	}

	@Override
	public String getDescricao() {
		return this.getTipoDeAtendimento().getNome();
	}

	@Override
	public LocalDateTime getData() {
		return this.getDataAtendimento();
	}

	@Override
	public TipoOcorrenciaProntuario getTipo() {
		return TipoOcorrenciaProntuario.ATENDIMENTO;
	}
    
}
