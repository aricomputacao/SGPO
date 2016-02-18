/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.engenharia.obra.modelo.EquipamentoObra;
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
public class EquipamentoObraDAO extends DAOGenerico<EquipamentoObra, Long> implements Serializable{
    
    public EquipamentoObraDAO() {
        super(EquipamentoObra.class);
    }

    public List<EquipamentoObra> cosultarAtivosPor(Obra obra) {
        TypedQuery<EquipamentoObra> tq;
        tq = getEm().createQuery("SELECT e FROM EquipamentoObra e WHERE e.obra = :obr and e.ativo =  true ORDER BY e.obra ,e.dataEntrada", EquipamentoObra.class)
                .setParameter("obr", obra);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
