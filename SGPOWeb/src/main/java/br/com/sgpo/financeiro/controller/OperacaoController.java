/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.controller;

import br.com.sgpo.financeiro.DAO.FaturaOperacaoDAO;
import br.com.sgpo.financeiro.DAO.OperacaoDAO;
import br.com.sgpo.financeiro.modelo.Operacao;
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
public class OperacaoController extends ControllerGenerico<Operacao, Long> implements Serializable{

    @Inject
    private OperacaoDAO dao;
    @Inject
    private FaturaOperacaoDAO faturaDAO;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
}
