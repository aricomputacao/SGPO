/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.managedbean;

import br.com.sgpo.seguranca.controller.ModuloController;
import br.com.sgpo.seguranca.modelo.Modulo;
import br.com.sgpo.utilitario.BeanGenerico;
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
public class ModuloMB extends BeanGenerico implements Serializable{

    @Inject
    private ModuloController moduloController;
    private List<Modulo> listaDeModulos;
    
    @PostConstruct
    @Override
    public void init() {
        try {
            listaDeModulos = moduloController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(ModuloMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Modulo> getListaDeModulos() {
        return listaDeModulos;
    }
    
    
    
}
