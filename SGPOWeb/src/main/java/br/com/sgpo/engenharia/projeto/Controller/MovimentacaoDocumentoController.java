/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.Controller;

import br.com.sgpo.engenharia.projeto.DAO.MovimentacaoDocumentoDAO;
import br.com.sgpo.engenharia.projeto.modelo.DocumentoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.MovimentacaoDocumento;
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
public class MovimentacaoDocumentoController extends ControllerGenerico<MovimentacaoDocumento, Long> implements Serializable{

    @Inject
    private MovimentacaoDocumentoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<MovimentacaoDocumento> consultarTodos(DocumentoProjeto dp) {
        return dao.consultarTodos(dp);
    }
    
   
}
