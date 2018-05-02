package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "exames")
public class Exame implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long exameId;
    StringBuilder apresentacao;
    StringBuilder encerramento;
}
