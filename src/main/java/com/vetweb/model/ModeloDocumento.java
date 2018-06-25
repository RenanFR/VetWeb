package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "modelosDocumento")
public class ModeloDocumento implements Serializable {
	
    @Id
    private String nome;
    
    private boolean infoCliente;
    
    private StringBuilder modelo;
    
    public ModeloDocumento() {
	}
    

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isInfoCliente() {
		return infoCliente;
	}

	public void setInfoCliente(boolean infoCliente) {
		this.infoCliente = infoCliente;
	}

	public StringBuilder getModelo() {
		return modelo;
	}

	public void setModelo(StringBuilder modelo) {
		this.modelo = modelo;
	}
    
}
