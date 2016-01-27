/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.Controller;

import br.com.sgpo.engenharia.DAO.TipoProjetoDAO;
import br.com.sgpo.engenharia.modelo.TipoProjeto;
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
public class TipoProjetoController extends ControllerGenerico<TipoProjeto, Long> implements Serializable{

    @Inject
    private TipoProjetoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
}
