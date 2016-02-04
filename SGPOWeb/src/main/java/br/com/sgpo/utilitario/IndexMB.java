/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitario;

import br.com.sgpo.engenharia.projeto.Controller.NotificacaoProjetoController;
import br.com.sgpo.engenharia.projeto.modelo.NotificacaoProjeto;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@SessionScoped
public class IndexMB  implements Serializable{
    
    @Inject
    private NotificacaoProjetoController notificacaoProjetoController;
    
    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;
    private NotificacaoProjeto notificacaoProjeto;
    
    @PostConstruct
    public void init(){
        try {
            notificacaoProjeto = new NotificacaoProjeto();
            listaDeNotificacaoProjetos = notificacaoProjetoController.consultarTodosDoDia();
        } catch (Exception ex) {
            Logger.getLogger(IndexMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setarNotificacao(NotificacaoProjeto np){
        notificacaoProjeto = np;
        
    }
    
    public List<NotificacaoProjeto> getListaDeNotificacaoProjetos() {
        return listaDeNotificacaoProjetos;
    }

    public NotificacaoProjeto getNotificacaoProjeto() {
        return notificacaoProjeto;
    }

    public void setNotificacaoProjeto(NotificacaoProjeto notificacaoProjeto) {
        this.notificacaoProjeto = notificacaoProjeto;
    }

 
    
 
    
    
}
