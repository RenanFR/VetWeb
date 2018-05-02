package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import javax.persistence.Embeddable;
@Embeddable
public class Protocolo implements Serializable {
    String nome;
    String especie;
    Integer aplicacao;
    Integer intervalo;
    Vacina vemApos;
}
