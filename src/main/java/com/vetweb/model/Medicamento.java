package com.vetweb.model;

import javax.persistence.Column;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_medicamento")
public class Medicamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicamentoId;
    
	@Column(columnDefinition = "TEXT")
    private String descricao;
	
	@ManyToMany(mappedBy = "medicamentos")
	private List<Prescricao> prescricoes;
    
    private boolean usoControlado;

	public Medicamento(Integer medicamentoId, String descricao, boolean usoControlado) {
		super();
		this.medicamentoId = medicamentoId;
		this.descricao = descricao;
		this.usoControlado = usoControlado;
	}

	public Integer getMedicamentoId() {
		return medicamentoId;
	}

	public void setMedicamentoId(Integer medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isUsoControlado() {
		return usoControlado;
	}

	public void setUsoControlado(boolean usoControlado) {
		this.usoControlado = usoControlado;
	}

	public List<Prescricao> getPrescricoes() {
		return prescricoes;
	}

	public void setPrescricoes(List<Prescricao> prescricoes) {
		this.prescricoes = prescricoes;
	}
    
}
