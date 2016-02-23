/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.modelo;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "falta_colaborador", schema = "administrativo",uniqueConstraints = @UniqueConstraint(columnNames = {"fal_data","col_id"}))
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class FaltaColaborador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fal_id", nullable = false)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "fal_data",nullable = false)
    private Date data;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "fal_observacoa")
    private String observacao;

    public FaltaColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public FaltaColaborador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final FaltaColaborador other = (FaltaColaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
