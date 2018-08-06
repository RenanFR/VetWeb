package com.vetweb.model;
//	@author renan.rodrigues@metasix.com.br

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_animal")
@NamedQueries({@NamedQuery(name = "animalPorNome", query = "SELECT a FROM Animal a WHERE a.nome = :nomeAnimal"),
                @NamedQuery(name = "quantidadeAnimais", query = "SELECT COUNT(a) FROM Animal a"),
                @NamedQuery(name = "consultaGetId", query = "SELECT a FROM Animal a WHERE a.nome = :nomeAnimal"
                        + " AND a.proprietario.pessoaId = :idPessoa"
                        + " AND a.dtNascimento = :nascimentoAnimal")})
public class Animal implements Serializable {
	
	private static final long serialVersionUID = 5772156689530639119L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;
    
	@Column(columnDefinition = "VARCHAR(30)")
    private String nome;
    
    private LocalDate dtNascimento;
    
    private boolean esteril;
    
    private boolean status;
    
    private double peso;
    
    @ManyToOne
    @JoinColumn(name = "pelagemId", referencedColumnName = "pelagemId")
    private Pelagem pelagem;
    
    @ManyToOne
    @JoinColumn(name = "racaId", referencedColumnName = "racaId")
    private Raca raca;
    
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "proprietarioId", referencedColumnName = "pessoaId")
    private Proprietario proprietario;
    
    @OneToOne(mappedBy = "animal")
    @JoinColumn(name = "prontuarioid", referencedColumnName = "prontuarioId")
    private Prontuario prontuario;
    
    private String imagem;

    public Animal() {
    }

    public Animal(String nome, LocalDate dtNascimento, boolean esteril, boolean status, double peso, Pelagem pelagem, Raca raca, Proprietario proprietario) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.esteril = esteril;
        this.status = status;
        this.peso = peso;
        this.pelagem = pelagem;
        this.raca = raca;
        this.proprietario = proprietario;
    }

    public Animal(Long animalId, String nome, LocalDate dtNascimento, boolean esteril, boolean status, double peso, Pelagem pelagem, Raca raca, Proprietario proprietario, Prontuario prontuario) {
        this.animalId = animalId;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.esteril = esteril;
        this.status = status;
        this.peso = peso;
        this.pelagem = pelagem;
        this.raca = raca;
        this.proprietario = proprietario;
        this.prontuario = prontuario;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public boolean isEsteril() {
        return esteril;
    }

    public void setEsteril(boolean esteril) {
        this.esteril = esteril;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Pelagem getPelagem() {
        return pelagem;
    }

    public void setPelagem(Pelagem pelagem) {
        this.pelagem = pelagem;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }
    
    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	@Override
	public String toString() {
		return "Animal{" + "animalId=" + animalId + ", nome=" + nome + ", dtNascimento=" + dtNascimento + ", esteril=" + esteril + ", status=" + status + ", peso=" + peso + ", pelagem=" + pelagem + ", raca=" + raca + ", proprietario=" + proprietario + ", prontuario=" + prontuario + '}';
	}
	
}
