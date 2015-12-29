/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;


import br.com.sgpo.administrativo.DAO.EventoDAO;
import br.com.sgpo.administrativo.modelo.Evento;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class EventoController extends ControllerGenerico<Evento, Long> implements Serializable {
    
    @Inject
    private EventoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
   
    
}
