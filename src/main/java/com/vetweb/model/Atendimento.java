package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "atendimento")
public class Atendimento implements Serializable, ElementoProntuario {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long atendimentoId;
	
    private TipoDeAtendimento tipoDeAtendimento;
    
    private StringBuilder preenchimentoModeloAtendimento;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtendimento;
    
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
    
    public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Atendimento() {
    }

    public Atendimento(Long atendimentoId, TipoDeAtendimento tipoDeAtendimento, StringBuilder preenchimentoModeloAtendimento) {
        this.atendimentoId = atendimentoId;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.preenchimentoModeloAtendimento = preenchimentoModeloAtendimento;
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
