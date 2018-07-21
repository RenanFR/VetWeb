package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import java.util.List;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_prontuario")
public class Prontuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prontuarioId;
	
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_prontuario_atendimento", 
	    joinColumns = {@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId") }, 
	    inverseJoinColumns = {	@JoinColumn(name = "atendimentoId", referencedColumnName = "atendimentoId") })
    private List<Atendimento> atendimentos;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_prontuario_vacina",
	    joinColumns = {@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")}, 
	    inverseJoinColumns = {@JoinColumn(name = "vacinaId", referencedColumnName = "prontuarioVacinaId")  })
    private List<ProntuarioVacina> vacinas;
    
    @OneToMany
    @JoinTable(name = "tbl_prontuario_documento", 
	    joinColumns = {@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")   },
	    inverseJoinColumns = {	@JoinColumn(name = "documentoId", referencedColumnName = "documentoId") })
    private List<Documento> documentos;
    
    @OneToMany
    @JoinTable(name = "tbl_prontuario_exame",
	    joinColumns = {@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId") },
	    inverseJoinColumns = {@JoinColumn(name = "exameId", referencedColumnName = "exameId")})
    private List<Exame> exames;
    
    @OneToMany
    @JoinTable(name = "tbl_prontuario_prescricao", 
	    joinColumns = {@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")},
	    inverseJoinColumns = {@JoinColumn(name = "prescricaoId", referencedColumnName = "prescricaoId")})
    private List<Prescricao> prescricoes;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_prontuario_patologia",
	    joinColumns = {@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId") }, 
	    inverseJoinColumns = {@JoinColumn(name = "patologiaId", referencedColumnName = "prontuarioPatologiaId") })
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
