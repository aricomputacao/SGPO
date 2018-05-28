/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;

import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author ari
 */
@Stateless
public class UnidadeFederativaDAO extends DAOGenerico<UnidadeFederativa, Integer> implements Serializable{

    public UnidadeFederativaDAO() {
        super(UnidadeFederativa.class);
    }
    
    
}
