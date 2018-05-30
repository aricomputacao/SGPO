/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jsoup.Jsoup;

/**
 *
 * @author ari
 */
public class StringUtil {

    public static String[] quebrarStringPorSeparador(String separador, String valor) {
        String[] i = valor.split(separador);
        return i;
    }

    public static String formatarTelefone(String telefone) {
        if (telefone == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(telefone);
        if (sb.length() == 10) {
            sb.insert(0, "(");
            sb.insert(3, ")");
            sb.insert(8, "-");

        } else if (sb.length() == 8) {
            sb.insert(4, "-");
        } else if (sb.length() == 11) {
            sb.insert(0, "(");
            sb.insert(3, ")");
            sb.insert(9, "-");
        } else if (sb.length() == 9) {
            sb.insert(5, "-");
        }

        return sb.toString();

    }
    
    public static String removerCaracteresEspeciais(String st) {
        
        return st.replaceAll("[^a-zZ-Z0-9 ]", "");
      }

    
     public static String removeAccentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
     
     
     public void enviarEmail(List<String> destinos, String mensagem, String titulo) throws EmailException {
        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(465);

        //Adicione os destinatários
        for (String destino : destinos) {
            email.addTo(destino, "", "UTF-8");
        }
        email.setSentDate(new Date());

        //Configure o seu Email do qual enviará
        email.setFrom("javafalo@gmail.com", "TESTE");
        //Adicione um assunto
        email.setSubject(titulo);
        //Adicione a mensagem do Email
        email.setMsg(Jsoup.parse(mensagem).text());
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        email.setTLS(true);
        email.setSSL(true);

        email.setAuthentication("javafalo@gmail.com", "c90150b123");
        email.send();
    }
     
     
//     public void enviarEmail2(List<String> destinos, String mensagem, String titulo) throws AddressException, MessagingException {
//        Properties props = new Properties();
//        /**
//         * Parâmetros de conexão com servidor Gmail
//         */
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.port", "465");
//
//        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("javafalo@gmail.com", "c90150b123");
//            }
//        });
//
//     
//        /**
//         * Ativa Debug para sessão
//         */
//        session.setDebug(true);
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("javafalo@gmail.com")); //Remetente
//
//        Address[] toUser = InternetAddress.parse(destinos.toString());
//       
//        message.setRecipients(Message.RecipientType.TO, toUser);
//        message.setSubject(titulo);//Assunto
//        message.setText(mensagem);
//        /**
//         * Método para enviar a mensagem criada
//         */
//        Transport t = session.getTransport("smtps");
//        t.connect("smtp.gmail.com", "javafalo@gmail.com", "c90150b123");
//
////        t.sendMessage(message, new Address[]{new InternetAddress("aricomputacao@gmail.com")});
//        t.sendMessage(message, message.getAllRecipients());
//
////        Transport.send(message);
//        System.out.println("Feito!!!");
//
//    }
    public static void main(String[] args) {
        
        try {
            StringUtil su = new StringUtil();
            List<String> des = new ArrayList<>();
            des.add("aricomputacao@gmail.com");
            su.enviarEmail(des, "TESTE", "TESTE");
            
//          System.out.println(removerCaracteresEspeciais("j´so´ss´s´sáá´$$###"));

//        System.out.println(StringUtil.removerCaracteresEspeciais("005.222.403-13"));
//        System.out.println(StringUtil.formatarTelefone("8836110665"));
//        System.out.println(StringUtil.formatarTelefone("88936110665"));
//        System.out.println(StringUtil.formatarTelefone("936110665"));

//        String[] s = StringUtil.quebrarStringPorSeparador("-", "tarefa_teste-fdsddf-rewwer");
//        System.out.println(s[0]);
//        System.out.println(s[1]);
//        System.out.println(s[2]);
        } catch (EmailException ex) {
            Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
