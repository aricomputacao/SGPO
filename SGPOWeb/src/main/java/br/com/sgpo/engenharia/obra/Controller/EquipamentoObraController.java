/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.Controller;

import br.com.sgpo.engenharia.obra.DAO.EquipamentoDAO;
import br.com.sgpo.engenharia.obra.DAO.EquipamentoObraDAO;
import br.com.sgpo.engenharia.obra.modelo.EquipamentoObra;
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
public class EquipamentoObraController extends ControllerGenerico<EquipamentoObra, Long> implements Serializable{

    @Inject
    private EquipamentoObraDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<EquipamentoObra> cosultarAtivosPor(Obra obra) {
        return dao.cosultarAtivosPor(obra);
    }

    
        
}
