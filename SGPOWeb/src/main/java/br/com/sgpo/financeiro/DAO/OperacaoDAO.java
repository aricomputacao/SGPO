/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.DAO;

import br.com.sgpo.financeiro.modelo.Operacao;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class OperacaoDAO extends DAOGenerico<Operacao, Long> implements Serializable{
    
    public OperacaoDAO() {
        super(Operacao.class);
    }

    public List<Operacao> consultarOperacoesDoDia(Date date) {
        TypedQuery tq;
        tq = getEm().createQuery("SELECT o FROM Operacao o WHERE o.dateOperacao = :dt", Operacao.class)
                .setParameter("dt", date);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
