/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.managedbean;

import br.com.sgpo.engenharia.enumeration.ClassificacaoItem;
import br.com.sgpo.engenharia.obra.Controller.ItemController;
import br.com.sgpo.engenharia.obra.modelo.Item;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ItemMB extends BeanGenerico implements Serializable {

    @Inject
    private ItemController itemController;
    
    private Item item;
    
    private List<Item> listaDeItens;
    
            
    @PostConstruct
    @Override
    public void init() {
        try {
            item = new Item();
            listaDeItens = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addItem(){
        try {
            itemController.salvar(item);
            listaDeItens = itemController.consultarTodosOrdenadorPor("nome");
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarItens(){
        try {
            listaDeItens = itemController.consultarLike("nome", getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(ItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ClassificacaoItem[] getListaDeClassificacoesItem(){
        return ClassificacaoItem.values();
    }
    
    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getListaDeItens() {
        return listaDeItens;
    }
    
    

}
