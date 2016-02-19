/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.modelo;

import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.engenharia.enumeration.TipoExtencaoArquivo;
import br.com.sgpo.utilitarios.ManipuladorDeArquivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "nota_obra", schema = "engenharia")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class NotaObra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nob_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "for_id", referencedColumnName = "for_id", nullable = false)
    private Fornecedor fornecedor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "obr_id", referencedColumnName = "obr_id", nullable = false)
    private Obra obra;

    @NotEmpty
    @Column(name = "nob_numero", unique = true, nullable = false)
    private String numero;

    @Column(name = "nob_observacao")
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    

    private String getNomeDoArquivo() {
        return this.numero;
    }

    public String getNomeDoArquivoComExtencao() {
        return this.numero.concat(TipoExtencaoArquivo.PDF.getExtencao());
    }

    public String getCaminhoArquivoComExtencao() {
        return getDiretorioDoArquivo().concat(File.separator)
                .concat(getNomeDoArquivo())
                .concat(TipoExtencaoArquivo.PDF.getExtencao());
    }

    public String getDiretorioDoArquivo() {
        return ManipuladorDeArquivo.getDiretorioDocumentosObras()
                .concat(this.obra.getId().toString()).concat(File.separator).concat(ManipuladorDeArquivo.PASTA_NOTA_FISCAL);
    }

    public StreamedContent download() throws FileNotFoundException {
        return ManipuladorDeArquivo.download(getCaminhoArquivoComExtencao(), getNomeDoArquivoComExtencao(), TipoExtencaoArquivo.PDF);
    }

    public void addDocumento(byte[] conteudo) throws IOException, Exception {
        if (conteudo != null) {
            //Criar diretorio local
            ManipuladorDeArquivo.criarDiretorioLocal(this.getDiretorioDoArquivo());

            //Remove do local
//            ManipuladorDeArquivo.checarSeExisteExcluir(this.getCaminhoArquivoComExtencao());

            //Grava no local
            ManipuladorDeArquivo.gravarArquivoLocalmente(this.getDiretorioDoArquivo(), this.getNomeDoArquivoComExtencao(), conteudo);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
