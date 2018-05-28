/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.DAO;

import br.com.sgpo.engenharia.projeto.modelo.MovimentacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class MovimentacaoProjetoDAO extends DAOGenerico<MovimentacaoProjeto, Long> implements Serializable{
    
    public MovimentacaoProjetoDAO() {
        super(MovimentacaoProjeto.class);
    }

    public List<MovimentacaoProjeto> consultarTodosOrdenadosPor(Projeto projeto) {
        TypedQuery<MovimentacaoProjeto> tq;
        tq = getEm().createQuery("SELECT m from MovimentacaoProjeto m WHERE m.projeto = :pro ORDER BY m.data", MovimentacaoProjeto.class)
                .setParameter("pro", projeto);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
    
}
