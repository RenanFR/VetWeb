package com.vetweb.model;
 // @author 11151504898
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
@Entity//Informa a implementação da JPA (Hibernate) que a classe representa uma tabela no banco de dados
@Table(name = "pessoas")//Permite personalizar nome da tabela dentre outros parametros. Por padrão nomeia c/ classe
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {//Tabelas com relacionamentos devem implementar Serializable
    public enum TipoPessoa { FISICA, JURIDICA }
    @Id//Informa que o atributo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.AUTO)//Informa que a geração do valor será via auto-incremento
    private Long pessoaId;
    @NotBlank
    private String nome;
    @NotBlank
    private String rg;
    @NotBlank @CPF
    private String cpf;
    private char sexo;
    private TipoPessoa tipoPessoa;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inclusao;
    private String nacionalidade;//@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento; 
    private Endereco endereco;
    private Contato contato;

    @Override
    public String toString() {
        return nome;
    }

    public Pessoa() {
        this.inclusao = LocalDate.now();
    }

    public Pessoa(Long pessoaId, String nome, String rg, String cpf, char sexo, TipoPessoa tipoPessoa, LocalDate inclusao, String nacionalidade, LocalDate nascimento, Endereco endereco, Contato contato) {
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.tipoPessoa = tipoPessoa;
        this.inclusao = inclusao;
        this.nacionalidade = nacionalidade;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.contato = contato;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
    public LocalDate getInclusao() {
        return inclusao;
    }

    public void setInclusao(LocalDate inclusao) {
//        this.inclusao = LocalDate.parse(inclusao.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.inclusao = inclusao;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
//        this.nascimento = LocalDate.parse(nascimento.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
    
}
