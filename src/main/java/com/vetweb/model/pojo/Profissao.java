package com.vetweb.model.pojo;
// @author Maria Jéssica

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class Profissao implements Serializable{
    @JsonProperty("profissoes")
    private static List<String> profissoes;

    public List<String> getProfissoes() {
        return profissoes;
    }

    public void setProfissoes(List<String> profissoes) {
        this.profissoes = profissoes;
    }
    
}
