package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "tiposDeAtendimento")
@NamedQueries({@NamedQuery(name = "tiposDeAtendimentoQuery", query = "SELECT t FROM TipoDeAtendimento t"),
                @NamedQuery(name = "tipoDeAtendimentoPorNomeQuery", query = "SELECT t FROM TipoDeAtendimento t WHERE t.nome = :nomeTipoAtendimento")})
public class TipoDeAtendimento implements Serializable {
	
	private static final long serialVersionUID = -686226545519235798L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tipoDeAtendimentoId;
	
    private String nome;
    
    private Duration duracao;
    
    private Period frequencia;
    
    private boolean status;
    
    private StringBuilder modeloAtendimento;
    
    private BigDecimal custo;

    public TipoDeAtendimento() {
    }
    
    public TipoDeAtendimento(String nome, Duration duracao, Period frequencia, boolean status, StringBuilder modeloAtendimento) {
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

    public StringBuilder getModeloAtendimento() {
        return modeloAtendimento;
    }

    public void setModeloAtendimento(StringBuilder modeloAtendimento) {
        this.modeloAtendimento = modeloAtendimento;
    }

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
}
