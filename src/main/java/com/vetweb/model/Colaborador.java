package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "tbl_colaborador")
public class Colaborador extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition = "VARCHAR(30)")
	private String cargo;
	
	private double salario;
	
	public Colaborador() {
	}
	
    public Colaborador(String cargo, double salario) {
		super();
		this.cargo = cargo;
		this.salario = salario;
	}
    
	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
}
