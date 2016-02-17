/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author ari
 */
@Entity
@Table(name = "equipamento_obra",schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EquipamentoObra implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eqo_id",nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "obr_id",referencedColumnName = "obr_id",nullable = false)
    private Obra obra;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "equ_id",referencedColumnName = "equ_id",nullable = false)
    private Equipamento equipamento;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "eqo_data",nullable = false)
    private Date dataEntrada;
    
    @NotNull
    @Column(name = "eqo_quantidade",nullable = false)
    private int  quantidade;
    
    @NotNull
    @Column(name = "eqo_ativo",nullable = false)
    private boolean ativo;

    @Column(name = "eqo_observacao")
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
    
    public String getTipoDeEquipamento(){
        return this.equipamento.getTipoEquipamento().getDescricao();
    }
    
    public String getNomeDaObra(){
       return this.obra.getDescricao();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final EquipamentoObra other = (EquipamentoObra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
