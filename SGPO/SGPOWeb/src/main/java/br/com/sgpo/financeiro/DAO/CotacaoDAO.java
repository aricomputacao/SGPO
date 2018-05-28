/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.DAO;

import br.com.sgpo.financeiro.modelo.Cotacao;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class CotacaoDAO extends DAOGenerico<Cotacao, Long> implements Serializable{
    
    public CotacaoDAO() {
        super(Cotacao.class);
    }
    
}
