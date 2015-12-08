/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitario;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Ari
 */
@Singleton
@Startup
public class ConfiguracaoSistemaMB implements Serializable{
//    @Inject
//    private UsuarioController usuarioController;
//    
//    
//    @PostConstruct
//    public void init(){
//        try {
//            usuarioController.criarUsuarioAdministrador();
//        } catch (Exception ex) {
//            Logger.getLogger(ConfiguracaoSistemaMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
