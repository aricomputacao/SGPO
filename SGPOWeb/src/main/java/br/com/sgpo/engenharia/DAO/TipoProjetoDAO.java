/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.DAO;

import br.com.sgpo.engenharia.modelo.TipoProjeto;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author ari
 */
@Stateless
public class TipoProjetoDAO extends DAOGenerico<TipoProjeto, Long> implements Serializable{
    
    public TipoProjetoDAO() {
        super(TipoProjeto.class);
    }
    
}
