/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitario;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@SessionScoped
public class UtilitarioNavegacaoMB extends BeanGenerico implements Serializable {

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Inject
//    private UsuarioController usuarioController;
//    private Usuario usuarioLogado;
//
//    @PostConstruct
//    @Override
//    public void init() {
//        usuarioLogado = usuarioController.usuarioLogin(getContexto().getRemoteUser());
//        if (usuarioLogado.getId() == null) {
//            logout();
//        }
//
//    }
//
    public String logout() {
        return getNomeSistema() + "/j_spring_security_logout";
    }
//
    public static ExternalContext getContexto() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        return external;
    }
//
//    @Override
//    protected Map<String, Object> getCampo() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public Usuario getUsuarioLogado() {
//        return usuarioLogado;
//    }

  

}
