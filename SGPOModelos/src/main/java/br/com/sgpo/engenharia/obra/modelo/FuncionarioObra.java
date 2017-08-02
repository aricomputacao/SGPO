/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.modelo;

import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.utilitarios.MetodosUtilitariosData;
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
@Table(name = "funcionario_obra", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FuncionarioObra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuo_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "obr_id", referencedColumnName = "obr_id", nullable = false)
    private Obra obra;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaborador;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "fuo_data_entrada", nullable = false)
    private Date dataEntrada;

    @Temporal(TemporalType.DATE)
    @Column(name = "fuo_data_saida")
    private Date dataSaida;

    @Column(name = "fuo_observacoa")
    private String observacao;
    
    
    public double getTotalDeDiasTrabalhado(){
        if (dataSaida != null) {
            return MetodosUtilitariosData.diferencaEmDias(dataEntrada, dataSaida);
        }
        return 0;
    }

    public FuncionarioObra() {
    }

    public FuncionarioObra(Obra obra) {
        this.obra = obra;
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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
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
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final FuncionarioObra other = (FuncionarioObra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
