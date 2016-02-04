/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.modelo;

import br.com.sgpo.engenharia.enumeration.TipoExtencaoArquivo;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitarios.ManipuladorDeArquivo;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "documento_projeto", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DocumentoProjeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dop_id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "dop_nome", nullable = false)
    private String nome;

  

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id", nullable = false)
    private Projeto projeto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dop_extencao", nullable = false)
    private TipoExtencaoArquivo extencaoArquivo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id", nullable = false)
    private Usuario usuario;

    @Column(name = "dop_ativo", nullable = false)
    private boolean ativo;
   

    @Temporal(TemporalType.DATE)
    @Column(name = "dop_data")
    private Date dataUpload;
    
    
    public boolean getDisponivelParaUpload(Usuario usuario) {
        return  ((! this.ativo) && this.getUsuario().equals(usuario)) ;
    }

    public Date getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(Date dataUpload) {
        this.dataUpload = dataUpload;
    }
    
    
    
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    private String getNomeDoArquivo() {
        return this.nome;
    }

    public String getNomeDoArquivoComExtencao() {
        return this.nome.concat(extencaoArquivo.getExtencao());
    }

    public String getCaminhoArquivoComExtencao() {
        return getDiretorioDoArquivo().concat(File.separator)
                .concat(getNomeDoArquivo())
                .concat(this.extencaoArquivo.getExtencao());
    }

    public String getDiretorioDoArquivo() {
        return ManipuladorDeArquivo.getDiretorioDocumentos()
                .concat(this.projeto.getId().toString());
    }

    public StreamedContent download() throws FileNotFoundException {
        return ManipuladorDeArquivo.download(getCaminhoArquivoComExtencao(), getNomeDoArquivoComExtencao(), extencaoArquivo);
    }

}
