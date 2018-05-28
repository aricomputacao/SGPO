/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.modelo;

import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.administrativo.modelo.UnidadeDeMedida;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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
@Table(name = "item_obra", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemObra implements Serializable,Comparable<ItemObra> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ito_id", nullable = false)
    private Long id;

    @Column(name = "ito_descricao")
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "udm_id", referencedColumnName = "udm_id", nullable = false)
    private UnidadeDeMedida unidadeDeMedida;

    @NotNull
    @Column(name = "ito_quantidade", nullable = false)
    private Float quantidade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ite_id", referencedColumnName = "ite_id", nullable = false)
    private Item item;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "eta_id", referencedColumnName = "eta_id", nullable = false)
    private Etapa etapa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "for_id", referencedColumnName = "for_id", nullable = false)
    private Fornecedor fornecedor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "obr_id", referencedColumnName = "obr_id", nullable = false)
    private Obra obra;

    @NotNull
    @Column(name = "ito_preco", nullable = false)
    private BigDecimal valorUnitario;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "ito_data", nullable = false)
    private Date data;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ito_data_pagamento")
    private Date dataPagamento;

    @NotNull
    @Column(name = "ito_pago", nullable = false)
    private boolean pago;
    

    public int getDia(){
        Calendar dia  =  Calendar.getInstance();
        dia.setTime(this.data);
        return dia.get(Calendar.DAY_OF_MONTH);
    }
    public int getMes(){
        Calendar dia  =  Calendar.getInstance();
        dia.setTime(this.data);
        return dia.get(Calendar.MONTH);
    }
    
    public int getQuinzena(){
        Calendar dia  =  Calendar.getInstance();
        dia.setTime(this.data);
        
        if (dia.get(Calendar.DAY_OF_MONTH) <= 15) {
            return 1;
        } else {
            return 2;
        }
        
    }
    
    
    public BigDecimal getValorTotal() {
        return this.valorUnitario.multiply(new BigDecimal(this.quantidade));
    }

    public String getNomeFornecedor() {
        return this.fornecedor.getNome();
    }

    public String getClassificacaoItem(){
        return this.item.getClassificacao().getDescricao();
    }
    
    public String getNomeItem() {
        return this.item.getNome();
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

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ItemObra other = (ItemObra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(ItemObra o) {
        if (this.getQuinzena() < o.getQuinzena()) {
            return -1;
        }
        if (this.getQuinzena() > o.getQuinzena()) {
            return 1;
        }
        return 0;
    }

  

}
