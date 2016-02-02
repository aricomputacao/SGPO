/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.modelo;

import java.io.Serializable;
import java.util.List;
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
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "conta_email", schema = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ContaEmail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coe_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false, referencedColumnName = "emp_id")
    private Empresa empresa;

    @NotBlank
    @Email
    @Column(name = "coe_email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "coe_senha", nullable = false)
    private String senha;

    @NotBlank
    @Column(name = "coe_host", nullable = false)
    private String host;

    @NotNull
    @Column(name = "coe_porta", nullable = false)
    private Integer porta;

    @NotEmpty
    @Column(name = "coe_descricao", nullable = false)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ContaEmail other = (ContaEmail) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public ContaEmail() {
        this.host = "smtp.gmail.com";
        this.porta = 465;
    }

    public void enviarEmail(List<String> destinos, String mensagem, String titulo) throws EmailException {
        SimpleEmail email = new SimpleEmail();

        email.setHostName(this.host);
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(this.porta);

        //Adicione os destinatários
        for (String destino : destinos) {
            email.addTo(destino, "", "UTF-8");
        }

        //Configure o seu Email do qual enviará
        email.setFrom(this.email, this.empresa.getNome());
        //Adicione um assunto
        email.setSubject(titulo);
        //Adicione a mensagem do Email
        email.setMsg(mensagem);
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        email.setTLS(true);
        email.setSSL(true);

        email.setAuthentication(this.email, this.senha);
        email.send();
    }

}
