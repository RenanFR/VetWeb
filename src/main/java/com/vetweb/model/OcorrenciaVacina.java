package com.vetweb.model;
//@author renan.rodrigues@metasix.com.br

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vetweb.model.pojo.OcorrenciaProntuario;
import com.vetweb.model.pojo.TipoOcorrenciaProntuario;

@Entity
@Table(name = "tbl_vacina_event")
public class OcorrenciaVacina extends OcorrenciaProntuario implements Serializable {
	
	private static final long serialVersionUID = 3695573793611769516L;

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "prontuarioId", referencedColumnName = "prontuarioId")
	private Prontuario prontuario;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="vacinaId", referencedColumnName = "vacinaId")
	private Vacina vacina;
	
	private boolean pago;
	
	public OcorrenciaVacina() {
	}
	
	public OcorrenciaVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	@Override
	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	@Override
	public String toString() {
		return vacina.getNome();
	}

	@Override
	public String getDescricao() {
		return this.vacina.getNome();
	}

	@Override
	public TipoOcorrenciaProntuario getTipo() {
		return TipoOcorrenciaProntuario.VACINA;
	}
	
	
}
