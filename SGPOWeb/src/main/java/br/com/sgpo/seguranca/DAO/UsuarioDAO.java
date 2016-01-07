/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.DAO;

import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author ari
 */
@Stateless
public class UsuarioDAO extends DAOGenerico<Usuario, Long> implements Serializable{
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
}
