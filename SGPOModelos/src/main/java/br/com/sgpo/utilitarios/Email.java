/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ari
 */
public class Email {

    public static void enviarEmail() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        //Utilize o hostname do seu provedor de Email
        System.out.println("alterando hostname...");
        email.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(465);
        //Adicione os destinatários
        email.addTo("aricomputacao@gmail.com", "Jose");
        //Configure o seu Email do qual enviará
        email.setFrom("javafalo@gmail.com", "Seu nome");
        //Adicione um assunto
        email.setSubject("Test message");
        //Adicione a mensagem do Email
        email.setMsg("This is a simple test of commons-email");
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        System.out.println("autenticando...");
        email.setTLS(true);
        email.setSSL(true);
        email.setAuthentication("javafalo@gmail.com", "senha");
        System.out.println("enviando...");
        email.send();
        System.out.println("Email enviado!");
    }

    public static void main(String[] args) {
        try {
           Email.enviarEmail();
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
