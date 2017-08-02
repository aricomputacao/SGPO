/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.engenharia.obra.modelo.ItemObra;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class ItemObraDAO extends DAOGenerico<ItemObra, Long> implements Serializable {

    public ItemObraDAO() {
        super(ItemObra.class);
    }

    public List<ItemObra> consultarPor(Obra obra) {
        TypedQuery<ItemObra> tq;
        Calendar c = Calendar.getInstance();
        tq = getEm().createQuery("SELECT i FROM ItemObra i WHERE i.obra = :obr and year(i.data) = :ano ORDER BY month(i.data), i.etapa,day(i.data),i.fornecedor,i.quantidade", ItemObra.class)
                .setParameter("obr", obra)
                .setParameter("ano", c.get(Calendar.YEAR));

        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    public List<ItemObra> consultarOrdenadoPorEtapaTipo(Obra obra) {
        TypedQuery<ItemObra> tq;
        Calendar c = Calendar.getInstance();
        tq = getEm().createQuery("SELECT i FROM ItemObra i WHERE i.obra = :obr ORDER BY i.etapa,i.item.classificacao,i.quantidade", ItemObra.class)
                .setParameter("obr", obra);

        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<ItemObra> consultarPor(Obra obra, int mes) {
        TypedQuery<ItemObra> tq;
        Calendar c = Calendar.getInstance();
        tq = getEm().createQuery("SELECT i FROM ItemObra i WHERE i.obra = :obr and month(i.data) = :mes and year(i.data) = :ano ORDER BY month(i.data), i.etapa,day(i.data),i.fornecedor,i.quantidade", ItemObra.class)
                .setParameter("obr", obra)
                .setParameter("ano", c.get(Calendar.YEAR))
                .setParameter("mes", mes);

        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<ItemObra> consultarPor(Obra obra, Fornecedor f) {
          TypedQuery<ItemObra> tq;
        Calendar c = Calendar.getInstance();
        tq = getEm().createQuery("SELECT i FROM ItemObra i WHERE i.obra = :obr and i.fornecedor = :for ORDER BY i.fornecedor,i.etapa,i.data", ItemObra.class)
                .setParameter("obr", obra)
                .setParameter("for", f);

        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

}
