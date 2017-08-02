/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.modelo;

import br.com.sgpo.financeiro.enumeration.Cor;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "categoria_operacao",schema = "financeiro",uniqueConstraints = 
        @UniqueConstraint(columnNames = {"cop_nome","cop_tipo"}))
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CategoriaOperacao implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cop_id",nullable = false)
    private Long id;
    
    @NotEmpty
    @Column(name = "cop_nome",nullable = false)
    private String nome;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cop_cor",nullable = false)
    private Cor cor;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cop_tipo",nullable = false)
    private TipoDeOperacao tipoDeOperacao;

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

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public TipoDeOperacao getTipoDeOperacao() {
        return tipoDeOperacao;
    }

    public void setTipoDeOperacao(TipoDeOperacao tipoDeOperacao) {
        this.tipoDeOperacao = tipoDeOperacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final CategoriaOperacao other = (CategoriaOperacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
