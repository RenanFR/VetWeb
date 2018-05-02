package com.vetweb.model;
 //@author renanrodrigues
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "colaboradores")
public class Colaborador extends Pessoa {
    private String cargo;
    private double salario;
}
