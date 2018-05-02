package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "medicamentos")
public class Medicamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer medicamentoId;
    String descricao;
    boolean usoControlado;
}
