/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.cadastrobasico.modelo;

import br.com.sgpo.utilitarios.CpfCnpjUtil;
import br.com.sgpo.utilitarios.RemoveCaracteresUtil;
import br.com.sgpo.utilitarios.StringUtil;
import java.io.Serializable;
import java.util.Calendar;
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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "pessoa", schema = "cadastro_basico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id", nullable = false)
    private Long id;

//    @CPF
    @NotBlank
    @Column(name = "pes_cpf")
    private String cpf;

    @NotBlank
    @Column(name = "pes_nome", nullable = false)
    private String nome;

    @Column(name = "pes_nome_pai")
    private String nomeDoPai;

    @Column(name = "pes_nome_mae")
    private String nomeDoMae;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pes_sexo")
    private Sexo sexo;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pes_estado_civil")
    private EstadoCivil estadoCivil;

    @Column(name = "pes_tipo_sanguineo")
    private String tipoSanguineo;

    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Profissao profissao;

    @ManyToOne
    @JoinColumn(name = "lgr_id", referencedColumnName = "lgr_id")
    private Logradouro logradouro;

    @ManyToOne
    @JoinColumn(name = "nac_id", referencedColumnName = "nac_id")
    private Nacionalidade nacionalidade;

    @Temporal(TemporalType.DATE)
    @Column(name = "pes_data_nascimento")
    private Date dataNascimento;

    @Email
    @Column(name = "pes_email")
    private String email;

    @Column(name = "pes_tel_residencia")
    private String telefoneResidencial;

    @Column(name = "pes_tel_trabalho")
    private String telefoneTrabalho;

    @Column(name = "pes_tel_celular")
    private String telefoneCelular;

    @Column(name = "pes_numero", length = 20)
    private String numero;

    @Column(name = "pes_complemento", length = 120)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "esc_id", referencedColumnName = "esc_id")
    private Escolaridade escolaridade;

    @Column(name = "pes_nome_conjuge")
    private String nomeConjuge;

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEmail() {
        return email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneResidencial() {
        return StringUtil.formatarTelefone(telefoneResidencial);
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = RemoveCaracteresUtil.removerCaracteres(telefoneResidencial);
    }

    public String getTelefoneTrabalho() {
        return StringUtil.formatarTelefone(telefoneTrabalho);
    }

    public void setTelefoneTrabalho(String telefoneTrabalho) {
        this.telefoneTrabalho = RemoveCaracteresUtil.removerCaracteres(telefoneTrabalho);
    }

    public String getTelefoneCelular() {
        return StringUtil.formatarTelefone(telefoneCelular);
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = RemoveCaracteresUtil.removerCaracteres(telefoneCelular);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = RemoveCaracteresUtil.removerCaracteres(cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public void setNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getNomeDoMae() {
        return nomeDoMae;
    }

    public void setNomeDoMae(String nomeDoMae) {
        this.nomeDoMae = nomeDoMae;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Nacionalidade getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfFormatado() {
        return CpfCnpjUtil.formataCPF(this.cpf);
    }

    public Date getDiaDeNascimento() {
        if (this.dataNascimento != null) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(this.getDataNascimento().getTime());
            c.set(Calendar.YEAR, Calendar.YEAR);
            return c.getTime();
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String[] tipoSanguineos() {
        String[] tp = new String[8];
        tp[0] = "A+";
        tp[1] = "A-";
        tp[2] = "B+";
        tp[3] = "B-";
        tp[4] = "AB+";
        tp[5] = "AB-";
        tp[6] = "O+";
        tp[7] = "O-";
        return tp;
    }

}
    
    
