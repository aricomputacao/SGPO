/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.ContaEmailDAO;
import br.com.sgpo.administrativo.modelo.ContaEmail;
import br.com.sgpo.administrativo.modelo.Empresa;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless 
public class ContaEmailController extends ControllerGenerico<ContaEmail, Integer> implements Serializable{

    @Inject
    private ContaEmailDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<ContaEmail> cosultar(Empresa empresa) {
       return dao.consultar(empresa);
    }
    
}
