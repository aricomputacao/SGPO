/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitario;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ari
 */
@Singleton
@Startup
public class ConfiguracaoSistemaMB implements Serializable {
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
    
    private boolean processadoImagemLogo;

    public String getDiretorioReal(String diretorio) {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(diretorio);

    }

    @PostConstruct
    public void init() {
       processadoImagemLogo =false;
    }
    
    public void marcarImagemLogoComoProcessada(){
        processadoImagemLogo = true;
        
    }

    public boolean isProcessadoImagemLogo() {
        return processadoImagemLogo;
    }
    
    

}
