/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.engenharia.obra.modelo.ItemObra;
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
public class ItemObraDAO extends DAOGenerico<ItemObra, Long> implements Serializable{
    
    public ItemObraDAO() {
        super(ItemObra.class);
    }

    public List<ItemObra> consultarPor(Obra obra) {
        TypedQuery<ItemObra> tq;
        tq = getEm().createQuery("SELECT i FROM ItemObra i WHERE i.obra = :obr ORDER BY i.data , i.etapa,i.fornecedor,i.quantidade", ItemObra.class)
                .setParameter("obr", obra);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
