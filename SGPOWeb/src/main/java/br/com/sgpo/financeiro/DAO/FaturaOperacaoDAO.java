/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.DAO;

import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author ari
 */
@Stateless
public class FaturaOperacaoDAO extends DAOGenerico<FaturaOperacao, Long> implements Serializable{
    
    public FaturaOperacaoDAO() {
        super(FaturaOperacao.class);
    }
    
}
