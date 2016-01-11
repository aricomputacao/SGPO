/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.DAO;

import br.com.sgpo.seguranca.modelo.Permissao;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author ari
 */
@Stateless
public class PermissaoDAO  extends DAOGenerico<Permissao, Long> implements Serializable{
    
    public PermissaoDAO() {
        super(Permissao.class);
    }
    
}
