/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;

import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.FaltaColaborador;
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
public class FaltaColaboradorDAO extends DAOGenerico<FaltaColaborador, Long> implements Serializable{
    
    public FaltaColaboradorDAO() {
        super(FaltaColaborador.class);
    }

    public List<FaltaColaborador> consultarPor(Colaborador colaborador) {
        
        TypedQuery<FaltaColaborador> tq;
        
        tq = getEm().createQuery("SELECT f from FaltaColaborador f WHERE f.colaborador = :col  ORDER BY f.data", FaltaColaborador.class)
                .setParameter("col", colaborador);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<FaltaColaborador> consultarTodosOrdenadoColaboraroData() {
         TypedQuery<FaltaColaborador> tq;
        
        tq = getEm().createQuery("SELECT f from FaltaColaborador f   ORDER BY f.colaborador,f.data", FaltaColaborador.class);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
