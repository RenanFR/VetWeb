package com.vetweb.model;

import java.io.Serializable;

//@author renan.rodrigues@metasix.com.br

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tbl_prontuario")
public class Prontuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prontuarioId;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prontuario")
    @JsonBackReference
    private List<OcorrenciaAtendimento> atendimentos;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prontuario")
    @JsonBackReference
    private List<OcorrenciaVacina> vacinas;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prontuario")
    @JsonBackReference
    private List<Documento> documentos;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prontuario")
    @JsonBackReference
    private List<OcorrenciaExame> exames;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prontuario")
    @JsonBackReference
    private List<Prescricao> prescricoes;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prontuario")
    @JsonBackReference
    private List<OcorrenciaPatologia> patologias;
    
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "animalId", referencedColumnName = "animalId")
    private Animal animal;

    public Prontuario() {
    }

    public Prontuario(Animal animal) {
        this.animal = animal;
    }

	public Long getProntuarioId() {
		return prontuarioId;
	}

	public void setProntuarioId(Long prontuarioId) {
		this.prontuarioId = prontuarioId;
	}

	public List<OcorrenciaAtendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<OcorrenciaAtendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<OcorrenciaVacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<OcorrenciaVacina> vacinas) {
		this.vacinas = vacinas;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<OcorrenciaExame> getExames() {
		return exames;
	}

	public void setExames(List<OcorrenciaExame> exames) {
		this.exames = exames;
	}

	public List<Prescricao> getPrescricoes() {
		return prescricoes;
	}

	public void setPrescricoes(List<Prescricao> prescricoes) {
		this.prescricoes = prescricoes;
	}

	public List<OcorrenciaPatologia> getPatologias() {
		return patologias;
	}

	public void setPatologias(List<OcorrenciaPatologia> patologias) {
		this.patologias = patologias;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
    
}
