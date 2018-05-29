package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prontuarios")
public class Prontuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prontuarioId;
	
    @OneToMany(fetch = FetchType.LAZY)
    private List<Atendimento> atendimentos;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProntuarioVacina> vacinas;
    
    @OneToMany
    private List<Documento> documentos;
    
    @OneToMany
    private List<Exame> exames;
    
    @OneToMany
    private List<Prescricao> prescricoes;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProntuarioPatologia> patologias;
    
    @OneToOne
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

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<ProntuarioVacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<ProntuarioVacina> vacinas) {
		this.vacinas = vacinas;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public List<Prescricao> getPrescricoes() {
		return prescricoes;
	}

	public void setPrescricoes(List<Prescricao> prescricoes) {
		this.prescricoes = prescricoes;
	}

	public List<ProntuarioPatologia> getPatologias() {
		return patologias;
	}

	public void setPatologias(List<ProntuarioPatologia> patologias) {
		this.patologias = patologias;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
    
}
