package com.vetweb.model;
 // @author 11151504898
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "proprietarios")
@NamedQueries({@NamedQuery(name = "quantidadeClientes", query = "SELECT COUNT(p) FROM Proprietario p"),
	@NamedQuery(name = "prontuariosAnimaisDoCliente", query = "SELECT p FROM Prontuario p "
                + "JOIN FETCH p.animal a JOIN FETCH a.proprietario prop WHERE prop.pessoaId = :Id"),
    @NamedQuery(name = "proprietarioPorNome", query = "SELECT p FROM Proprietario p WHERE p.nome = :nomeProprietario")})
public class Proprietario extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	private boolean aceitaNotificacoes;
	
    @Column(columnDefinition = "TEXT")
    private String profissao;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    private String comoNosConheceu;
    
    @ColumnDefault("true")
    private boolean ativo;
    
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.EAGER)
    private List<Animal> animais;
    
    @Column(columnDefinition = "TEXT")
    private String nacionalidade;

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Proprietario(boolean aceitaNotificacoes, String profissao, String observacoes, String comoNosConheceu, String p) {
        this.aceitaNotificacoes = aceitaNotificacoes;
        this.profissao = profissao;
        this.observacoes = observacoes;
        this.comoNosConheceu = comoNosConheceu;
        this.nacionalidade = p;
    }

    public Proprietario() {
    }
    
    public String getComoNosConheceu() {
        return comoNosConheceu;
    }

    public void setComoNosConheceu(String comoNosConheceu) {
        this.comoNosConheceu = comoNosConheceu;
    }

    public boolean isAceitaNotificacoes() {
        return aceitaNotificacoes;
    }

    public void setAceitaNotificacoes(boolean aceitaNotificacoes) {
        this.aceitaNotificacoes = aceitaNotificacoes;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
