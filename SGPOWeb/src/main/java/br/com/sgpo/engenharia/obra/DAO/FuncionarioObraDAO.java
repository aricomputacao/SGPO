/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.engenharia.obra.modelo.FuncionarioObra;
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
public class FuncionarioObraDAO extends DAOGenerico<FuncionarioObra, Long> implements Serializable{
    
    public FuncionarioObraDAO() {
        super(FuncionarioObra.class);
    }

    public List<FuncionarioObra> consultarPor(Obra obra) {
        TypedQuery<FuncionarioObra> tq;
        
        tq = getEm().createQuery("SELECT f from FuncionarioObra f WHERE f.obra = :obr ORDER BY f.colaborador,f.dataEntrada ", FuncionarioObra.class)
                .setParameter("obr", obra);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
