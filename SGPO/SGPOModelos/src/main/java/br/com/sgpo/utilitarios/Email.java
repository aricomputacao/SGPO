/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import java.util.List;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ari
 */
public class Email {

    public static void enviarEmail(String emailOrigem,String host,int porta ,String senha, List<String> destinos, String mensagem,String titulo) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        
        email.setHostName(host);
        //Quando a porta utilizada não é a padrão (gmail = 465)
//        email.setSmtpPort(465);
        email.setSmtpPort(porta);

        //Adicione os destinatários
        for (String destino : destinos) {
            email.addTo(destino, "", "UTF-8");
        }

        email.setFrom(emailOrigem, "");
        //Adicione um assunto
        email.setSubject(titulo);
        //Adicione a mensagem do Email
        email.setMsg(mensagem);
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        email.setTLS(true);
        email.setSSL(true);
        email.setAuthentication(emailOrigem, senha);
        email.send();
    }

    public static void main(String[] args) {
       
    }
}
