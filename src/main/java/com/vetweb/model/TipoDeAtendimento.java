package com.vetweb.model;

//@author renan.rodrigues@metasix.com.br

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.Duration;
import java.time.Period;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vetweb.dao.converter.FrequenciaConverter;

@Entity
@Table(name = "tbl_tipo_atendimento")
@NamedQueries({@NamedQuery(name = "tiposDeAtendimentoQuery", query = "SELECT tipo FROM TipoDeAtendimento tipo"),
                @NamedQuery(name = "tipoDeAtendimentoPorNomeQuery", query = "SELECT tp FROM TipoDeAtendimento tp WHERE tp.nome = :tipoDeAtendimento")})
public class TipoDeAtendimento implements Serializable {
	
	private static final long serialVersionUID = -686226545519235798L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoDeAtendimentoId;
	
    private String nome;
    
    private Duration duracao;
    
    @Convert(converter = FrequenciaConverter.class)
    private Period frequencia;
    
    private boolean status;
    
    @Column(columnDefinition = "TEXT")
    private String modeloAtendimento;
    
    private BigDecimal custo;
    
    @OneToMany(mappedBy = "tipoDeAtendimento")
    private List<Atendimento> atendimentos;

    public TipoDeAtendimento() {
    }
    
    public TipoDeAtendimento(String nome, Duration duracao, Period frequencia, boolean status, String modeloAtendimento) {
        this.nome = nome;
        this.duracao = duracao;
        this.frequencia = frequencia;
        this.status = status;
        this.modeloAtendimento = modeloAtendimento;
    }
    
    public Long getTipoDeAtendimentoId() {
        return tipoDeAtendimentoId;
    }

    public void setTipoDeAtendimentoId(Long tipoDeAtendimentoId) {
        this.tipoDeAtendimentoId = tipoDeAtendimentoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public Period getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Period frequencia) {
        this.frequencia = frequencia;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getModeloAtendimento() {
        return modeloAtendimento;
    }

    public void setModeloAtendimento(String modeloAtendimento) {
        this.modeloAtendimento = modeloAtendimento;
    }

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
}
