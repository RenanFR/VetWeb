package com.vetweb.model;

import java.io.Serializable;

//@author renan.rodrigues@metasix.com.br

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private StringBuilder preenchimentoModeloAtendimento;
    
    private LocalDate dataAtendimento;
    
    public boolean pago;
    
    public Atendimento() {
    }
    
    public Atendimento(Long atendimentoId, TipoDeAtendimento tipoDeAtendimento, StringBuilder preenchimentoModeloAtendimento) {
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

    public StringBuilder getPreenchimentoModeloAtendimento() {
        return preenchimentoModeloAtendimento;
    }

    public void setPreenchimentoModeloAtendimento(StringBuilder preenchimentoModeloAtendimento) {
        this.preenchimentoModeloAtendimento = preenchimentoModeloAtendimento;
    }
    
    public String getDataAtendimento() {
		return dataAtendimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setDataAtendimento(String dataAtendimento) {
		if (dataAtendimento.contains("-")) {
			this.dataAtendimento = LocalDate.parse(dataAtendimento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} else if (dataAtendimento.contains("/")) {
			this.dataAtendimento = LocalDate.parse(dataAtendimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} else {
			throw new RuntimeException("FORMATO DESCONHECIDO DE DATA. ");
		}
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
