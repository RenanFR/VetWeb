package com.vetweb.model.pojo;
// @author Maria Jéssica

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
public class Pais implements Serializable{
    @JsonProperty("gentilico")
    String gentilico;
    @JsonProperty("nome_pais")
    String nomePais;
    @JsonProperty("nome_pais_int")
    String nomeIngles;
    @JsonProperty("sigla")
    String sigla;

    @Override
    public String toString() {
        return nomePais;
    }
    
}
