/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.ColaboradorDAO;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.StringUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class ColaboradorController extends ControllerGenerico<Colaborador, Long> implements Serializable{

    @Inject
    private ColaboradorDAO dao;
    
    @Inject
    private CargoController tipoCargo;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    @Override
    public void salvar(Colaborador col) throws Exception{
       col.setCpf(StringUtil.removerCaracteresEspeciais(col.getCpf()));
       dao.atualizar(col);    
    }
    
    
}
