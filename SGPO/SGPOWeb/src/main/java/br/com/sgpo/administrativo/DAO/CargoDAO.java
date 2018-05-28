/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;

import br.com.sgpo.administrativo.modelo.Cargo;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class CargoDAO extends DAOGenerico<Cargo, Integer> implements Serializable{
    
    public CargoDAO() {
        super(Cargo.class);
    }

    public Cargo buscarPorNome(String nome) {
        TypedQuery<Cargo> tq;
        tq = getEm().createQuery("SELECT c FROM Cargo c WHERE c.nome = :nome", Cargo.class)
                .setParameter("nome", nome);
        
        return tq.getResultList().isEmpty() ? new Cargo() : tq.getSingleResult();
    }
    
}
