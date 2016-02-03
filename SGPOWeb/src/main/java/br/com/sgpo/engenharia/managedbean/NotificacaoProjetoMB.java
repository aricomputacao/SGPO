/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.managedbean;

import br.com.sgpo.engenharia.Controller.NotificacaoProjetoController;
import br.com.sgpo.engenharia.modelo.NotificacaoProjeto;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class NotificacaoProjetoMB implements Serializable{
    
    @Inject
    private NotificacaoProjetoController notificacaoProjetoController;
    
    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;
    
    @PostConstruct
    public void init(){
        try {
            listaDeNotificacaoProjetos = notificacaoProjetoController.consultarTodosDoDia();
        } catch (Exception ex) {
            Logger.getLogger(NotificacaoProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<NotificacaoProjeto> getListaDeNotificacaoProjetos() {
        return listaDeNotificacaoProjetos;
    }
 
    
    
}
