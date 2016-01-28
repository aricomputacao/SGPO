/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.modelo;

import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.Empresa;
import br.com.sgpo.engenharia.enumeration.FaseProjeto;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "projeto",schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Projeto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id",nullable = false)
    private Long id;
    
    @NotEmpty
    @Column(name = "pro_nome",nullable = false)
    private String nome;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "tip_id",referencedColumnName = "tip_id",nullable = false)
    private TipoProjeto tipo;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pro_fase",nullable = false)
    private FaseProjeto fase;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id",referencedColumnName = "col_id",nullable = false)
    private Colaborador responsavel;
    
    @NotNull
    @Column(name = "pro_inicio",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    
    @Column(name = "pro_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cli_id",referencedColumnName = "cli_id",nullable = false)
    private Cliente cliente;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "emp_id",nullable = false)
    private Empresa empresa;
    
    @Column(name = "pro_observacao",length = 1024)
    private String observacao;
    
    @Column(name = "pro_numero")    
    private String numero;

    
    
    public String getNomeResponsavel(){
        return responsavel != null ? responsavel.getNome() : "";
    }
    
    public String getNomeCliente(){
        return cliente != null ? cliente.getNome() : "";
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProjeto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProjeto tipo) {
        this.tipo = tipo;
    }

    public FaseProjeto getFase() {
        return fase;
    }

    public void setFase(FaseProjeto fase) {
        this.fase = fase;
    }

    public Colaborador getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Colaborador responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projeto other = (Projeto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
