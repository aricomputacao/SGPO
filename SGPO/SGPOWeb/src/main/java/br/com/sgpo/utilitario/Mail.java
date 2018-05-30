/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitario;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

/**
 *
 * @author ari
 */
@Stateless
public class Mail {

//    @Resource(name = "java:jboss/mail/gmail")
//    @Resource(name = "java:jboss/mail/gmail")
//    private Session session;
    @Transactional
    public void enviarEmail(String destinantariosSeparadosPorVirgula, String titulo, String conteudo) throws AddressException, MessagingException {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.eusebio.ce.gov.br");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sefin@eusebio.ce.gov.br", "eusebio@sefin123");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sefin@eusebio.ce.gov.br")); //Remetente

        Address[] toUser = InternetAddress.parse(destinantariosSeparadosPorVirgula);
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(titulo);//Assunto
        message.setText(conteudo);
        /**
         * Método para enviar a mensagem criada
         */
        Transport t = session.getTransport("smtps");
        t.connect("smtp.eusebio.ce.gov.br", "sefin@eusebio.ce.gov.br", "eusebio@sefin123");

//        t.sendMessage(message, new Address[]{new InternetAddress("aricomputacao@gmail.com")});
        t.sendMessage(message, message.getAllRecipients());

//        Transport.send(message);
        System.out.println("Feito!!!");

    }

    @Transactional
    public void enviarEmail(String destinantariosSeparadosPorVirgula, String titulo, String conteudo, String smtp, String port, String remetente, String senha) throws AddressException, MessagingException {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remetente)); //Remetente

        Address[] toUser = InternetAddress.parse(destinantariosSeparadosPorVirgula);
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(titulo);//Assunto
        message.setText(conteudo);
        /**
         * Método para enviar a mensagem criada
         */
        Transport t = session.getTransport("smtps");
        t.connect(smtp, remetente, senha);

//        t.sendMessage(message, new Address[]{new InternetAddress("aricomputacao@gmail.com")});
        t.sendMessage(message, message.getAllRecipients());

//        Transport.send(message);
        System.out.println("Feito!!!");

    }

    @Transactional
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
        props.put("mail.smtp.host", "smtp.eusebio.ce.gov.br");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sefin@eusebio.ce.gov.br", "eusebio@sefin123");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sefin@eusebio.ce.gov.br")); //Remetente

        Address[] toUser = InternetAddress.parse(destinatarios);
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(titulo);//Assunto
        message.setContent(msg + rodape, "text/html");
        /**
         * Método para enviar a mensagem criada
         */
        Transport t = session.getTransport("smtps");
        t.connect("smtp.eusebio.ce.gov.br", "sefin@eusebio.ce.gov.br", "eusebio@sefin123");

//        t.sendMessage(message, new Address[]{new InternetAddress("aricomputacao@gmail.com")});
        t.sendMessage(message, message.getAllRecipients());

//        Transport.send(message);
        System.out.println("Feito!!!");
        
    }

    public static void main(String[] args) {
        try {
            Mail m = new Mail();
            m.enviarEmail("javafalo@gmail.com", "TESTE", "TESTE");
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
