/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.Controller;

import br.com.sgpo.engenharia.obra.DAO.FuncionarioObraDAO;
import br.com.sgpo.engenharia.obra.modelo.FuncionarioObra;
import br.com.sgpo.engenharia.obra.modelo.Obra;
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
public class FuncionarioObraController extends ControllerGenerico<FuncionarioObra, Long> implements Serializable{

    @Inject
    private FuncionarioObraDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    

    public List<FuncionarioObra> consultarPor(Obra obra) {
        return dao.consultarPor(obra);
    }

    
    
}
