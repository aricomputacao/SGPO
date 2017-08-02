/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;

import br.com.sgpo.administrativo.modelo.ContaEmail;
import br.com.sgpo.administrativo.modelo.Empresa;
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
public class ContaEmailDAO extends DAOGenerico<ContaEmail, Integer> implements Serializable{
    
    public ContaEmailDAO() {
        super(ContaEmail.class);
    }

    public List<ContaEmail> consultar(Empresa empresa) {
        TypedQuery<ContaEmail> tq;
        tq = getEm().createQuery("SELECT c FROM ContaEmail c WHERE c.empresa = :emp", ContaEmail.class)
                .setParameter("emp", empresa);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
