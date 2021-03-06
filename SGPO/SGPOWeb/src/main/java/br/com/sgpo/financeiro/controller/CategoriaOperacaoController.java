/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.controller;

import br.com.sgpo.financeiro.DAO.CategoriaOperacaoDAO;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.CategoriaOperacao;
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
public class CategoriaOperacaoController extends ControllerGenerico<CategoriaOperacao, Long> implements Serializable{

    @Inject
    private CategoriaOperacaoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<CategoriaOperacao> consultar(TipoDeOperacao tipoDeOperacao) {
        return dao.consultar(tipoDeOperacao);
    }
    
    
    
}
