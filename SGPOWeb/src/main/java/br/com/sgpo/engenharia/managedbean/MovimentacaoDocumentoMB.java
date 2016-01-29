/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.managedbean;

import br.com.sgpo.engenharia.Controller.MovimentacaoDocumentoController;
import br.com.sgpo.engenharia.modelo.DocumentoProjeto;
import br.com.sgpo.engenharia.modelo.MovimentacaoDocumento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class MovimentacaoDocumentoMB implements Serializable{
    
    @Inject
    private MovimentacaoDocumentoController movimentacaoController;
    private List<MovimentacaoDocumento> listaMovimentacao;
    
    @PostConstruct
    public void init(){
        listaMovimentacao = new ArrayList<>();
    }
    
    public void consultaMovimentacao(DocumentoProjeto dp){
        try {
            listaMovimentacao = movimentacaoController.consultarTodos(dp);
        } catch (Exception ex) {
            Logger.getLogger(MovimentacaoDocumentoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<MovimentacaoDocumento> getListaMovimentacao() {
        return listaMovimentacao;
    }
    
    
    
    
}
