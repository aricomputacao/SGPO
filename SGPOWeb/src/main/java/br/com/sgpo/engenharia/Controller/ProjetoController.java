/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.Controller;

import br.com.sgpo.engenharia.DAO.ProjetoDAO;
import br.com.sgpo.engenharia.modelo.Projeto;
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
public class ProjetoController extends ControllerGenerico<Projeto, Long> implements Serializable{
    
    @Inject
    private ProjetoDAO dao;
  

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public Projeto salvarGerenciar(Projeto projeto) {
        return dao.atualizarGerenciar(projeto);
    }
    
    
     
    
}
