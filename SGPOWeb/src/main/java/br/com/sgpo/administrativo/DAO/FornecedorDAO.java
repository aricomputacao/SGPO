/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;

import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;

/**
 *
 * @author Giancarlo
 */
public class FornecedorDAO extends DAOGenerico<Fornecedor, Long> implements Serializable{
    
    public FornecedorDAO() {
        super(Fornecedor.class);
    }
    
}
