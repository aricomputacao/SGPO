/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.cadastrobasico.modelo;

import br.com.sgpo.utilitarios.RemoveCaracteresUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gilmario
 */
@Entity
@Table(name = "tipo_logradouro", schema = "cadastro_basico")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TipoLogradouro implements Serializable {

    @Id
    @Column(name = "tpl_id", nullable = false, length = 3)
    @NotBlank
    private String id;
    
    @NotBlank
    @Column(name = "tpl_nome", nullable = false, length = 20, unique = true)
    private String nome;
    
    @Column(nullable = false, name = "tpl_ativo", columnDefinition = " boolean DEFAULT false ")
    @NotNull
    private boolean ativo;

    public TipoLogradouro() {
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoLogradouro(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = RemoveCaracteresUtil.removeAccentos(id.toUpperCase());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = RemoveCaracteresUtil.removeAccentos(nome.toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoLogradouro other = (TipoLogradouro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
