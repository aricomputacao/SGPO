/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.Controller;

import br.com.sgpo.engenharia.DAO.NotificacaoProjetoDAO;
import br.com.sgpo.engenharia.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.modelo.Projeto;
import br.com.sgpo.seguranca.modelo.Usuario;
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
public class NotificacaoProjetoController extends ControllerGenerico<NotificacaoProjeto, Long> implements Serializable{

    @Inject
    private NotificacaoProjetoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<NotificacaoProjeto> consultarTodos(Projeto projeto) {
        return dao.consultarTodos(projeto);
    }

    
}
