package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "modelosDocumento")
public class ModeloDocumento implements Serializable {
    @Id
    String nome;
    boolean infoCliente;
    StringBuilder modelo;
}
