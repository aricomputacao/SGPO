/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class ObraDAO extends DAOGenerico<Obra, Long> implements Serializable {

    public ObraDAO() {
        super(Obra.class);
    }

    public List<Projeto> consultarProjetosDisponiveis(List<Projeto> projetos) {
        TypedQuery<Projeto> tq;
        tq = getEm().createQuery("SELECT p from Projeto p WHERE  p.dataFim  IS NULL and p  NOT IN :pro ORDER BY p.nome", Projeto.class)
                .setParameter("pro", projetos);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<Projeto> consultarProjetosDisponiveis() {
        TypedQuery<Projeto> tq;
        tq = getEm().createQuery("SELECT p from Projeto p WHERE  p.dataFim  IS NULL  ORDER BY p.nome", Projeto.class);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    public List<Projeto> consultarProjetosDasObras() {
        TypedQuery tq;
        tq = getEm().createQuery("SELECT o.listaDeProjetos from Obra o", Collection.class);
        
        return  tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
        
    }

}
