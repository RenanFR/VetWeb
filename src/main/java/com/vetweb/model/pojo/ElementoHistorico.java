package com.vetweb.model.pojo;

import java.time.LocalDate;

public class ElementoHistorico {
	
	private Long elementohistoricoId;
	
	private String descricao;
	
	private LocalDate data;
	
	private TipoElementoHistorico tipo;

	public ElementoHistorico() {
	}

	public ElementoHistorico(Long elementohistoricoId, String descricao, LocalDate data, TipoElementoHistorico tipo) {
		this.elementohistoricoId = elementohistoricoId;
		this.descricao = descricao;
		this.data = data;
		this.tipo = tipo;
	}

	public Long getElementohistoricoId() {
		return elementohistoricoId;
	}

	public void setElementohistoricoId(Long elementohistoricoId) {
		this.elementohistoricoId = elementohistoricoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public TipoElementoHistorico getTipo() {
		return tipo;
	}

	public void setTipo(TipoElementoHistorico tipo) {
		this.tipo = tipo;
	}

}
