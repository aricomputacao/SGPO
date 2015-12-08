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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author gilmario
 */
@Entity
@Table(name = "logradouro", schema = "cadastro_basico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Logradouro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lgr_id", nullable = false)
    private Long id;
    @Column(name = "lgr_cep", nullable = false)
    @NotBlank
    private String cep;
    @NotNull(message = "O nome do logradouro não pode ser nulo")
    @NotEmpty(message = "O nome do logradouro não pode ser vazio")
    @NotBlank
    @Column(name = "lgr_nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "lgr_bairro", nullable = false, length = 50)
    private String bairro;
    @ManyToOne
    @JoinColumn(name = "tpl_id", referencedColumnName = "tpl_id", nullable = false)
    private TipoLogradouro tipoLogradouro;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "mun_id", referencedColumnName = "mun_id", nullable = false)
    private Municipio municipio;
    @Column(nullable = false, name = "tpl_ativo", columnDefinition = " boolean DEFAULT false ")
    @NotNull
    private Boolean ativo;

    public Logradouro() {
    }

     public String getNomeDoPais(){
        return this.municipio.getUnidadeFederativa().getPais().getNome();
    }
    
    public String getNomeDaCidade() {
        return this.municipio.getNome();
    }

    public String getAbreviacaoUnidadeFederativa() {
        return this.getMunicipio().getUnidadeFederativa().getSigla();
    }

    public String getNomeUnidadeFederativa() {
        return this.getMunicipio().getUnidadeFederativa().getNome();
    }

    public String getNomeTipoLogradouro() {
        return this.tipoLogradouro.getNome();
    }

    public Logradouro(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.replaceAll("[^0-9]", "");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = RemoveCaracteresUtil.removeAccentos(nome.toUpperCase());
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = RemoveCaracteresUtil.removeAccentos(bairro.toUpperCase());
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    
    public Pais getPais(){
        return municipio.getUnidadeFederativa().getPais();
    }
    
    public UnidadeFederativa getUnidadeFederativa(){
        return municipio.getUnidadeFederativa();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Logradouro other = (Logradouro) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "CEP: " + cep + ", Rua: " + nome + ", Bairro: " + bairro + " " + municipio.getNome()+"-"+municipio.getUnidadeFederativa().getSigla() ;
    }

}
