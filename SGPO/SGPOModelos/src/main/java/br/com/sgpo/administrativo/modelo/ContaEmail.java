/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.jsoup.Jsoup;

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
    @JoinColumn(name = "emp_id", nullable = false, referencedColumnName = "emp_id", unique = true)
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

    

    public void enviarEmail(List<String> destinos, String mensagem, String titulo) throws EmailException, AddressException, MessagingException {
          //       Recipient's email ID needs to be mentioned.
        String destinatarios = "";
        String rodape = "<br/><br/><br/><br/>  <div style=\"border-top: 1px dashed #c8cdbe;border-top: 1px dashed #c8cdbe \">"
                + "<br/> "
                + "<span style=\"font-style: italic; font-family: Narrow; font-size: large; color: rgb(0, 153, 0);\">Sistema de Gestão de Projetos e Obras - SGPO</span><br/>"
                + "<span style=\"font-size: large; font-style: italic; color: rgb(0, 153, 0); font-family: Narrow;\">Oiti Engenharia e Gestão Ambiental</span>"
                + "</div>";
        
        
        
        
       
        for (String d : destinos) {
            if (d.equals(destinos.get(destinos.size() - 1))) {
                destinatarios = destinatarios.concat(d);
            } else {
                destinatarios = destinatarios.concat(d.concat(","));
            }
        }
        
        
         Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.socketFactory.port", this.porta);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", this.porta);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senha);
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email)); //Remetente

        Address[] toUser = InternetAddress.parse(destinatarios);
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(titulo);//Assunto
        message.setContent(mensagem + rodape, "text/html");
        /**
         * Método para enviar a mensagem criada
         */
        Transport t = session.getTransport("smtps");
        t.connect(this.host, this.email, this.senha);

//        t.sendMessage(message, new Address[]{new InternetAddress("aricomputacao@gmail.com")});
        t.sendMessage(message, message.getAllRecipients());

//        Transport.send(message);
        System.out.println("Feito!!!");
    }
    
    

    public void enviarEmailHtml(List<String> destinos, String msg, String titulo) throws AddressException, MessagingException {
        //       Recipient's email ID needs to be mentioned.
        String destinatarios = "";
        String rodape = "<br/><br/><br/><br/>  <div style=\"border-top: 1px dashed #c8cdbe;border-top: 1px dashed #c8cdbe \">"
                + "Esta mensagem é uma notificação enviada automaticamente por tanto não deve ser respondida. <br/> "
                + "<span style=\"font-style: italic; font-family: Narrow; font-size: large; color: rgb(0, 153, 0);\">Sistema de Gestão de Projetos e Obras - SGPO</span><br/>"
                + "<span style=\"font-size: large; font-style: italic; color: rgb(0, 153, 0); font-family: Narrow;\">Oiti Engenharia e Gestão Ambiental</span>"
                + "</div>";
        
        
        
        
       
        for (String d : destinos) {
            if (d.equals(destinos.get(destinos.size() - 1))) {
                destinatarios = destinatarios.concat(d);
            } else {
                destinatarios = destinatarios.concat(d.concat(","));
            }
        }
        
        
         Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.socketFactory.port", this.porta);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", this.porta);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senha);
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email)); //Remetente

        Address[] toUser = InternetAddress.parse(destinatarios);
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(titulo);//Assunto
        message.setContent(msg + rodape, "text/html");
        /**
         * Método para enviar a mensagem criada
         */
        Transport t = session.getTransport("smtps");
        t.connect(this.host, this.email, this.senha);

//        t.sendMessage(message, new Address[]{new InternetAddress("aricomputacao@gmail.com")});
        t.sendMessage(message, message.getAllRecipients());

//        Transport.send(message);
        System.out.println("Feito!!!");
    }

}
