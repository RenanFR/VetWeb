package com.vetweb.model.pojo;

public class ElementoHistorico {
	
	private Long elementohistoricoId;
	
	private String descricao;
	
	private String data;
	
	private TipoElementoHistorico tipo;

	public ElementoHistorico() {
	}

	public ElementoHistorico(Long elementohistoricoId, String descricao, String data, TipoElementoHistorico tipo) {
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public TipoElementoHistorico getTipo() {
		return tipo;
	}

	public void setTipo(TipoElementoHistorico tipo) {
		this.tipo = tipo;
	}

}
