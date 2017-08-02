/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.CargoDAO;
import br.com.sgpo.administrativo.enumeration.TipoCargo;
import br.com.sgpo.administrativo.modelo.Cargo;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class CargoController extends ControllerGenerico<Cargo, Integer> implements Serializable{

    @Inject
    private CargoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
}
