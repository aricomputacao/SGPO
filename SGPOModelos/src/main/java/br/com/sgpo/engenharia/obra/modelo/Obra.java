/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.modelo;

import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.Empresa;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "obra", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Obra implements Serializable {

    @Id
    @NotNull
    @Column(name = "obr_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "obr_descricao", nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador responsavel;

    @NotNull
    @Column(name = "obr_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "obr_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", nullable = false)
    private Empresa empresa;

    @Column(name = "obr_numero")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "end_id", referencedColumnName = "end_id")
    private Endereco endereco;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Projeto> listaDeProjetos;

    public void addProjeto(Projeto p) {
        if (!listaDeProjetos.contains(p)) {
            listaDeProjetos.add(p);
        }
    }

    public void removeProjeto(Projeto p) {
        if (listaDeProjetos.contains(p)) {
            listaDeProjetos.remove(p);
        }
    }

    public String getNomeResponsavel() {
        return responsavel != null ? responsavel.getNome() : "";
    }

    public String getNomeCliente() {
        return cliente != null ? cliente.getNome() : "";
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

    public Colaborador getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Colaborador responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Projeto> getListaDeProjetos() {
        return Collections.unmodifiableList(listaDeProjetos);
    }

    public void setListaDeProjetos(List<Projeto> listaDeProjetos) {
        this.listaDeProjetos = listaDeProjetos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Obra other = (Obra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
