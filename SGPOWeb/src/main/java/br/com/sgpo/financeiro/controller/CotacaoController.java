/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.controller;

import br.com.sgpo.administrativo.controller.ClienteController;
import br.com.sgpo.financeiro.DAO.CotacaoDAO;
import br.com.sgpo.financeiro.modelo.Cotacao;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class CotacaoController extends ControllerGenerico<Cotacao, Long> implements Serializable{

    @Inject
    private CotacaoDAO dao;
    @Inject
    private ClienteController clienteController;
    
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
}
