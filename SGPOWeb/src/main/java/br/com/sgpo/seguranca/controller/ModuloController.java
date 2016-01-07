/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.controller;

import br.com.sgpo.seguranca.DAO.ModuloDAO;
import br.com.sgpo.seguranca.modelo.Modulo;
import br.com.sgpo.utilitario.ConfiguracaoSistemaMB;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class ModuloController extends ControllerGenerico<Modulo, Long> implements Serializable{

    @Inject
    private ModuloDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    public boolean existeModulo(String nome){
        return dao.existeModulo(nome);
    }
    
    public void criarModulos() {
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.sgpo.arquivos.modulos");
        Enumeration<String> modulos = bundle.getKeys();
        while (modulos.hasMoreElements()) {
            String string = modulos.nextElement();
            String nome = bundle.getString(string);
            try {
                if (!existeModulo(nome)) {
                    Modulo m = new Modulo();
                    m.setNome(nome);
                    salvar(m);
                }
            } catch (Exception ex) {
                Logger.getLogger(ConfiguracaoSistemaMB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
