/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.DAO;

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
public class ProjetoDAO extends DAOGenerico<Projeto, Long> implements Serializable {

    public ProjetoDAO() {
        super(Projeto.class);
    }

    public List<Projeto> consultarTodosAtivos() {
        TypedQuery<Projeto> tq;
        tq = getEm().createQuery("SELECT p from Projeto p WHERE p.dataFim  IS NULL ORDER BY p.dataInicio", Projeto.class);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

}
