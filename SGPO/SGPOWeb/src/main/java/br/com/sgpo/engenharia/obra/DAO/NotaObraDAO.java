/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.engenharia.obra.modelo.NotaObra;
import br.com.sgpo.engenharia.obra.modelo.Obra;
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
public class NotaObraDAO extends DAOGenerico<NotaObra, Long> implements Serializable{

    public NotaObraDAO() {
        super(NotaObra.class);
    }

    public List<NotaObra> consultarPor(Obra obra) {
        TypedQuery<NotaObra> tq;
        tq = getEm().createQuery("SELECT n from NotaObra n WHERE n.obra = :obr ORDER BY n.fornecedor ", NotaObra.class)
                .setParameter("obr", obra);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    
}
