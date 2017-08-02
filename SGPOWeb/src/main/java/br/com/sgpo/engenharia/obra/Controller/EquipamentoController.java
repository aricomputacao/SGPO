/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.Controller;

import br.com.sgpo.engenharia.obra.DAO.EquipamentoDAO;
import br.com.sgpo.engenharia.obra.modelo.Equipamento;
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
public class EquipamentoController extends ControllerGenerico<Equipamento, Long> implements Serializable{

    @Inject
    private EquipamentoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
}
