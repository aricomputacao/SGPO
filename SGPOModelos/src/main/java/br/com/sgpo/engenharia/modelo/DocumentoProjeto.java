/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.modelo;

import br.com.sgpo.engenharia.enumeration.TipoExtencaoArquivo;
import br.com.sgpo.utilitarios.ManipuladorDeArquivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Resources;
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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "documento_projeto",schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DocumentoProjeto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dop_id",nullable = false)
    private Long id;
    
    @NotEmpty
    @Column(name = "dop_nome",nullable = false,unique = true)
    private String nome;
    
    @Column(name = "dop_descricao",length = 1024)
    private String descricao;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "pro_id",referencedColumnName = "pro_id",nullable = false)
    private Projeto projeto;
    

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dop_extencao",nullable = false)
    private TipoExtencaoArquivo extencaoArquivo;
    
    
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public TipoExtencaoArquivo getExtencaoArquivo() {
        return extencaoArquivo;
    }

    public void setExtencaoArquivo(TipoExtencaoArquivo extencaoArquivo) {
        this.extencaoArquivo = extencaoArquivo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final DocumentoProjeto other = (DocumentoProjeto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    private String getNomeDoArquivo(){
        return this.nome.concat(this.id.toString());
    }
    public String getNomeDoArquivoComExtencao(){
        return this.nome.concat(this.id.toString().concat(extencaoArquivo.getExtencao()));
    }
    
    public String getCaminhoArquivo(){
        return ManipuladorDeArquivo.getDiretorioDocumentos()
                .concat(getNomeDoArquivo())
                .concat(this.extencaoArquivo.getExtencao());
    }
    
    public StreamedContent download() throws FileNotFoundException{
        return ManipuladorDeArquivo.download(getCaminhoArquivo(),getNomeDoArquivoComExtencao(), extencaoArquivo);
    }
    
    
}
