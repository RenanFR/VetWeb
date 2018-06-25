package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import java.time.LocalDate;

import java.io.Serializable;

import java.time.Period;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prescricoes")
public class Prescricao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer prescricaoId;
    
	private double dosagem;
    
	private Period tomarACada;
    
	private LocalDate tomarAte;
    
    @OneToMany
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
    
}
