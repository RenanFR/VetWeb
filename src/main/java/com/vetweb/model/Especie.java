package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_especie")
public class Especie implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long especieId;
    
	@Column(columnDefinition = "VARCHAR(60)")
    private String descricao;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "especie",fetch = FetchType.EAGER)
    private Set<Raca> racas;
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Raca> getRacas() {
        return racas;
    }

    public void setRacas(Set<Raca> racas) {
        this.racas = racas;
    }

    public Long getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
