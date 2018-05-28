/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.modelo;

import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.engenharia.enumeration.TipoMovimentacaoDocumento;
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
@Table(name = "movimentacao_documento", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class MovimentacaoDocumento implements Serializable {

    @Id
    @Column(name = "mvd_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mvd_tipo", nullable = false)
    private TipoMovimentacaoDocumento tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_antigo_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaboradorAntigo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_novo_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaboradorNovo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dop_id", referencedColumnName = "dop_id", nullable = false)
    private DocumentoProjeto documentoProjeto;

    @NotNull
    @Column(name = "mvd_data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "mvd_descricao", length = 1024)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    
    
    public Long getId() {
        return id;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacaoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacaoDocumento tipo) {
        this.tipo = tipo;
    }

    public Colaborador getColaboradorAntigo() {
        return colaboradorAntigo;
    }

    public void setColaboradorAntigo(Colaborador colaboradorAntigo) {
        this.colaboradorAntigo = colaboradorAntigo;
    }

    public Colaborador getColaboradorNovo() {
        return colaboradorNovo;
    }

    public void setColaboradorNovo(Colaborador colaboradorNovo) {
        this.colaboradorNovo = colaboradorNovo;
    }

    public DocumentoProjeto getDocumentoProjeto() {
        return documentoProjeto;
    }

    public void setDocumentoProjeto(DocumentoProjeto documentoProjeto) {
        this.documentoProjeto = documentoProjeto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final MovimentacaoDocumento other = (MovimentacaoDocumento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
