/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.DAO;

import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.CategoriaOperacao;
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
public class CategoriaOperacaoDAO extends DAOGenerico<CategoriaOperacao, Long> implements Serializable{
    
    public CategoriaOperacaoDAO() {
        super(CategoriaOperacao.class);
    }

    public List<CategoriaOperacao> consultar(TipoDeOperacao tipoDeOperacao) {
        TypedQuery tq;
        
        tq = getEm().createQuery("SELECT c FROM CategoriaOperacao c WHERE c.tipoDeOperacao = :tp", CategoriaOperacao.class)
                .setParameter("tp", tipoDeOperacao);
        
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
    
    
    
    
}
