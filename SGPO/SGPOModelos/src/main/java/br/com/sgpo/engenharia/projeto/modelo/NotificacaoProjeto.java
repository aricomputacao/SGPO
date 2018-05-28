/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.modelo;

import br.com.sgpo.administrativo.modelo.Colaborador;
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
import org.hibernate.validator.constraints.NotEmpty;
import org.jsoup.Jsoup;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "notificacao_projeto",schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class NotificacaoProjeto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nop_id",nullable = false)
    private Long id;
    
    @NotEmpty
    @Column(name = "nop_motivo",length = 1024,nullable = false)
    private String motivo;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id",referencedColumnName = "col_id",nullable = false)
    private Colaborador colaborador;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "pro_id",referencedColumnName = "pro_id",nullable = false)
    private Projeto projeto;
    
    @Column(name = "nop_data",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return this.motivo ;
    }
    
    public String getTextoMotivo(){
        return Jsoup.parse(this.motivo).text();
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final NotificacaoProjeto other = (NotificacaoProjeto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
