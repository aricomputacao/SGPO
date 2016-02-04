/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.Controller;

import br.com.sgpo.engenharia.projeto.DAO.MovimentacaoProjetoDAO;
import br.com.sgpo.engenharia.projeto.modelo.MovimentacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
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
public class MovimentacaoProjetoController extends ControllerGenerico<MovimentacaoProjeto, Long> implements Serializable{
    
    @Inject
    private MovimentacaoProjetoDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<MovimentacaoProjeto> consultarTodos(Projeto projeto) {
        return  dao.consultarTodosOrdenadosPor(projeto);
    }
    
}
