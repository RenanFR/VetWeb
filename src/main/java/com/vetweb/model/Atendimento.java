package com.vetweb.model;

import java.io.Serializable;

//@author renan.rodrigues@metasix.com.br

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_atendimento")
public class Atendimento implements Serializable, ElementoProntuario {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendimentoId;
	
	@ManyToOne
	@JoinColumn(name = "tipoDeAtendimentoId", referencedColumnName = "tipoDeAtendimentoId")
    private TipoDeAtendimento tipoDeAtendimento;
    
	@Column(columnDefinition = "TEXT")
    private String preenchimentoModeloAtendimento;
    
    private LocalDate dataAtendimento;
    
    public boolean pago;
    
    public Atendimento() {
    }
    
    public Atendimento(Long atendimentoId, TipoDeAtendimento tipoDeAtendimento, String preenchimentoModeloAtendimento) {
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
    
    public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
    
    @Override
    public String toString() {
    	return this.getTipoDeAtendimento().getNome();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(!(obj instanceof Atendimento)) return false;
    	Atendimento atendimentoComparar = (Atendimento)obj;
    	return this.getAtendimentoId().equals(atendimentoComparar.getAtendimentoId());
    }
    
    @Override
    public int hashCode() {
    	return this.atendimentoId.intValue();
    }
    
}
