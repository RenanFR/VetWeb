package com.vetweb.model;
// @author Maria Jéssica

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Especie implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long especieId;
    private String descricao;
    @JsonManagedReference
    @OneToMany(mappedBy = "especie",fetch = FetchType.EAGER)
//    @Cascade(CascadeType.ALL)//Operações serão feitas em cascada a partir do objeto pai p/ filhos
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
