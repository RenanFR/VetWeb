package com.vetweb.model;

import java.io.Serializable;

//@author renan.rodrigues@metasix.com.br

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vetweb.dao.converter.FrequenciaConverter;

@Entity
@Table(name = "tbl_prescricao")
public class Prescricao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prescricaoId;
    
	private double dosagem;
    
	@Convert(converter = FrequenciaConverter.class)
	private Period tomarACada;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;
    
	private LocalDate tomarAte;
    
    @ManyToMany
    @JoinTable(name = "tbl_prescricao_medicamento", 
	    joinColumns = {@JoinColumn(name = "prescricaoId", referencedColumnName = "prescricaoId")}, 
	    inverseJoinColumns = {@JoinColumn(name = "medicamentoId", referencedColumnName = "medicamentoId")})
    private List<Medicamento> medicamentos;
    
    public Prescricao() {
	}
    
	public Prescricao(Integer prescricaoId, double dosagem, Period tomarACada, LocalDate tomarAte,
			List<Medicamento> medicamentos) {
		this.prescricaoId = prescricaoId;
		this.dosagem = dosagem;
		this.tomarACada = tomarACada;
		this.tomarAte = tomarAte;
		this.medicamentos = medicamentos;
	}

	public Integer getPrescricaoId() {
		return prescricaoId;
	}

	public void setPrescricaoId(Integer prescricaoId) {
		this.prescricaoId = prescricaoId;
	}

	public double getDosagem() {
		return dosagem;
	}

	public void setDosagem(double dosagem) {
		this.dosagem = dosagem;
	}

	public Period getTomarACada() {
		return tomarACada;
	}

	public void setTomarACada(Period tomarACada) {
		this.tomarACada = tomarACada;
	}

	public LocalDate getTomarAte() {
		return tomarAte;
	}

	public void setTomarAte(LocalDate tomarAte) {
		this.tomarAte = tomarAte;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
    
}
