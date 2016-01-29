/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.DAO;

import br.com.sgpo.engenharia.modelo.DocumentoProjeto;
import br.com.sgpo.engenharia.modelo.MovimentacaoDocumento;
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
public class MovimentacaoDocumentoDAO extends DAOGenerico<MovimentacaoDocumento, Long> implements Serializable{
    
    public MovimentacaoDocumentoDAO() {
        super(MovimentacaoDocumento.class);
    }

    public List<MovimentacaoDocumento> consultarTodos(DocumentoProjeto dp) {
        TypedQuery<MovimentacaoDocumento> tq;
        tq = getEm().createQuery("SELECT m FROM MovimentacaoDocumento m WHERE m.documentoProjeto = :dp ORDER BY m.data", MovimentacaoDocumento.class)
                .setParameter("dp", dp);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
