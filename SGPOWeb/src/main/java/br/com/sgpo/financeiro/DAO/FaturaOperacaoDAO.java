/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.DAO;

import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.financeiro.modelo.Operacao;
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
public class FaturaOperacaoDAO extends DAOGenerico<FaturaOperacao, Long> implements Serializable{
    
    public FaturaOperacaoDAO() {
        super(FaturaOperacao.class);
    }

    public List<FaturaOperacao> consultarFaturaDa(Operacao o) {
        TypedQuery tq;
        
        tq= getEm().createQuery("SELECT f FROM FaturaOperacao f WHERE f.operacao = :o ORDER BY f.dataVencimento", FaturaOperacao.class)
                .setParameter("o", o);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}