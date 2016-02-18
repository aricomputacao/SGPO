/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.modelo;

import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.engenharia.enumeration.TipoEquipamento;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "equipamento_obra", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EquipamentoObra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eqo_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "obr_id", referencedColumnName = "obr_id", nullable = false)
    private Obra obra;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equ_id", referencedColumnName = "equ_id", nullable = false)
    private Equipamento equipamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "eqo_tipo", nullable = false)
    private TipoEquipamento tipoEquipamento;

    @ManyToOne
    @JoinColumn(name = "for_id", referencedColumnName = "for_id")
    private Fornecedor fornecedor;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "eqo_data_entrada", nullable = false)
    private Date dataEntrada;

    @Temporal(TemporalType.DATE)
    @Column(name = "eqo_data_saida")
    private Date dataSaida;

    @NotNull
    @Column(name = "eqo_quantidade", nullable = false)
    private int quantidade;

    @Column(name = "eqo_valor_unitario")
    private BigDecimal valorUnitario;

    @NotNull
    @Column(name = "eqo_ativo", nullable = false)
    private boolean ativo;

    @Column(name = "eqo_observacao")
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getNomeDoFornecedor() {
        return this.fornecedor.getNome();
    }

    public BigDecimal valorTotal() {
        return this.valorUnitario.multiply(new BigDecimal(quantidade));
    }

    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

   
    
    public String getNomeDaObra() {
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

    public EquipamentoObra() {
        this.quantidade = 0;
        this.valorUnitario = BigDecimal.ZERO;
    }

}
