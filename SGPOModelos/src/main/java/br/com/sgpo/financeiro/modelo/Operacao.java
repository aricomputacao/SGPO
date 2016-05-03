/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.modelo;

import br.com.sgpo.financeiro.enumeration.PeriodoRecorrencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
@Table(name = "operacao", schema = "financeiro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Operacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ope_id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "ope_descricao", nullable = false, length = 1024)
    private String descricao;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "ope_data", nullable = false)
    private Date dateOperacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cop_id", referencedColumnName = "cop_id", nullable = false)
    private CategoriaOperacao categoriaOperacao;

    @Column(name = "ope_recorrencia")
    private boolean recorrencia;

    @Column(name = "ope_tempo_recorrencia")
    private int tempoRecorrencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "ope_periodo_recorrencia")
    private PeriodoRecorrencia periodoRecorrencia;

    @Column(name = "ope_parcelamento")
    private boolean parcelamento;
 
    @Column(name = "ope_nota_fiscal")
    private boolean possuiNota;
 
    @Column(name = "ope_numero_nota")
    private String numeroNota;

    @Column(name = "ope_numero_parcelas")
    private int numeroParcelas;

    public BigDecimal getValorDaOperacao(List<FaturaOperacao> fos) {
        BigDecimal valor = BigDecimal.ZERO;
        for (FaturaOperacao fo : fos) {
            valor = valor.add(fo.getValor());
        }

        return valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDateOperacao() {
        return dateOperacao;
    }

    public void setDateOperacao(Date dateOperacao) {
        this.dateOperacao = dateOperacao;
    }

    public CategoriaOperacao getCategoriaOperacao() {
        return categoriaOperacao;
    }

    public void setCategoriaOperacao(CategoriaOperacao categoriaOperacao) {
        this.categoriaOperacao = categoriaOperacao;
    }

    public boolean isRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(boolean recorrencia) {
        this.recorrencia = recorrencia;
    }

    public int getTempoRecorrencia() {
        return tempoRecorrencia;
    }

    public void setTempoRecorrencia(int tempoRecorrencia) {
        this.tempoRecorrencia = tempoRecorrencia;
    }

    public PeriodoRecorrencia getPeriodoRecorrencia() {
        return periodoRecorrencia;
    }

    public void setPeriodoRecorrencia(PeriodoRecorrencia periodoRecorrencia) {
        this.periodoRecorrencia = periodoRecorrencia;
    }

    public boolean isParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(boolean parcelamento) {
        this.parcelamento = parcelamento;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public boolean isPossuiNota() {
        return possuiNota;
    }

    public void setPossuiNota(boolean possuiNota) {
        this.possuiNota = possuiNota;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Operacao other = (Operacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
