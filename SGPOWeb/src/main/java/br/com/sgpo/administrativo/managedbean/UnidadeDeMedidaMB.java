/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.UnidadeDeMedidaController;
import br.com.sgpo.administrativo.modelo.UnidadeDeMedida;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
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
public class UnidadeDeMedidaMB  extends BeanGenerico implements Serializable{

    @Inject
    private UnidadeDeMedidaController unidadeDeMedidaController;
    
    private UnidadeDeMedida unidadeDeMedida;
    
    private List<UnidadeDeMedida> listaDeUnidadesDeMedidas;
    
    
    @PostConstruct
    @Override
    public void init() {
        try {
            unidadeDeMedida = new UnidadeDeMedida();
            
            listaDeUnidadesDeMedidas = unidadeDeMedidaController.consultarTodosOrdenadorPor("abreviacao");
        } catch (Exception ex) {
            Logger.getLogger(UnidadeDeMedidaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar(){
        try {
            unidadeDeMedidaController.salvar(unidadeDeMedida);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(UnidadeDeMedidaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public List<UnidadeDeMedida> getListaDeUnidadesDeMedidas() {
        return listaDeUnidadesDeMedidas;
    }
    
    
}
