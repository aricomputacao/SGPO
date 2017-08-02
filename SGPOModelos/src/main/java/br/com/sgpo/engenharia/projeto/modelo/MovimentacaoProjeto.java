/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.modelo;

import br.com.sgpo.administrativo.modelo.Colaborador;
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

/**
 *
 * @author ari
 */
@Entity
@Table(name = "movimentacao_projeto",schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class MovimentacaoProjeto implements Serializable{
    
    @Id
    @Column(name = "mov_pro",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "mov_fase",nullable = false)
    @Enumerated(EnumType.STRING)
    private FaseProjeto fase;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mov_data",nullable = false)
    private Date data;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "pro_id",referencedColumnName = "pro_id",nullable = false)
    private Projeto projeto;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id",referencedColumnName = "col_id",nullable = false)
    private Colaborador colaborador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FaseProjeto getFase() {
        return fase;
    }

    public void setFase(FaseProjeto fase) {
        this.fase = fase;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final MovimentacaoProjeto other = (MovimentacaoProjeto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
